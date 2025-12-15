package com.epayclub.sdk.models.paymentlinks;

import com.epayclub.sdk.errors.EpayClubClientException;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Request model for canceling recurring payments.
 */
public class CancelRecurringPaymentsRequest {

    @JsonProperty("id")
    private String id;

    public CancelRecurringPaymentsRequest() {
    }

    private CancelRecurringPaymentsRequest(Builder builder) {
        this.id = builder.id;
    }

    /**
     * Validates the request has all required fields.
     */
    public void validate() {
        if (id == null || id.isBlank()) {
            throw EpayClubClientException.requiredField("linkId");
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String id;

        public Builder id(String linkId) {
            this.id = linkId;
            return this;
        }

        public CancelRecurringPaymentsRequest build() {
            CancelRecurringPaymentsRequest request = new CancelRecurringPaymentsRequest(this);
            request.validate();
            return request;
        }
    }
}
