package com.epayclub.sdk.models.paymentlinks;

import com.epayclub.sdk.errors.EpayClubClientException;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Request model for canceling recurring payments.
 */
public class CancelRecurringPaymentsRequest {

    @JsonProperty("linkId")
    private String linkId;

    @JsonProperty("subscriptionId")
    private String subscriptionId;

    public CancelRecurringPaymentsRequest() {
    }

    private CancelRecurringPaymentsRequest(Builder builder) {
        this.linkId = builder.linkId;
        this.subscriptionId = builder.subscriptionId;
    }

    /**
     * Validates the request has all required fields.
     */
    public void validate() {
        if (linkId == null || linkId.isBlank()) {
            throw EpayClubClientException.requiredField("linkId");
        }
    }

    public String getLinkId() {
        return linkId;
    }

    public void setLinkId(String linkId) {
        this.linkId = linkId;
    }

    public String getSubscriptionId() {
        return subscriptionId;
    }

    public void setSubscriptionId(String subscriptionId) {
        this.subscriptionId = subscriptionId;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String linkId;
        private String subscriptionId;

        public Builder linkId(String linkId) {
            this.linkId = linkId;
            return this;
        }

        public Builder subscriptionId(String subscriptionId) {
            this.subscriptionId = subscriptionId;
            return this;
        }

        public CancelRecurringPaymentsRequest build() {
            CancelRecurringPaymentsRequest request = new CancelRecurringPaymentsRequest(this);
            request.validate();
            return request;
        }
    }
}
