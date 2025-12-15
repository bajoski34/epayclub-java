package com.epayclub.sdk.models.paymentlinks;

import com.epayclub.sdk.errors.EpayClubClientException;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Request model for activating a payment link.
 */
public class ActivateLinkRequest {

    @JsonProperty("id")
    private String id;

    public ActivateLinkRequest() {
    }

    public ActivateLinkRequest(String id) {
        this.id = id;
        validate();
    }

    /**
     * Validates the request has all required fields.
     */
    public void validate() {
        if (id == null || id.isBlank()) {
            throw EpayClubClientException.requiredField("id");
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

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public ActivateLinkRequest build() {
            return new ActivateLinkRequest(id);
        }
    }
}
