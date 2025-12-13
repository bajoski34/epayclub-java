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
        stubFor(get(urlEqualTo("/bank-codes"))
                .withHeader("api-key", equalTo("test-api-key"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody("""
                            {
                                "status": "success",
                                "statusId": "00",
                                "responseCode": "00",
                                "message": "Bank codes retrieved successfully",
                                "data": [
                                    {
                                        "bankCode": "001",
                                        "bankName": "First Bank",
                                        "bankLogo": "https://example.com/firstbank.png",
                                        "active": true
                                    },
                                    {
                                        "bankCode": "002",
                                        "bankName": "GTBank",
                                        "bankLogo": "https://example.com/gtbank.png",
                                        "active": true
                                    },
                                    {
                                        "bankCode": "003",
                                        "bankName": "Access Bank",
                                        "bankLogo": "https://example.com/access.png",
                                        "active": false
                                    }
                                ]
                            }
                            """)));

        BankCodesResponse response = client.paymentOperations().bankCodes();

        assertThat(response.getStatus()).isEqualTo("success");
        assertThat(response.getData()).hasSize(3);
        
        assertThat(response.getData().get(0).getBankCode()).isEqualTo("001");
        assertThat(response.getData().get(0).getBankName()).isEqualTo("First Bank");
        assertThat(response.getData().get(0).getActive()).isTrue();
        
        assertThat(response.getData().get(2).getActive()).isFalse();
    }

    @Test
    void bankCodes_shouldIncludeCorrectHeaders() {
        stubFor(get(urlEqualTo("/bank-codes"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody("""
                            {
                                "status": "success",
                                "data": []
                            }
                            """)));

        client.paymentOperations().bankCodes();

        verify(getRequestedFor(urlEqualTo("/bank-codes"))
                .withHeader("api-key", equalTo("test-api-key"))
                .withHeader("Content-Type", equalTo("application/json")));
    }
}
