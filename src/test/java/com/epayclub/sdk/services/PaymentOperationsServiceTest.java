package com.epayclub.sdk.services;

import com.epayclub.sdk.EpayClubClient;
import com.epayclub.sdk.models.paymentoperations.BankCodesResponse;
import com.github.tomakehurst.wiremock.junit5.WireMockRuntimeInfo;
import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.assertj.core.api.Assertions.*;

/**
 * Integration tests for PaymentOperationsService using WireMock.
 */
@WireMockTest
class PaymentOperationsServiceTest {

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
    void bankCodes_shouldFetchBankCodes() {
        stubFor(get(urlEqualTo("/checkout/banks"))
                .withHeader("api-key", equalTo("test-api-key"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody("""
                            {
                              "status": "success",
                              "statusCode": "00",
                              "message": "Operation successful",
                              "data": [
                                {
                                  "name": "AB MICROFINANCE BANK",
                                  "countryId": 1,
                                  "bankCode": "090270",
                                  "isMicrofinance": null,
                                  "isMortgage": null,
                                  "ussdBankCode": null,
                                  "logo": "https://cloudfilesstore.blob.core.windows.net/icons/Banks/090270.png",
                                  "id": 1,
                                  "dateCreated": "2024-03-06T08:48:45",
                                  "dateUpdated": null,
                                  "dateDeleted": null,
                                  "createdBy": -1,
                                  "updatedBy": null,
                                  "deletedBy": null
                                },
                                {
                                  "name": "ABBEY MORTGAGE BANK",
                                  "countryId": 1,
                                  "bankCode": "070010",
                                  "isMicrofinance": null,
                                  "isMortgage": null,
                                  "ussdBankCode": null,
                                  "logo": "https://cloudfilesstore.blob.core.windows.net/icons/Banks/070010.png",
                                  "id": 2,
                                  "dateCreated": "2024-03-06T08:48:45",
                                  "dateUpdated": null,
                                  "dateDeleted": null,
                                  "createdBy": -1,
                                  "updatedBy": null,
                                  "deletedBy": null
                                },
                                {
                                  "name": "ABOVE ONLY MICROFINANCE BANK",
                                  "countryId": 1,
                                  "bankCode": "090260",
                                  "isMicrofinance": null,
                                  "isMortgage": null,
                                  "ussdBankCode": null,
                                  "logo": "https://cloudfilesstore.blob.core.windows.net/icons/Banks/090260.png",
                                  "id": 3,
                                  "dateCreated": "2024-03-06T08:48:45",
                                  "dateUpdated": null,
                                  "dateDeleted": null,
                                  "createdBy": -1,
                                  "updatedBy": null,
                                  "deletedBy": null
                                }
                              ]
                            }
                            """)));

        BankCodesResponse response = client.paymentOperations().bankCodes();

        assertThat(response.getStatus()).isEqualTo("success");
        assertThat(response.getData()).hasSize(3);

        assertThat(response.getData().get(0).getBankCode()).isEqualTo("090270");
        assertThat(response.getData().get(0).getName()).isEqualTo("AB MICROFINANCE BANK");
        assertThat(response.getData().get(0).getLogo()).contains("090270.png");
        assertThat(response.getData().get(0).getId()).isEqualTo(1);
        assertThat(response.getData().get(0).getDateCreated()).isEqualTo("2024-03-06T08:48:45");

        assertThat(response.getData().get(2).getBankCode()).isEqualTo("090260");
        assertThat(response.getData().get(2).getName()).isEqualTo("ABOVE ONLY MICROFINANCE BANK");
        assertThat(response.getData().get(2).getId()).isEqualTo(3);
    }

    @Test
    void bankCodes_shouldIncludeCorrectHeaders() {
        stubFor(get(urlEqualTo("/checkout/banks"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody("""
                                {
                                  "data": [
                                    {
                                      "name": "AB MICROFINANCE BANK",
                                      "countryId": 1,
                                      "bankCode": "090270",
                                      "isMicrofinance": null,
                                      "isMortgage": null,
                                      "ussdBankCode": null,
                                      "logo": "https://cloudfilesstore.blob.core.windows.net/icons/Banks/090270.png",
                                      "id": 1,
                                      "dateCreated": "2024-03-06T08:48:45",
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

        client.paymentOperations().bankCodes();

        verify(getRequestedFor(urlEqualTo("/checkout/banks"))
                .withHeader("api-key", equalTo("test-api-key"))
                .withHeader("Content-Type", equalTo("application/json")));
    }
}
