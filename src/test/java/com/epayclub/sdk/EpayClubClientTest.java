package com.epayclub.sdk;

import com.epayclub.sdk.errors.EpayClubClientException;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static org.assertj.core.api.Assertions.*;

/**
 * Tests for EpayClubClient construction and configuration.
 */
class EpayClubClientTest {

    @Test
    void build_shouldThrowWhenApiKeyIsNull() {
        assertThatThrownBy(() -> EpayClubClient.builder().build())
                .isInstanceOf(EpayClubClientException.class)
                .hasMessageContaining("apiKey");
    }

    @Test
    void build_shouldThrowWhenApiKeyIsBlank() {
        assertThatThrownBy(() -> EpayClubClient.builder().apiKey("  ").build())
                .isInstanceOf(EpayClubClientException.class)
                .hasMessageContaining("apiKey");
    }

    @Test
    void build_shouldCreateClientWithValidApiKey() {
        EpayClubClient client = EpayClubClient.builder()
                .apiKey("test-api-key")
                .build();

        assertThat(client).isNotNull();
        assertThat(client.orders()).isNotNull();
        assertThat(client.paymentLinks()).isNotNull();
        assertThat(client.paymentOperations()).isNotNull();
    }

    @Test
    void build_shouldAcceptCustomConfiguration() {
        EpayClubClient client = EpayClubClient.builder()
                .apiKey("test-api-key")
                .baseUrl("https://custom.api.com")
                .timeout(Duration.ofSeconds(60))
                .retries(5)
                .userAgent("custom-agent/1.0")
                .merchantEncryptionKey("encrypted-key")
                .build();

        assertThat(client).isNotNull();
    }

    @Test
    void defaultValues_shouldBeSet() {
        assertThat(EpayClubClient.DEFAULT_BASE_URL)
                .isEqualTo("https://checkout-api-service.epayclub.com");
        assertThat(EpayClubClient.DEFAULT_TIMEOUT).isEqualTo(Duration.ofSeconds(30));
        assertThat(EpayClubClient.DEFAULT_RETRIES).isEqualTo(3);
        assertThat(EpayClubClient.DEFAULT_USER_AGENT).isEqualTo("epayclub-java/1.0.0");
    }
}
