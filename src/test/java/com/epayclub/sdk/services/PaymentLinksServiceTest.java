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
        stubFor(post(urlEqualTo("/checkout/links/create"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody("""
                                {
                                   "data": {
                                     "paymentLink": {
                                       "id": 133,
                                       "name": "Checkout TestAB",
                                       "paymentType": null,
                                       "logo": "https://merchant-api-service.devepayclub.com/subsidiary/dashboard/file/epayclub-compliance-images/download?fileId=",
                                       "amount": null,
                                       "dateCreated": "2025-04-15T00:16:33.984405",
                                       "reference": "vxwHip7xXm4xtPUtg0ZzDv$0noEby6MIPxG74O9nm4v3b1tby9mnErxfSOY_AZ0412",
                                       "createdBy": null,
                                       "creatorEmail": null,
                                       "isActive": true,
                                       "currency": "USD",
                                       "limit": 1,
                                       "paymentLinkUrl": "https://payment-link.devepayclub.com/vxwHip7xXm4xtPUtg0ZzDv$0noEby6MIPxG74O9nm4v3b1tby9mnErxfSOY_AZ0412",
                                       "appEnvironmentId": 1,
                                       "paymentLinkType": "Single Charge",
                                       "paymentLinkCode": "SC",
                                       "description": "A Demo to understand how payment links work."
                                     },
                                     "subsidiary": {
                                       "id": 1,
                                       "name": "Merchant Epayclub",
                                       "country": "NG",
                                       "supportEmail": "merchant@epayclub.com",
                                       "customization": null
                                     }
                                   },
                                   "status": "success",
                                   "statusCode": "00",
                                   "message": "Payment details fetched successfully"
                                 }
                            """)));

        CreateLinkRequest request = CreateLinkRequest.builder()
                .name("Checkout TestAB")
                .backgroundImage("https://merchant-api-service.devepayclub.com/subsidiary/dashboard/file/epayclub-compliance-images/download?fileId=")
                .paymentType("SC")
                .currency("USD")
                .authOption("NOAUTH")
                .amount(1200.0)
                .description("A Demo to understand how payment links work.")
                .build();

        CreateLinkResponse response = client.paymentLinks().create(request);

        // updated to reflect nested paymentLink
        assertThat(response.getData().getPaymentLink()).isNotNull();
        assertThat(response.getData().getPaymentLink().getCurrency()).isEqualTo("USD");
        assertThat(response.getData().getPaymentLink().getIsActive()).isTrue();
    }

    @Test
    void list_shouldFetchPaymentLinks() {
        stubFor(get(urlPathEqualTo("/checkout/links/all"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody("""
                                [
                                     {
                                         "id": 1,
                                         "name": "Merchant Epayclub default payment link test",
                                         "paymentType": "Default",
                                         "logo": null,
                                         "amount": null,
                                         "dateCreated": "2025-02-13T02:36:42.449569",
                                         "reference": "mJKmguySpGQI5LAT14j4T12",
                                         "createdBy": null,
                                         "creatorEmail": null,
                                         "isActive": true,
                                         "currency": "NGN",
                                         "limit": null,
                                         "paymentLinkUrl": "https://payment-link.devepayclub.com/mJKmguySpGQI5LAT14j4T12",
                                         "appEnvironmentId": 0,
                                         "paymentLinkType": null,
                                         "paymentLinkCode": null,
                                         "description": null
                                     },
                                     {
                                         "id": 108,
                                         "name": "John Doe",
                                         "paymentType": "Single Charge",
                                         "logo": null,
                                         "amount": 500.000000,
                                         "dateCreated": "2025-04-11T04:00:54.181869",
                                         "reference": "NbiV15Lgx5iUrdX7WaiTDiU$GxOpw$hd4RlEud$FrdWKfy6CeRM12",
                                         "createdBy": null,
                                         "creatorEmail": null,
                                         "isActive": true,
                                         "currency": "KES",
                                         "limit": 1,
                                         "paymentLinkUrl": "https://payment-link.devepayclub.com/NbiV15Lgx5iUrdX7WaiTDiU$GxOpw$hd4RlEud$FrdWKfy6CeRM12",
                                         "appEnvironmentId": 0,
                                         "paymentLinkType": null,
                                         "paymentLinkCode": null,
                                         "description": "Cake order payment"
                                     }
                                 ]
                            """)));

        FetchLinksRequest request = FetchLinksRequest.builder()
                .id(108)
                .build();

        FetchLinksResponse response = client.paymentLinks().list(request);

        assertThat(response.getData()).hasSize(2);
        assertThat(response.getPagination().getTotal()).isEqualTo(2);
    }

    @Test
    void types_shouldFetchLinkTypes() {
        stubFor(get(urlEqualTo("/checkout/links/types"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody("""
                                {
                                   "paymentLinkTypes": [
                                     {
                                       "paymentLinkName": "Single Charge",
                                       "description": "For single charge tokens",
                                       "status": true,
                                       "code": "SC",
                                       "id": 1,
                                       "dateCreated": "2023-07-06T08:48:45",
                                       "dateUpdated": null,
                                       "dateDeleted": null,
                                       "createdBy": -1,
                                       "updatedBy": null,
                                       "deletedBy": null
                                     },
                                     {
                                       "paymentLinkName": "Multiple Charge",
                                       "description": "For subscriptions",
                                       "status": true,
                                       "code": "MC",
                                       "id": 2,
                                       "dateCreated": "2023-07-06T08:48:45",
                                       "dateUpdated": null,
                                       "dateDeleted": null,
                                       "createdBy": -1,
                                       "updatedBy": null,
                                       "deletedBy": null
                                     },
                                     {
                                       "paymentLinkName": "Subscription Payments",
                                       "description": "Subscription Payments",
                                       "status": true,
                                       "code": "SUB",
                                       "id": 5,
                                       "dateCreated": "2023-07-06T08:48:45",
                                       "dateUpdated": null,
                                       "dateDeleted": null,
                                       "createdBy": -1,
                                       "updatedBy": null,
                                       "deletedBy": null
                                     }
                                   ],
                                   "status": "success",
                                   "statusCode": "00",
                                   "message": "Payment link types retrieved successfully"
                                 }
                            """)));

        LinkTypesResponse response = client.paymentLinks().types();

        assertThat(response.getPaymentLinkTypes()).hasSize(3);
        assertThat(response.getPaymentLinkTypes().get(0).getCode()).isEqualTo("SC");
    }

    @Test
    void edit_shouldEditPaymentLink() {
        stubFor(patch(urlEqualTo("/checkout/links/125/edit"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody("""
                                {
                                     "data": {
                                         "paymentLink": {
                                             "id": 125,
                                             "name": "Checkout TestA",
                                             "paymentType": null,
                                             "logo": "",
                                             "amount": null,
                                             "dateCreated": "2025-04-14T01:03:38.532696",
                                             "reference": "cONEmanSJGVvUaGI5Vq31qtaLDt1RR7OGMMgNNcZxZgEWBZLAiW3u12",
                                             "createdBy": null,
                                             "creatorEmail": null,
                                             "isActive": true,
                                             "currency": "NGN",
                                             "limit": null,
                                             "paymentLinkUrl": "cONEmanSJGVvUaGI5Vq31qtaLDt1RR7OGMMgNNcZxZgEWBZLAiW3u12",
                                             "appEnvironmentId": 1,
                                             "paymentLinkType": "Subscription Payments",
                                             "paymentLinkCode": "SUB",
                                             "description": "A Demo to understand how payment links work."
                                         },
                                         "subsidiary": {
                                             "id": 1,
                                             "name": "Merchant Epayclub",
                                             "country": "NG",
                                             "supportEmail": "merchant@epayclub.com",
                                             "customization": null
                                         }
                                     },
                                     "status": "success",
                                     "statusCode": "00",
                                     "message": "Payment details fetched successfully"
                                 }
                            """)));

        EditLinkRequest request = EditLinkRequest.builder()
                .id("125")
                .description("A Demo to understand how payment links work.")
                .name("Updated Product")
                .amount("7500")
                .website("https://merchant.example.com/product")
                .mobile("07023232232")
                .backgroundImage("https://merchant.example.com/images/bg.png")
                .paymentType("MC")
                .authOption("NOAUTH")
                .limit("3")
                .build();

        EditLinkResponse response = client.paymentLinks().edit(request);

        // adapt to nested paymentLink.
        assertThat(response.getData().getPaymentLink().getName()).isEqualTo("Checkout TestA");
        assertThat(response.getStatus()).isEqualTo("success");
    }

    @Test
    void activate_shouldActivateLink() {
        stubFor(patch(urlEqualTo("/checkout/links/link123/status/activate"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody("""
                                {
                                     "data": null,
                                     "status": "success",
                                     "statusCode": "00",
                                     "message": "Payment link status updated successfully"
                                 }
                            """)));

        ActivateLinkRequest request = new ActivateLinkRequest("link123");

        ActivateLinkResponse response = client.paymentLinks().activate(request);

        assertThat(response.getData()).isEqualTo(null);
    }

    @Test
    void deactivate_shouldDeactivateLink() {
        stubFor(patch(urlEqualTo("/checkout/links/link123/status/disable"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody("""
                            {
                                 "data": null,
                                 "status": "success",
                                 "statusCode": "00",
                                 "message": "Payment link status updated successfully"
                             }
                            """)));

        DeactivateLinkRequest request = new DeactivateLinkRequest("link123");

        DeactivateLinkResponse response = client.paymentLinks().deactivate(request);

        assertThat(response.getData()).isEqualTo(null);
    }

    @Test
    void frequencies_shouldFetchFrequencies() {
        stubFor(get(urlEqualTo("/checkout/frequencies"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody("""
                                {
                                    "data": [
                                        {
                                            "hours": 1,
                                            "days": 0,
                                            "name": "Hourly",
                                            "description": "Payment made hourly",
                                            "isActive": null,
                                            "id": 1,
                                            "dateCreated": "2023-07-06T08:48:45",
                                            "dateUpdated": null,
                                            "dateDeleted": null,
                                            "createdBy": -1,
                                            "updatedBy": null,
                                            "deletedBy": null
                                        },
                                        {
                                            "hours": 24,
                                            "days": 1,
                                            "name": "Daily",
                                            "description": "Daily",
                                            "isActive": null,
                                            "id": 2,
                                            "dateCreated": "2023-07-06T08:48:45",
                                            "dateUpdated": null,
                                            "dateDeleted": null,
                                            "createdBy": -1,
                                            "updatedBy": null,
                                            "deletedBy": null
                                        }
                                    ],
                                    "status": "success",
                                    "statusCode": "00",
                                    "message": "Operation successful"
                                }
                            """)));

        FrequenciesResponse response = client.paymentLinks().frequencies();

        assertThat(response.getData()).hasSize(2);
        assertThat(response.getData().get(0).getName()).isEqualTo("Hourly");
    }

    @Test
    void cancelRecurringPayments_shouldCancelRecurring() {
        stubFor(patch(urlEqualTo("/checkout/links/recurringpayment/link123/cancel"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody("""
                            {
                                "data": null,
                                "status": "success",
                                "statusCode": "00",
                                "message": "Recurring payment cancelled successfully"
                            }
                            """)));

        CancelRecurringPaymentsRequest request = CancelRecurringPaymentsRequest.builder()
                .id("link123")
                .build();

        CancelRecurringPaymentsResponse response = client.paymentLinks().cancelRecurringPayments(request);

        assertThat(response.getStatus()).isEqualTo("success");
    }
}
