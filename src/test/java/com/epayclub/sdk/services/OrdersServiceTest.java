package com.epayclub.sdk.services;

import com.epayclub.sdk.models.orders.*;
import com.github.tomakehurst.wiremock.junit5.WireMockRuntimeInfo;
import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.assertj.core.api.Assertions.*;

import com.epayclub.sdk.EpayClubClient;

/**
 * Integration tests for OrdersService using WireMock.
 */
@WireMockTest
class OrdersServiceTest {

    private EpayClubClient client;

    @BeforeEach
    void setUp(WireMockRuntimeInfo wmRuntimeInfo) {
        client = EpayClubClient.builder()
                .apiKey("test-api-key")
                .baseUrl(wmRuntimeInfo.getHttpBaseUrl())
                .retries(0) // Disable retries for testing
                .build();
    }

    @Test
    void create_shouldSendCorrectRequest() {
        stubFor(post(urlEqualTo("/orders"))
                .withHeader("api-key", equalTo("test-api-key"))
                .withHeader("Content-Type", equalTo("application/json"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody("""
                            {
                                "status": "success",
                                "statusId": "00",
                                "responseCode": "00",
                                "message": "Order created successfully",
                                "data": {
                                    "orderId": "order123",
                                    "orderReference": "ref123",
                                    "amount": 1000.0,
                                    "currency": "NGN",
                                    "email": "test@example.com",
                                    "checkoutUrl": "https://checkout.epayclub.com/order123"
                                }
                            }
                            """)));

        CreateOrderRequest request = CreateOrderRequest.builder()
                .amount(1000.0)
                .currency("NGN")
                .email("test@example.com")
                .firstName("John")
                .lastName("Doe")
                .build();

        CreateOrderResponse response = client.orders().create(request);

        assertThat(response.getStatus()).isEqualTo("success");
        assertThat(response.getData()).isNotNull();
        assertThat(response.getData().getOrderId()).isEqualTo("order123");
        assertThat(response.getData().getAmount()).isEqualTo(1000.0);

        verify(postRequestedFor(urlEqualTo("/orders"))
                .withRequestBody(containing("\"amount\":1000.0"))
                .withRequestBody(containing("\"currency\":\"NGN\""))
                .withRequestBody(containing("\"email\":\"test@example.com\"")));
    }

    @Test
    void fee_shouldCalculateFee() {
        stubFor(post(urlEqualTo("/orders/fee"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody("""
                            {
                                "status": "success",
                                "data": {
                                    "fee": 50.0,
                                    "total": 1050.0,
                                    "amount": 1000.0,
                                    "currency": "NGN"
                                }
                            }
                            """)));

        FeeRequest request = FeeRequest.builder()
                .amount(1000.0)
                .currency("NGN")
                .build();

        FeeResponse response = client.orders().fee(request);

        assertThat(response.getStatus()).isEqualTo("success");
        assertThat(response.getData().getFee()).isEqualTo(50.0);
        assertThat(response.getData().getTotal()).isEqualTo(1050.0);
    }

    @Test
    void status_shouldReturnOrderStatus() {
        stubFor(get(urlEqualTo("/orders/order123/status"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody("""
                            {
                                "status": "success",
                                "data": {
                                    "orderId": "order123",
                                    "orderStatus": "completed",
                                    "paymentStatus": "paid"
                                }
                            }
                            """)));

        OrderStatusRequest request = new OrderStatusRequest("order123");

        OrderStatusResponse response = client.orders().status(request);

        assertThat(response.getData().getOrderId()).isEqualTo("order123");
        assertThat(response.getData().getOrderStatus()).isEqualTo("completed");
    }

    @Test
    void verify_shouldVerifyOrder() {
        stubFor(get(urlEqualTo("/orders/order456/verify"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody("""
                            {
                                "status": "success",
                                "data": {
                                    "orderId": "order456",
                                    "verified": true,
                                    "paymentStatus": "paid"
                                }
                            }
                            """)));

        VerifyOrderRequest request = new VerifyOrderRequest("order456");

        VerifyOrderResponse response = client.orders().verify(request);

        assertThat(response.getData().getVerified()).isTrue();
        assertThat(response.getData().getPaymentStatus()).isEqualTo("paid");
    }

    @Test
    void timeline_shouldReturnOrderTimeline() {
        stubFor(get(urlEqualTo("/orders/order789/timeline"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody("""
                            {
                                "status": "success",
                                "data": [
                                    {
                                        "eventType": "ORDER_CREATED",
                                        "description": "Order was created",
                                        "timestamp": "2024-01-15T10:00:00Z"
                                    },
                                    {
                                        "eventType": "PAYMENT_INITIATED",
                                        "description": "Payment was initiated",
                                        "timestamp": "2024-01-15T10:05:00Z"
                                    }
                                ]
                            }
                            """)));

        OrderTimelineRequest request = new OrderTimelineRequest("order789");

        OrderTimelineResponse response = client.orders().timeline(request);

        assertThat(response.getData()).hasSize(2);
        assertThat(response.getData().get(0).getEventType()).isEqualTo("ORDER_CREATED");
    }

    @Test
    void pay_shouldProcessPayment() {
        stubFor(post(urlEqualTo("/orders/pay"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody("""
                            {
                                "status": "success",
                                "data": {
                                    "orderId": "order123",
                                    "transactionId": "txn456",
                                    "paymentStatus": "processing"
                                }
                            }
                            """)));

        PayOrderRequest request = PayOrderRequest.builder()
                .orderId("order123")
                .paymentMethod("card")
                .cardNumber("4111111111111111")
                .expiryMonth("12")
                .expiryYear("25")
                .cvv("123")
                .build();

        PayOrderResponse response = client.orders().pay(request);

        assertThat(response.getData().getTransactionId()).isEqualTo("txn456");
        assertThat(response.getData().getPaymentStatus()).isEqualTo("processing");
    }
}
