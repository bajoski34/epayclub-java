package com.epayclub.sdk.models.paymentlinks;

import com.epayclub.sdk.errors.EpayClubClientException;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Request model for creating a payment link.
 */
public class CreateLinkRequest {

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    @JsonProperty("amount")
    private Double amount;

    @JsonProperty("currency")
    private String currency;

    @JsonProperty("type")
    private String type;

    @JsonProperty("redirectUrl")
    private String redirectUrl;

    @JsonProperty("expiresAt")
    private String expiresAt;

    @JsonProperty("frequency")
    private String frequency;

    @JsonProperty("metadata")
    private Object metadata;

    public CreateLinkRequest() {
    }

    private CreateLinkRequest(Builder builder) {
        this.name = builder.name;
        this.description = builder.description;
        this.amount = builder.amount;
        this.currency = builder.currency;
        this.type = builder.type;
        this.redirectUrl = builder.redirectUrl;
        this.expiresAt = builder.expiresAt;
        this.frequency = builder.frequency;
        this.metadata = builder.metadata;
    }

    /**
     * Validates the request has all required fields.
     */
    public void validate() {
        if (name == null || name.isBlank()) {
            throw EpayClubClientException.requiredField("name");
        }
        if (amount == null || amount <= 0) {
            throw EpayClubClientException.requiredField("amount (must be positive)");
        }
        if (currency == null || currency.isBlank()) {
            throw EpayClubClientException.requiredField("currency");
        }
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

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
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
        private String name;
        private String description;
        private Double amount;
        private String currency;
        private String type;
        private String redirectUrl;
        private String expiresAt;
        private String frequency;
        private Object metadata;

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

        public Builder currency(String currency) {
            this.currency = currency;
            return this;
        }

        public Builder type(String type) {
            this.type = type;
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

        public Builder frequency(String frequency) {
            this.frequency = frequency;
            return this;
        }

        public Builder metadata(Object metadata) {
            this.metadata = metadata;
            return this;
        }

        public CreateLinkRequest build() {
            CreateLinkRequest request = new CreateLinkRequest(this);
            request.validate();
            return request;
        }
    }
}
