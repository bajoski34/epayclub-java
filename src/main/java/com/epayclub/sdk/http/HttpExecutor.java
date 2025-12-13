package com.epayclub.sdk.http;

import com.epayclub.sdk.crypto.Encryption;
import com.epayclub.sdk.errors.EpayClubApiException;
import com.epayclub.sdk.errors.EpayClubClientException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Set;

/**
 * Central HTTP executor for the EpayClub SDK.
 * Handles request execution, retries, and error handling.
 */
public class HttpExecutor {

    private static final Set<Integer> RETRYABLE_STATUS_CODES = Set.of(408, 429, 500, 502, 503, 504);
    private static final int MAX_BACKOFF_MS = 30000;
    private static final Random RANDOM = new Random();

    private final HttpClient httpClient;
    private final ObjectMapper objectMapper;
    private final String baseUrl;
    private final String apiKey;
    private final String merchantEncryptionKey;
    private final Duration defaultTimeout;
    private final int defaultRetries;
    private final String userAgent;

    /**
     * Constructs a new HttpExecutor.
     *
     * @param baseUrl               the base URL for API requests
     * @param apiKey                the API key for authentication
     * @param merchantEncryptionKey the merchant encryption key (may be null)
     * @param timeout               the default timeout for requests
     * @param retries               the default number of retries
     * @param userAgent             the User-Agent header value
     */
    public HttpExecutor(String baseUrl, String apiKey, String merchantEncryptionKey,
                        Duration timeout, int retries, String userAgent) {
        this.baseUrl = baseUrl.endsWith("/") ? baseUrl.substring(0, baseUrl.length() - 1) : baseUrl;
        this.apiKey = apiKey;
        this.merchantEncryptionKey = merchantEncryptionKey;
        this.defaultTimeout = timeout;
        this.defaultRetries = retries;
        this.userAgent = userAgent;

        this.httpClient = HttpClient.newBuilder()
                .connectTimeout(timeout)
                .build();

        this.objectMapper = new ObjectMapper()
                .registerModule(new JavaTimeModule())
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
    }

    /**
     * Returns the ObjectMapper for JSON serialization/deserialization.
     *
     * @return the ObjectMapper
     */
    public ObjectMapper getObjectMapper() {
        return objectMapper;
    }

    /**
     * Executes a GET request.
     *
     * @param path         the API path
     * @param responseType the response type class
     * @param options      request options
     * @param <T>          the response type
     * @return the deserialized response
     */
    public <T> T get(String path, Class<T> responseType, RequestOptions options) {
        return executeWithRetries("GET", path, null, responseType, options, true);
    }

    /**
     * Executes a POST request.
     *
     * @param path         the API path
     * @param body         the request body
     * @param responseType the response type class
     * @param options      request options
     * @param <T>          the response type
     * @return the deserialized response
     */
    public <T> T post(String path, Object body, Class<T> responseType, RequestOptions options) {
        return executeWithRetries("POST", path, body, responseType, options, false);
    }

    /**
     * Executes a PATCH request.
     *
     * @param path         the API path
     * @param body         the request body
     * @param responseType the response type class
     * @param options      request options
     * @param <T>          the response type
     * @return the deserialized response
     */
    public <T> T patch(String path, Object body, Class<T> responseType, RequestOptions options) {
        return executeWithRetries("PATCH", path, body, responseType, options, false);
    }

    /**
     * Executes a PUT request.
     *
     * @param path         the API path
     * @param body         the request body
     * @param responseType the response type class
     * @param options      request options
     * @param <T>          the response type
     * @return the deserialized response
     */
    public <T> T put(String path, Object body, Class<T> responseType, RequestOptions options) {
        return executeWithRetries("PUT", path, body, responseType, options, false);
    }

    /**
     * Executes a DELETE request.
     *
     * @param path         the API path
     * @param responseType the response type class
     * @param options      request options
     * @param <T>          the response type
     * @return the deserialized response
     */
    public <T> T delete(String path, Class<T> responseType, RequestOptions options) {
        return executeWithRetries("DELETE", path, null, responseType, options, true);
    }

