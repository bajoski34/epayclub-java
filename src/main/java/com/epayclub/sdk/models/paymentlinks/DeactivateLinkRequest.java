package com.epayclub.sdk.models.paymentlinks;

import com.epayclub.sdk.errors.EpayClubClientException;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Request model for deactivating a payment link.
 */
public class DeactivateLinkRequest {

    @JsonProperty("linkId")
    private String linkId;

    public DeactivateLinkRequest() {
    }

    public DeactivateLinkRequest(String linkId) {
        this.linkId = linkId;
        validate();
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

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String linkId;

        public Builder linkId(String linkId) {
            this.linkId = linkId;
            return this;
        }

        public DeactivateLinkRequest build() {
            return new DeactivateLinkRequest(linkId);
        }
    }
}
