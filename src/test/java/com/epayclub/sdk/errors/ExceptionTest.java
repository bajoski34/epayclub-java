package com.epayclub.sdk.errors;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;

/**
 * Tests for exception classes.
 */
class ExceptionTest {

    @Test
    void epayClubException_shouldStoreAllFields() {
        Map<String, Object> parsedBody = new HashMap<>();
        parsedBody.put("message", "Test error");

        EpayClubException ex = new EpayClubException(
                "Test message",
                400,
                "error",
                "01",
                "ERR001",
                "POST",
                "/orders",
                "{\"message\":\"Test error\"}",
                parsedBody
        );

        assertThat(ex.getMessage()).isEqualTo("Test message");
        assertThat(ex.getHttpStatus()).isEqualTo(400);
        assertThat(ex.getStatus()).isEqualTo("error");
        assertThat(ex.getStatusId()).isEqualTo("01");
        assertThat(ex.getResponseCode()).isEqualTo("ERR001");
        assertThat(ex.getRequestMethod()).isEqualTo("POST");
        assertThat(ex.getRequestPath()).isEqualTo("/orders");
        assertThat(ex.getRawBody()).contains("Test error");
        assertThat(ex.getParsedBody()).containsEntry("message", "Test error");
    }

    @Test
    void epayClubException_shouldHandleSimpleConstructor() {
        EpayClubException ex = new EpayClubException("Simple error");

        assertThat(ex.getMessage()).isEqualTo("Simple error");
        assertThat(ex.getHttpStatus()).isNull();
        assertThat(ex.getStatus()).isNull();
    }

    @Test
    void epayClubException_shouldHandleCause() {
        RuntimeException cause = new RuntimeException("Root cause");
        EpayClubException ex = new EpayClubException("Wrapped error", cause);

        assertThat(ex.getMessage()).isEqualTo("Wrapped error");
        assertThat(ex.getCause()).isEqualTo(cause);
    }

    @Test
    void epayClubApiException_fromResponse_shouldParseFields() {
        Map<String, Object> parsedBody = new HashMap<>();
        parsedBody.put("status", "error");
        parsedBody.put("statusId", "01");
        parsedBody.put("responseCode", "ERR_INVALID");
        parsedBody.put("message", "Invalid request parameters");

        EpayClubApiException ex = EpayClubApiException.fromResponse(
                400, "POST", "/orders", "{...}", parsedBody
        );

        assertThat(ex.getHttpStatus()).isEqualTo(400);
        assertThat(ex.getStatus()).isEqualTo("error");
        assertThat(ex.getStatusId()).isEqualTo("01");
        assertThat(ex.getResponseCode()).isEqualTo("ERR_INVALID");
        assertThat(ex.getMessage()).isEqualTo("Invalid request parameters");
        assertThat(ex.getRequestMethod()).isEqualTo("POST");
        assertThat(ex.getRequestPath()).isEqualTo("/orders");
    }

    @Test
    void epayClubApiException_fromResponse_shouldHandleNullParsedBody() {
        EpayClubApiException ex = EpayClubApiException.fromResponse(
                500, "GET", "/test", "Raw error text", null
        );

        assertThat(ex.getHttpStatus()).isEqualTo(500);
        assertThat(ex.getMessage()).contains("API request failed with status 500");
        assertThat(ex.getStatus()).isNull();
    }

    @Test
    void epayClubClientException_requiredField_shouldFormatMessage() {
        EpayClubClientException ex = EpayClubClientException.requiredField("apiKey");

        assertThat(ex.getMessage()).contains("apiKey");
        assertThat(ex.getMessage()).contains("null or empty");
    }

    @Test
    void epayClubClientException_missingEncryptionKey_shouldFormatMessage() {
        EpayClubClientException ex = EpayClubClientException.missingEncryptionKey();

        assertThat(ex.getMessage()).contains("Encryption");
        assertThat(ex.getMessage()).contains("merchantEncryptionKey");
    }

    @Test
    void epayClubClientException_serializationError_shouldWrapCause() {
        RuntimeException cause = new RuntimeException("JSON error");
        EpayClubClientException ex = EpayClubClientException.serializationError(cause);

        assertThat(ex.getMessage()).contains("serialize");
        assertThat(ex.getCause()).isEqualTo(cause);
    }

    @Test
    void epayClubClientException_deserializationError_shouldWrapCause() {
        RuntimeException cause = new RuntimeException("Parse error");
        EpayClubClientException ex = EpayClubClientException.deserializationError(cause);

        assertThat(ex.getMessage()).contains("deserialize");
        assertThat(ex.getCause()).isEqualTo(cause);
    }
}