    private <T> T executeWithRetries(String method, String path, Object body,
                                      Class<T> responseType, RequestOptions options, boolean retryable) {
        int maxRetries = options != null && options.getRetries() != null ? options.getRetries() : defaultRetries;
        int attempt = 0;
        Exception lastException = null;

        while (attempt <= maxRetries) {
            try {
                return executeRequest(method, path, body, responseType, options);
            } catch (EpayClubApiException e) {
                lastException = e;
                if (!retryable || !isRetryableStatus(e.getHttpStatus())) {
                    throw e;
                }
                attempt++;
                if (attempt <= maxRetries) {
                    sleep(calculateBackoff(attempt));
                }
            } catch (IOException | InterruptedException e) {
                lastException = e;
                if (!retryable) {
                    throw new EpayClubClientException("Request failed: " + e.getMessage(), e);
                }
                attempt++;
                if (attempt <= maxRetries) {
                    sleep(calculateBackoff(attempt));
                }
            }
        }

        if (lastException instanceof EpayClubApiException) {
            throw (EpayClubApiException) lastException;
        }
        throw new EpayClubClientException("Request failed after " + maxRetries + " retries", lastException);
    }

    private <T> T executeRequest(String method, String path, Object body,
                                  Class<T> responseType, RequestOptions options)
            throws IOException, InterruptedException {
        String url = baseUrl + path;
        String requestBody = null;

        if (body != null) {
            try {
                if (options != null && options.isEncrypt()) {
                    if (merchantEncryptionKey == null || merchantEncryptionKey.isBlank()) {
                        throw EpayClubClientException.missingEncryptionKey();
                    }
                    String jsonPayload = objectMapper.writeValueAsString(body);
                    String encryptedData = Encryption.encryptPayload(merchantEncryptionKey, jsonPayload);
                    Map<String, String> encryptedBody = new HashMap<>();
                    encryptedBody.put("data", encryptedData);
                    requestBody = objectMapper.writeValueAsString(encryptedBody);
                } else {
                    requestBody = objectMapper.writeValueAsString(body);
                }
            } catch (JsonProcessingException e) {
                throw EpayClubClientException.serializationError(e);
            }
        }

        Duration timeout = options != null && options.getTimeoutSeconds() != null
                ? Duration.ofSeconds(options.getTimeoutSeconds())
                : defaultTimeout;

        HttpRequest.Builder requestBuilder = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .timeout(timeout)
                .header("Content-Type", "application/json")
                .header("api-key", apiKey)
                .header("User-Agent", userAgent);

        HttpRequest.BodyPublisher bodyPublisher = requestBody != null
                ? HttpRequest.BodyPublishers.ofString(requestBody)
                : HttpRequest.BodyPublishers.noBody();

        switch (method.toUpperCase()) {
            case "GET" -> requestBuilder.GET();
            case "POST" -> requestBuilder.POST(bodyPublisher);
            case "PUT" -> requestBuilder.PUT(bodyPublisher);
            case "DELETE" -> requestBuilder.DELETE();
            case "PATCH" -> requestBuilder.method("PATCH", bodyPublisher);
            default -> throw new EpayClubClientException("Unsupported HTTP method: " + method);
        }

        HttpResponse<String> response = httpClient.send(requestBuilder.build(),
                HttpResponse.BodyHandlers.ofString());

        return handleResponse(response, method, path, responseType);
    }

    private <T> T handleResponse(HttpResponse<String> response, String method, String path,
                                  Class<T> responseType) {
        String rawBody = response.body();
        int statusCode = response.statusCode();
        Map<String, Object> parsedBody = null;

        // Try to parse JSON
        if (rawBody != null && !rawBody.isBlank()) {
            try {
                parsedBody = objectMapper.readValue(rawBody, new TypeReference<>() {});
            } catch (JsonProcessingException e) {
                // Not valid JSON, that's okay
            }
        }

        // Check for errors
        if (statusCode >= 400) {
            throw EpayClubApiException.fromResponse(statusCode, method, path, rawBody, parsedBody);
        }

        // Handle empty or null response
        if (rawBody == null || rawBody.isBlank()) {
            if (responseType == Void.class) {
                return null;
            }
            throw new EpayClubClientException("Expected response body but received empty response");
        }

        // Deserialize response
        try {
            return objectMapper.readValue(rawBody, responseType);
        } catch (JsonProcessingException e) {
            throw EpayClubClientException.deserializationError(e);
        }
    }

    private boolean isRetryableStatus(Integer status) {
        return status != null && RETRYABLE_STATUS_CODES.contains(status);
    }

    private long calculateBackoff(int attempt) {
        // Exponential backoff with jitter
        long baseBackoff = (long) Math.pow(2, attempt) * 100;
        long jitter = RANDOM.nextLong(baseBackoff / 2);
        return Math.min(baseBackoff + jitter, MAX_BACKOFF_MS);
    }

    private void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
