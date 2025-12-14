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
                .description("A Demo to understand how payment links work.")
                .build();

        CreateLinkResponse response = client.paymentLinks().create(request);

        // updated to reflect nested paymentLink
        assertThat(response.getData().getPaymentLink()).isNotNull();
        assertThat(response.getData().getPaymentLink().getAmount()).isEqualTo(5000.0);
        assertThat(response.getData().getPaymentLink().getIsActive()).isTrue();
    }

    @Test
    void list_shouldFetchPaymentLinks() {
        stubFor(get(urlPathEqualTo("/checkout/links/all"))
                .withQueryParam("page", equalTo("1"))
                .withQueryParam("limit", equalTo("10"))
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
                .page(1)
                .limit(10)
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

        assertThat(response.getData()).hasSize(2);
        assertThat(response.getData().get(0).getType()).isEqualTo("one_time");
    }

    @Test
    void edit_shouldEditPaymentLink() {
        stubFor(patch(urlEqualTo("/checkout/links/{id}/edit"))
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
                .linkId("link123")
                .name("Updated Product")
                .amount(7500.0)
                .build();

        EditLinkResponse response = client.paymentLinks().edit(request);

        // adapt to nested paymentLink
        assertThat(response.getData().getPaymentLink().getName()).isEqualTo("Updated Product");
        assertThat(response.getData().getPaymentLink().getAmount()).isEqualTo(7500.0);
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

        assertThat(response.getData().getPaymentLink().getIsActive()).isTrue();
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

        assertThat(response.getData().getPaymentLink().getIsActive()).isFalse();
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
                                        "mobile": "daily",
                                        "name": "Daily",
                                        "description": "Once per day"
                                    },
                                    {
                                        "mobile": "weekly",
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
