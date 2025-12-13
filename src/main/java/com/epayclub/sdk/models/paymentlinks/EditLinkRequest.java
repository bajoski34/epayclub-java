package com.epayclub.sdk.models.paymentlinks;

import com.epayclub.sdk.errors.EpayClubClientException;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Request model for editing a payment link.
 */
public class EditLinkRequest {

    @JsonProperty("linkId")
    private String linkId;

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    @JsonProperty("amount")
    private Double amount;

    @JsonProperty("redirectUrl")
    private String redirectUrl;

    @JsonProperty("expiresAt")
    private String expiresAt;

    @JsonProperty("metadata")
    private Object metadata;

    public EditLinkRequest() {
    }

    private EditLinkRequest(Builder builder) {
        this.linkId = builder.linkId;
        this.name = builder.name;
        this.description = builder.description;
        this.amount = builder.amount;
        this.redirectUrl = builder.redirectUrl;
        this.expiresAt = builder.expiresAt;
        this.metadata = builder.metadata;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getRedirectUrl() {
        return redirectUrl;
    }

    public void setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }

    public String getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(String expiresAt) {
        this.expiresAt = expiresAt;
    }

    public Object getMetadata() {
        return metadata;
    }

    public void setMetadata(Object metadata) {
        this.metadata = metadata;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String linkId;
        private String name;
        private String description;
        private Double amount;
        private String redirectUrl;
        private String expiresAt;
        private Object metadata;

        public Builder linkId(String linkId) {
            this.linkId = linkId;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder amount(Double amount) {
            this.amount = amount;
            return this;
        }

        public Builder redirectUrl(String redirectUrl) {
            this.redirectUrl = redirectUrl;
            return this;
        }

        public Builder expiresAt(String expiresAt) {
            this.expiresAt = expiresAt;
            return this;
        }

        public Builder metadata(Object metadata) {
            this.metadata = metadata;
            return this;
        }

        public EditLinkRequest build() {
            EditLinkRequest request = new EditLinkRequest(this);
            request.validate();
            return request;
        }
    }
}
