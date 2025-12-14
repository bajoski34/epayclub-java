package com.epayclub.sdk.models.orders;

import com.epayclub.sdk.errors.EpayClubClientException;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Request model for checking order status.
 */
public class OrderStatusRequest {

    @JsonProperty("reference")
    private String reference;

    public OrderStatusRequest() {
    }

    public OrderStatusRequest(String reference) {
        this.reference = reference;
        validate();
    }

    /**
     * Validates the request has all required fields.
     */
    public void validate() {
        if (reference == null || reference.isBlank()) {
            throw EpayClubClientException.requiredField("orderId");
        }
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String reference;

        public Builder reference(String reference) {
            this.reference = reference;
            return this;
        }

        public OrderStatusRequest build() {
            OrderStatusRequest request = new OrderStatusRequest(reference);
            return request;
        }
    }
}
