package com.epayclub.sdk.http;

/**
 * Request options for individual API calls.
 * Allows per-request customization of behavior.
 */
public class RequestOptions {

    private final boolean encrypt;
    private final Integer timeoutSeconds;
    private final Integer retries;

    private RequestOptions(Builder builder) {
        this.encrypt = builder.encrypt;
        this.timeoutSeconds = builder.timeoutSeconds;
        this.retries = builder.retries;
    }

    /**
     * Returns whether encryption should be applied to this request.
     *
     * @return true if encryption is enabled
     */
    public boolean isEncrypt() {
        return encrypt;
    }

    /**
     * Returns the timeout in seconds for this request, or null to use default.
     *
     * @return the timeout in seconds
     */
    public Integer getTimeoutSeconds() {
        return timeoutSeconds;
    }

    /**
     * Returns the number of retries for this request, or null to use default.
     *
     * @return the number of retries
     */
    public Integer getRetries() {
        return retries;
    }

    /**
     * Creates a new builder for RequestOptions.
     *
     * @return a new builder
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Creates a RequestOptions with encryption enabled.
     *
     * @param encrypt whether to encrypt the request
     * @return a new RequestOptions
     */
    public static RequestOptions encrypt(boolean encrypt) {
        return builder().encrypt(encrypt).build();
    }

    /**
     * Returns the default RequestOptions.
     *
     * @return default options
     */
    public static RequestOptions defaults() {
        return builder().build();
    }

    /**
     * Builder for RequestOptions.
     */
    public static class Builder {
        private boolean encrypt = false;
        private Integer timeoutSeconds = null;
        private Integer retries = null;

        /**
         * Sets whether encryption should be applied.
         *
         * @param encrypt true to enable encryption
         * @return this builder
         */
        public Builder encrypt(boolean encrypt) {
            this.encrypt = encrypt;
            return this;
        }

        /**
         * Sets the timeout for this request.
         *
         * @param seconds timeout in seconds
         * @return this builder
         */
        public Builder timeout(int seconds) {
            this.timeoutSeconds = seconds;
            return this;
        }

        /**
         * Sets the number of retries for this request.
         *
         * @param retries number of retries
         * @return this builder
         */
        public Builder retries(int retries) {
            this.retries = retries;
            return this;
        }

        /**
         * Builds the RequestOptions.
         *
         * @return a new RequestOptions
         */
        public RequestOptions build() {
            return new RequestOptions(this);
        }
    }
}
