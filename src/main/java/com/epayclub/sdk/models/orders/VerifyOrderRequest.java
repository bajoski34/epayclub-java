package com.epayclub.sdk.models.orders;

import com.epayclub.sdk.errors.EpayClubClientException;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Request model for verifying an order.
 */
public class VerifyOrderRequest {

    @JsonProperty("orderId")
    private String orderId;

    public VerifyOrderRequest() {
    }

    public VerifyOrderRequest(String orderId) {
        this.orderId = orderId;
        validate();
    }

    /**
     * Validates the request has all required fields.
     */
    public void validate() {
        if (orderId == null || orderId.isBlank()) {
            throw EpayClubClientException.requiredField("orderId");
        }
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String orderId;

        public Builder orderId(String orderId) {
            this.orderId = orderId;
            return this;
        }

        public VerifyOrderRequest build() {
            return new VerifyOrderRequest(orderId);
        }
    }
}
