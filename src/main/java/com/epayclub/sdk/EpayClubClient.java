package com.epayclub.sdk;

import com.epayclub.sdk.errors.EpayClubClientException;
import com.epayclub.sdk.http.HttpExecutor;
import com.epayclub.sdk.services.OrdersService;
import com.epayclub.sdk.services.PaymentLinksService;
import com.epayclub.sdk.services.PaymentOperationsService;

import java.time.Duration;

/**
 * Main client for the EpayClub API.
 * 
 * <p>This is the entry point for interacting with the EpayClub payment platform.
 * Create an instance using the builder pattern and then access the various
 * service endpoints through the resource group methods.</p>
 * 
 * <h2>Example Usage:</h2>
 * <pre>{@code
 * EpayClubClient client = EpayClubClient.builder()
 *     .apiKey("your-api-key")
 *     .build();
 * 
 * // Create an order
 * CreateOrderResponse response = client.orders().create(
 *     CreateOrderRequest.builder()
 *         .amount(1000.0)
 *         .currency("NGN")
 *         .email("customer@example.com")
 *         .build()
 * );
 * }</pre>
 * 
 * @see OrdersService
 * @see PaymentLinksService
 * @see PaymentOperationsService
 */
public class EpayClubClient {

    /**
     * Default base URL for the EpayClub API.
     */
    public static final String DEFAULT_BASE_URL = "https://checkout-api-service.epayclub.com";

    /**
     * Default request timeout duration.
     */
    public static final Duration DEFAULT_TIMEOUT = Duration.ofSeconds(30);

    /**
     * Default number of retries for transient failures.
     */
    public static final int DEFAULT_RETRIES = 3;

    /**
     * Default User-Agent header value.
     */
    public static final String DEFAULT_USER_AGENT = "epayclub-java/1.0.0";

    private final HttpExecutor httpExecutor;
    private final OrdersService ordersService;
    private final PaymentLinksService paymentLinksService;
    private final PaymentOperationsService paymentOperationsService;

    private EpayClubClient(Builder builder) {
        this.httpExecutor = new HttpExecutor(
                builder.baseUrl,
                builder.apiKey,
                builder.merchantEncryptionKey,
                builder.timeout,
                builder.retries,
                builder.userAgent
        );
        this.ordersService = new OrdersService(httpExecutor);
        this.paymentLinksService = new PaymentLinksService(httpExecutor);
        this.paymentOperationsService = new PaymentOperationsService(httpExecutor);
    }

    /**
     * Returns the Orders service for order-related operations.
     * 
     * <p>Use this service to create orders, process payments, check status, and verify orders.</p>
     *
     * @return the OrdersService instance
     */
    public OrdersService orders() {
        return ordersService;
    }

    /**
     * Returns the Payment Links service for payment link operations.
     * 
     * <p>Use this service to create, manage, and query payment links.</p>
     *
     * @return the PaymentLinksService instance
     */
    public PaymentLinksService paymentLinks() {
        return paymentLinksService;
    }

    /**
     * Returns the Payment Operations service for payment-related operations.
     * 
     * <p>Use this service to retrieve bank codes and other payment-related data.</p>
     *
     * @return the PaymentOperationsService instance
     */
    public PaymentOperationsService paymentOperations() {
        return paymentOperationsService;
    }

    /**
     * Creates a new builder for EpayClubClient.
     *
     * @return a new Builder instance
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Builder for constructing EpayClubClient instances.
     */
    public static class Builder {
        private String apiKey;
        private String baseUrl = DEFAULT_BASE_URL;
        private Duration timeout = DEFAULT_TIMEOUT;
        private int retries = DEFAULT_RETRIES;
        private String userAgent = DEFAULT_USER_AGENT;
        private String merchantEncryptionKey;

        /**
         * Sets the API key for authentication. This is required.
         *
         * @param apiKey the API key
         * @return this builder
         */
        public Builder apiKey(String apiKey) {
            this.apiKey = apiKey;
            return this;
        }

        /**
         * Sets the base URL for API requests.
         * Default: https://checkout-api-service.epayclub.com
         *
         * @param baseUrl the base URL
         * @return this builder
         */
        public Builder baseUrl(String baseUrl) {
            this.baseUrl = baseUrl;
            return this;
        }

        /**
         * Sets the request timeout duration.
         * Default: 30 seconds
         *
         * @param timeout the timeout duration
         * @return this builder
         */
        public Builder timeout(Duration timeout) {
            this.timeout = timeout;
            return this;
        }

        /**
         * Sets the number of retries for transient failures.
         * Default: 3
         *
         * @param retries the number of retries
         * @return this builder
         */
        public Builder retries(int retries) {
            this.retries = retries;
            return this;
        }

        /**
         * Sets the User-Agent header value.
         * Default: epayclub-java/1.0.0
         *
         * @param userAgent the User-Agent string
         * @return this builder
         */
        public Builder userAgent(String userAgent) {
            this.userAgent = userAgent;
            return this;
        }

        /**
         * Sets the merchant encryption key for encrypted endpoints.
         * 
         * <p>This key is required for endpoints that require payload encryption.
         * When set, you can use {@code RequestOptions.encrypt(true)} to encrypt
         * request payloads.</p>
         *
         * @param merchantEncryptionKey the base64-encoded encryption key
         * @return this builder
         */
        public Builder merchantEncryptionKey(String merchantEncryptionKey) {
            this.merchantEncryptionKey = merchantEncryptionKey;
            return this;
        }

        /**
         * Builds the EpayClubClient instance.
         *
         * @return the configured EpayClubClient
         * @throws EpayClubClientException if required parameters are missing
         */
        public EpayClubClient build() {
            if (apiKey == null || apiKey.isBlank()) {
                throw EpayClubClientException.requiredField("apiKey");
            }
            return new EpayClubClient(this);
        }
    }
}
