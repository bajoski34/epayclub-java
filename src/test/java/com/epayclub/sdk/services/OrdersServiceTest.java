package com.epayclub.sdk.services;

import com.epayclub.sdk.models.orders.*;
import com.github.tomakehurst.wiremock.junit5.WireMockRuntimeInfo;
import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.assertj.core.api.Assertions.*;

import com.epayclub.sdk.EpayClubClient;

import java.util.Map;

/**
 * Integration tests for OrdersService using WireMock.
 */
@WireMockTest
class OrdersServiceTest {

    private EpayClubClient client;

    @BeforeEach
    void setUp(WireMockRuntimeInfo wmRuntimeInfo) {
        client = EpayClubClient.builder()
                .apiKey(System.getenv("EPAYCLUB_API_KEY"))
                .merchantEncryptionKey(System.getenv("EPAYCLUB_ENCRYPTION_KEY"))
                .baseUrl(wmRuntimeInfo.getHttpBaseUrl())
                .retries(0) // Disable retries for testing
                .build();
    }

    @Test
    void create_shouldSendCorrectRequest() {
        stubFor(post(urlEqualTo("/checkout/order/create"))
                .withHeader("api-key", equalTo("test-api-key"))
                .withHeader("Content-Type", equalTo("application/json"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody("""
                                {
                                   "data": {
                                     "order": {
                                       "reference": "805551685",
                                       "processorReference": "EPCLB-3134A6BDF47211EF93AB06BA2661E92B",
                                       "orderPaymentReference": null,
                                       "amount": 100,
                                       "fee": 0,
                                       "feeRate": null,
                                       "statusId": 1,
                                       "status": "Initiated",
                                       "currency": "USD",
                                       "narration": "Pay",
                                       "paymentLinkId": null,
                                       "recurringPaymentId": null,
                                       "paymentLinkReference": null,
                                       "recurringPaymentReference": null
                                     },
                                     "subsidiary": {
                                       "id": 1,
                                       "name": "Merchant Epayclub",
                                       "country": "NG",
                                       "supportEmail": "merchant@epayclub.com",
                                       "customization": []
                                     },
                                     "customer": {
                                       "email": "jones@gmail.com",
                                       "firstName": "James",
                                       "lastName": "Jones",
                                       "mobile": "08101234542",
                                       "country": "GB"
                                     },
                                     "payment": {
                                       "code": null,
                                       "source": null,
                                       "selectedOption": null,
                                       "accountNumber": null,
                                       "bankProviderName": null
                                     },
                                     "otherPaymentOptions": [
                                       {
                                         "code": "C",
                                         "name": "Card Payment",
                                         "currency": "USD"
                                       }
                                     ],
                                     "savedCards": [],
                                     "subsidiaryOrderSummary": {
                                       "orderName": "Merchant Epayclub Order 805551685",
                                       "totalAmount": 100,
                                       "reference": "805551685",
                                       "currency": "USD",
                                       "orderItems": [
                                         {
                                           "name": "Summary",
                                           "amount": 100
                                         }
                                       ]
                                     }
                                   },
                                   "status": "success",
                                   "statusCode": "01",
                                   "message": "Created order successfully"
                                 }
                            """)));
        CreateOrderRequest.Customer customer = new CreateOrderRequest.Customer("jones@gmail.com", "09067985861", "James", "Jones", "GB");
        CreateOrderRequest.Order order = new CreateOrderRequest.Order();
        order.setAmount(100.0);
        order.setCurrency("USD");
        order.setDescription("Pay");
        order.setReference("805551685");
        CreateOrderRequest.Payment payment = new CreateOrderRequest.Payment();
        payment.setFrequencyId(2);
        payment.setPaymentlinkid(2);
        payment.setRedirectUrl("https://example.com/redirect");
        payment.setNumberOfPayments(2);

        CreateOrderRequest request = CreateOrderRequest.builder()
                .customer(customer)
                .order(order)
                .payment(payment)
                .paymentMeta( Map.of("ipAddress", "127.0.0.1") )
                .build();

        CreateOrderResponse response = client.orders().create(request);

        assertThat(response.getStatus()).isEqualTo("success");
        assertThat(response.getData()).isNotNull();
        assertThat(response.getData().getOrder().getReference()).isEqualTo("805551685");
        assertThat(response.getData().getOrder().getProcessorReference()).isEqualTo("EPCLB-3134A6BDF47211EF93AB06BA2661E92B");
        assertThat(response.getData().getOrder().getAmount()).isEqualTo(100.0);

        verify(postRequestedFor(urlEqualTo("/checkout/order/create"))
                .withRequestBody(matching(".*\"data\"\\s*:\\s*\"[A-Za-z0-9+/=]+\".*")));
    }

    @Test
    void fee_shouldCalculateFee() {
        stubFor(post(urlEqualTo("/checkout/order/fee"))
                .withHeader("api-key", equalTo("test-api-key"))
                .withHeader("Content-Type", equalTo("application/json"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody("""
                                {
                                   "data": {
                                     "fee": 6,
                                     "amount": 100,
                                     "subsidiaryFee": 0,
                                     "customerFee": 6,
                                     "totalChargedAmount": 106,
                                     "paymentOption": "C"
                                   },
                                   "status": "success",
                                   "statusCode": "00",
                                   "message": "Operation successful"
                                 }
                            """)));

        FeeRequest request = FeeRequest.builder()
                .amount(100.0)
                .paymentMethod("C")
                .build();

        FeeResponse response = client.orders().fee(request);

        assertThat(response.getStatus()).isEqualTo("success");
        assertThat(response.getData().getFee()).isEqualTo(6.0);
        assertThat(response.getData().getTotalChargedAmount()).isEqualTo(106.0);
    }

    @Test
    void status_shouldReturnOrderStatus() {
        stubFor(post(urlEqualTo("/checkout/order/status"))
                .withHeader("api-key", equalTo("test-api-key"))
                .withHeader("Content-Type", equalTo("application/json"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody("""
                                {
                                   "is_final_status": true,
                                   "requery_needed": false,
                                   "requery_type": null,
                                   "data": {
                                     "payment_reference": "PARORD-E469DB31-3F33-4FD2-8365-73B9F1E08709",
                                     "order_reference": "ORD120993456ffn7777890",
                                     "product_id": 1,
                                     "subsidiary_id": 1,
                                     "wallet_id": 1,
                                     "customer_id": 1,
                                     "total_charged_amount": 5800,
                                     "payment_status": 4,
                                     "currency_id": 1,
                                     "fee": 800,
                                     "subsidiary_fee": null,
                                     "customer_fee": null,
                                     "payment_type": "1",
                                     "payment_response_code": "04",
                                     "payment_response_message": "Account number has expired",
                                     "provider_response_date": null,
                                     "date_payment_confirmed": "2021-05-09T12:05:00.413",
                                     "narration": "Test Payment",
                                     "remarks": "Account number has expired",
                                     "parent_transaction_id": null,
                                     "id": 163,
                                     "created_by": 1,
                                     "updated_by": -1,
                                     "deleted_by": null,
                                     "date_created": "2021-05-09T11:17:38.177",
                                     "date_updated": "2021-05-09T16:23:12.953",
                                     "date_deleted": null
                                   },
                                   "status": "success",
                                   "status_code": "00",
                                   "message": "Order Status fetched successfully"
                                 }
                            """)));

        OrderStatusRequest request = new OrderStatusRequest("ORD120993456ffn7777890");

        OrderStatusResponse response = client.orders().status(request);

        assertThat(response.getData().getOrderReference()).isEqualTo("order123");
        assertThat(response.isFinalStatus()).isEqualTo(true);
    }

    @Test
    void verify_shouldVerifyOrder() {
        stubFor(post(urlEqualTo("/checkout/order/verify"))
                .withHeader("api-key", equalTo("test-api-key"))
                .withHeader("Content-Type", equalTo("application/json"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody("""
                                {
                                   "data": {
                                     "orderReference": "805551685",
                                     "paymentReference": "EPCLB-3134A6BDF47211EF93AB06BA2661E92B",
                                     "productName": "Collection",
                                     "totalAmountCharged": 106,
                                     "statusId": 4,
                                     "status": "Failed",
                                     "paymentMethod": "Card Payment",
                                     "paymentResponseCode": "12",
                                     "paymentResponseMessage": "Transaction failed: Card transaction blocked due to change in the credit card details from the registered one",
                                     "narration": "Pay",
                                     "remarks": "Order initiated and created successfully",
                                     "currencyId": 6,
                                     "paymentLinkId": null,
                                     "paymentLinkReference": null,
                                     "recurringPaymentId": null,
                                     "recurringPaymentReference": null,
                                     "currencyName": "USD",
                                     "fee": 6,
                                     "feeRate": 6,
                                     "subsidiaryFee": 0,
                                     "customerFee": 6,
                                     "dateCreated": "2025-02-26T18:47:56",
                                     "dateUpdated": "2025-02-26T19:05:35.717496",
                                     "datePaymentConfirmed": null,
                                     "orderPayments": [
                                       {
                                         "orderId": 29,
                                         "orderPaymentReference": "PGW-PAYREF-3B315861F627474FA83852DBDD6CE71A",
                                         "paymentOptionId": 2,
                                         "paymentOption": "Card Payment",
                                         "statusId": 4,
                                         "status": "Failed",
                                         "responseCode": "12",
                                         "responseMessage": "Transaction failed: Card transaction blocked due to change in the credit card details from the registered one",
                                         "orderPaymentInstrument": null,
                                         "remarks": "Order payment initiated",
                                         "dateCreated": "2025-02-26T19:05:21.723112",
                                         "dateUpdated": "2025-02-26T19:05:35.717595"
                                       }
                                     ],
                                     "customer": {
                                       "customerId": null,
                                       "firstName": null,
                                       "lastName": null,
                                       "emailAddress": null,
                                       "countryShortName": null,
                                       "customerGroup": null,
                                       "countryId": 0,
                                       "globalStatusId": 0,
                                       "globalStatus": null,
                                       "mobileNumber": null,
                                       "isBlacklisted": false,
                                       "reasonBlacklisted": null,
                                       "dateCreated": "0001-01-01T00:00:00",
                                       "dateUpdated": null
                                     },
                                     "cardDetails": [
                                       {
                                         "orderPaymentId": 19,
                                         "status": true,
                                         "country": null,
                                         "cardToken": null,
                                         "cardExpiryMonth": null,
                                         "cardExpiryYear": null,
                                         "cardType": null,
                                         "cardIssuer": null,
                                         "cardFirstSixDigits": null,
                                         "cardLastFourDigits": null,
                                         "dateCreated": "2025-02-26T19:05:35.729312",
                                         "appEnvironmentId": 1
                                       }
                                     ],
                                     "paymentLink": null
                                   },
                                   "status": "success",
                                   "statusCode": "00",
                                   "message": "Order details fetched successfully"
                                 }
                            """)));

        VerifyOrderRequest request = new VerifyOrderRequest("order456");

        VerifyOrderResponse response = client.orders().verify(request);

        assertThat(response.getData().getVerified()).isTrue();
        assertThat(response.getData().getPaymentStatus()).isEqualTo("paid");
    }

    @Test
    void timeline_shouldReturnOrderTimeline() {
        stubFor(post(urlEqualTo("/checkout/order/event/track"))
                .withHeader("api-key", equalTo("test-api-key"))
                .withHeader("Content-Type", equalTo("application/json"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody("""
                                {
                                   "data": null,
                                   "status": "success",
                                   "statusCode": "00",
                                   "message": "Event logged successfully"
                                 }
                            """)));

        OrderTimelineRequest request = new OrderTimelineRequest();
        request.setOrderReference("order123");

        OrderTimelineResponse response = client.orders().timeline(request);

        assertThat(response.getData()).isEqualTo(null);
    }

    @Test
    void pay_shouldProcessPayment() {
        stubFor(post(urlEqualTo("/checkout/order/pay"))
                .withHeader("api-key", equalTo("test-api-key"))
                .withHeader("Content-Type", equalTo("application/json"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody("""
                                {
                                   "data": {
                                     "paymentDetail": {
                                       "website": "https://core.devepayclub.com/blktk-kpay/api/v1/card/initiatetransaction?tx1=NUJHIK51459855174059672277413231740596722774&t2=fd83e64b11ed12225bf69655d40dc3b55b28dfca784bcd8c3d46af0078e8eaa2",
                                       "recipientAccount": null,
                                       "paymentReference": "CP370D7116-5DDA-4BDE-81E3-5155DBB0AB48"
                                     },
                                     "bankTransferDetails": null,
                                     "orderPayment": {
                                       "orderId": 29,
                                       "orderPaymentReference": "PGW-PAYREF-3B315861F627474FA83852DBDD6CE71A",
                                       "currency": "USD",
                                       "statusId": 2,
                                       "orderPaymentResponseCode": "02",
                                       "orderPaymentResponseMessage": "pending-authenticaion",
                                       "orderPaymentInstrument": null,
                                       "remarks": "Order payment initiated",
                                       "totalAmount": 106,
                                       "fee": 6
                                     }
                                   },
                                   "status": "success",
                                   "statusCode": "02",
                                   "message": "pending-authenticaion"
                                 }
                            """)));

        PayOrderRequest.BillingAddress billingAddress = new PayOrderRequest.BillingAddress();
        billingAddress.setCity("Victoria Garden City");
        billingAddress.setCountry("NG");
        billingAddress.setStreet("Lagos Street");
        billingAddress.setZipcode("101254");
        billingAddress.setState("Lagos");
        PayOrderRequest.Card card = new PayOrderRequest.Card();
        card.setCardNumber("4242424242424242");
        card.setExpiryMonth("12");
        card.setExpiryYear("25");
        card.setCvv("123");
        card.setBillingAddress(billingAddress);

        PayOrderRequest request = PayOrderRequest.builder()
                .reference("order123")
                .country("NG")
                .paymentMethod("C")
                .card(card)
                .build();

        PayOrderResponse response = client.orders().pay(request);

        assertThat(response.getData().getTransactionId()).isEqualTo("txn456");
        assertThat(response.getData().getPaymentStatus()).isEqualTo("processing");
    }
}
