package com.epayclub.sdk.http;

import com.epayclub.sdk.EpayClubClient;
import com.epayclub.sdk.errors.EpayClubApiException;
import com.epayclub.sdk.models.orders.CreateOrderRequest;
import com.github.tomakehurst.wiremock.junit5.WireMockRuntimeInfo;
import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.assertj.core.api.Assertions.*;

/**
 * Tests for HTTP executor behavior including error handling and retries.
 */
@WireMockTest
class HttpExecutorTest {

    private EpayClubClient client;

    @BeforeEach
    void setUp(WireMockRuntimeInfo wmRuntimeInfo) {
        client = EpayClubClient.builder()
                .apiKey("test-api-key")
                .baseUrl(wmRuntimeInfo.getHttpBaseUrl())
                .retries(0)
                .build();
    }

    @Test
    void shouldParseJsonErrorResponse() {
        stubFor(post(urlEqualTo("/orders"))
                .willReturn(aResponse()
                        .withStatus(400)
                        .withHeader("Content-Type", "application/json")
                        .withBody("""
                            {
                                "status": "error",
                                "statusId": "01",
                                "responseCode": "400",
                                "message": "Invalid request: amount is required"
                            }
                            """)));

        CreateOrderRequest request = new CreateOrderRequest();
        request.setAmount(1000.0);
        request.setCurrency("NGN");
        request.setEmail("test@example.com");

        assertThatThrownBy(() -> client.orders().create(request))
                .isInstanceOf(EpayClubApiException.class)
                .satisfies(ex -> {
                    EpayClubApiException apiEx = (EpayClubApiException) ex;
                    assertThat(apiEx.getHttpStatus()).isEqualTo(400);
                    assertThat(apiEx.getStatus()).isEqualTo("error");
                    assertThat(apiEx.getStatusId()).isEqualTo("01");
                    assertThat(apiEx.getMessage()).contains("amount is required");
                    assertThat(apiEx.getRawBody()).contains("Invalid request");
                    assertThat(apiEx.getParsedBody()).isNotNull();
                });
    }

    @Test
    void shouldHandleNonJsonErrorResponse() {
        stubFor(post(urlEqualTo("/orders"))
                .willReturn(aResponse()
                        .withStatus(503)
                        .withHeader("Content-Type", "text/html")
                        .withBody("<html><body>Service Unavailable</body></html>")));

        CreateOrderRequest request = new CreateOrderRequest();
        request.setAmount(1000.0);
        request.setCurrency("NGN");
        request.setEmail("test@example.com");

        assertThatThrownBy(() -> client.orders().create(request))
                .isInstanceOf(EpayClubApiException.class)
                .satisfies(ex -> {
                    EpayClubApiException apiEx = (EpayClubApiException) ex;
                    assertThat(apiEx.getHttpStatus()).isEqualTo(503);
                    assertThat(apiEx.getRawBody()).contains("Service Unavailable");
                    assertThat(apiEx.getParsedBody()).isNull();
                });
    }

    @Test
    void shouldIncludeRequestMethodAndPathInError() {
        stubFor(post(urlEqualTo("/orders"))
                .willReturn(aResponse()
                        .withStatus(500)
                        .withHeader("Content-Type", "application/json")
                        .withBody("{\"message\":\"Internal server error\"}")));

        CreateOrderRequest request = new CreateOrderRequest();
        request.setAmount(1000.0);
        request.setCurrency("NGN");
        request.setEmail("test@example.com");

        assertThatThrownBy(() -> client.orders().create(request))
                .isInstanceOf(EpayClubApiException.class)
                .satisfies(ex -> {
                    EpayClubApiException apiEx = (EpayClubApiException) ex;
                    assertThat(apiEx.getRequestMethod()).isEqualTo("POST");
                    assertThat(apiEx.getRequestPath()).isEqualTo("/orders");
                });
    }

    @Test
    void shouldRetryOnTransientErrorsForGet(WireMockRuntimeInfo wmRuntimeInfo) {
        // Create client with retries enabled
        EpayClubClient retryClient = EpayClubClient.builder()
                .apiKey("test-api-key")
                .baseUrl(wmRuntimeInfo.getHttpBaseUrl())
                .retries(2)
                .build();

        // First two calls fail, third succeeds
        stubFor(get(urlEqualTo("/bank-codes"))
                .inScenario("retry-test")
                .whenScenarioStateIs("Started")
                .willReturn(aResponse().withStatus(503))
                .willSetStateTo("first-retry"));

        stubFor(get(urlEqualTo("/bank-codes"))
                .inScenario("retry-test")
                .whenScenarioStateIs("first-retry")
                .willReturn(aResponse().withStatus(503))
                .willSetStateTo("second-retry"));

        stubFor(get(urlEqualTo("/bank-codes"))
                .inScenario("retry-test")
                .whenScenarioStateIs("second-retry")
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody("""
                            {
                                "status": "success",
                                "data": []
                            }
                            """)));

        var response = retryClient.paymentOperations().bankCodes();

        assertThat(response.getStatus()).isEqualTo("success");
        verify(exactly(3), getRequestedFor(urlEqualTo("/bank-codes")));
    }

    @Test
    void shouldNotRetryPostByDefault() {
        // POST requests should not be retried
        stubFor(post(urlEqualTo("/orders"))
                .willReturn(aResponse().withStatus(503)));

        CreateOrderRequest request = new CreateOrderRequest();
        request.setAmount(1000.0);
        request.setCurrency("NGN");
        request.setEmail("test@example.com");

        assertThatThrownBy(() -> client.orders().create(request))
                .isInstanceOf(EpayClubApiException.class);

        verify(exactly(1), postRequestedFor(urlEqualTo("/orders")));
    }

    @Test
    void shouldHandle429TooManyRequests() {
        stubFor(get(urlEqualTo("/bank-codes"))
                .willReturn(aResponse()
                        .withStatus(429)
                        .withHeader("Content-Type", "application/json")
                        .withBody("{\"message\":\"Rate limit exceeded\"}")));

        assertThatThrownBy(() -> client.paymentOperations().bankCodes())
                .isInstanceOf(EpayClubApiException.class)
                .satisfies(ex -> {
                    EpayClubApiException apiEx = (EpayClubApiException) ex;
                    assertThat(apiEx.getHttpStatus()).isEqualTo(429);
                });
    }
}
