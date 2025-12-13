package com.epayclub.sdk.services;

import com.epayclub.sdk.EpayClubClient;
import com.epayclub.sdk.models.paymentlinks.*;
import com.github.tomakehurst.wiremock.junit5.WireMockRuntimeInfo;
import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.assertj.core.api.Assertions.*;

/**
 * Integration tests for PaymentLinksService using WireMock.
 */
@WireMockTest
class PaymentLinksServiceTest {

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
    void create_shouldCreatePaymentLink() {
        stubFor(post(urlEqualTo("/payment-links"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody("""
                            {
                                "status": "success",
                                "data": {
                                    "linkId": "link123",
                                    "name": "Test Product",
                                    "amount": 5000.0,
                                    "currency": "NGN",
                                    "url": "https://pay.epayclub.com/link123",
                                    "active": true
                                }
                            }
                            """)));

        CreateLinkRequest request = CreateLinkRequest.builder()
                .name("Test Product")
                .amount(5000.0)
                .currency("NGN")
                .description("Test payment link")
                .build();

        CreateLinkResponse response = client.paymentLinks().create(request);

        assertThat(response.getData().getLinkId()).isEqualTo("link123");
        assertThat(response.getData().getAmount()).isEqualTo(5000.0);
        assertThat(response.getData().getActive()).isTrue();
    }

    @Test
    void list_shouldFetchPaymentLinks() {
        stubFor(get(urlPathEqualTo("/payment-links"))
                .withQueryParam("page", equalTo("1"))
                .withQueryParam("limit", equalTo("10"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody("""
                            {
                                "status": "success",
                                "data": [
                                    {
                                        "linkId": "link1",
                                        "name": "Product 1",
                                        "amount": 1000.0
                                    },
                                    {
                                        "linkId": "link2",
                                        "name": "Product 2",
                                        "amount": 2000.0
                                    }
                                ],
                                "pagination": {
                                    "page": 1,
                                    "limit": 10,
                                    "total": 2,
                                    "totalPages": 1
                                }
                            }
                            """)));

        FetchLinksRequest request = FetchLinksRequest.builder()
                .page(1)
                .limit(10)
                .build();

        FetchLinksResponse response = client.paymentLinks().list(request);

        assertThat(response.getData()).hasSize(2);
        assertThat(response.getPagination().getTotal()).isEqualTo(2);
    }

    @Test
    void types_shouldFetchLinkTypes() {
        stubFor(get(urlEqualTo("/payment-links/types"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody("""
                            {
                                "status": "success",
                                "data": [
                                    {
                                        "type": "one_time",
                                        "name": "One Time Payment",
                                        "description": "Single payment link"
                                    },
                                    {
                                        "type": "recurring",
                                        "name": "Recurring Payment",
                                        "description": "Subscription payment link"
                                    }
                                ]
                            }
                            """)));

        LinkTypesResponse response = client.paymentLinks().types();

        assertThat(response.getData()).hasSize(2);
        assertThat(response.getData().get(0).getType()).isEqualTo("one_time");
    }

    @Test
    void edit_shouldEditPaymentLink() {
        stubFor(patch(urlEqualTo("/payment-links/link123"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody("""
                            {
                                "status": "success",
                                "data": {
                                    "linkId": "link123",
                                    "name": "Updated Product",
                                    "amount": 7500.0
                                }
                            }
                            """)));

        EditLinkRequest request = EditLinkRequest.builder()
                .linkId("link123")
                .name("Updated Product")
                .amount(7500.0)
                .build();

        EditLinkResponse response = client.paymentLinks().edit(request);

        assertThat(response.getData().getName()).isEqualTo("Updated Product");
        assertThat(response.getData().getAmount()).isEqualTo(7500.0);
    }

    @Test
    void activate_shouldActivateLink() {
        stubFor(post(urlEqualTo("/payment-links/link123/activate"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody("""
                            {
                                "status": "success",
                                "data": {
                                    "linkId": "link123",
                                    "active": true
                                }
                            }
                            """)));

        ActivateLinkRequest request = new ActivateLinkRequest("link123");

        ActivateLinkResponse response = client.paymentLinks().activate(request);

        assertThat(response.getData().getActive()).isTrue();
    }

    @Test
    void deactivate_shouldDeactivateLink() {
        stubFor(post(urlEqualTo("/payment-links/link123/deactivate"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody("""
                            {
                                "status": "success",
                                "data": {
                                    "linkId": "link123",
                                    "active": false
                                }
                            }
                            """)));

        DeactivateLinkRequest request = new DeactivateLinkRequest("link123");

        DeactivateLinkResponse response = client.paymentLinks().deactivate(request);

        assertThat(response.getData().getActive()).isFalse();
    }

    @Test
    void frequencies_shouldFetchFrequencies() {
        stubFor(get(urlEqualTo("/payment-links/frequencies"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody("""
                            {
                                "status": "success",
                                "data": [
                                    {
                                        "frequency": "daily",
                                        "name": "Daily",
                                        "description": "Once per day"
                                    },
                                    {
                                        "frequency": "weekly",
                                        "name": "Weekly",
                                        "description": "Once per week"
                                    }
                                ]
                            }
                            """)));

        FrequenciesResponse response = client.paymentLinks().frequencies();

        assertThat(response.getData()).hasSize(2);
        assertThat(response.getData().get(0).getFrequency()).isEqualTo("daily");
    }

    @Test
    void cancelRecurringPayments_shouldCancelRecurring() {
        stubFor(post(urlEqualTo("/payment-links/link123/cancel-recurring"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody("""
                            {
                                "status": "success",
                                "message": "Recurring payments cancelled"
                            }
                            """)));

        CancelRecurringPaymentsRequest request = CancelRecurringPaymentsRequest.builder()
                .linkId("link123")
                .subscriptionId("sub456")
                .build();

        CancelRecurringPaymentsResponse response = client.paymentLinks().cancelRecurringPayments(request);

        assertThat(response.getStatus()).isEqualTo("success");
    }
}
