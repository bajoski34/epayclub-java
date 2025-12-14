package com.epayclub.sdk.models.paymentlinks;

import com.epayclub.sdk.errors.EpayClubClientException;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Request model for creating a payment link.
 */
public class CreateLinkRequest {

    @JsonProperty("Name")
    private String name;

    @JsonProperty("Description")
    private String description;

    @JsonProperty("Amount")
    private Double amount;

    @JsonProperty("Currency")
    private String currency;

    @JsonProperty("PaymentType")
    private String paymentType;

    @JsonProperty("Website")
    private String website;

    @JsonProperty("AuthOption")
    private String authOption;

    @JsonProperty("Mobile")
    private String mobile;

    @JsonProperty("BackgroundImage")
    private String backgroundImage;

    @JsonProperty("Limit")
    private String limit;

    public CreateLinkRequest() {
    }

    private CreateLinkRequest(Builder builder) {
        this.name = builder.name;
        this.description = builder.description;
        this.amount = builder.amount;
        this.currency = builder.currency;
        this.paymentType = builder.paymentType;
        this.website = builder.website;
        this.authOption = builder.authOption;
        this.mobile = builder.mobile;
        this.backgroundImage = builder.backgroundImage;
        this.limit = builder.limit;
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
        if (paymentType == null || paymentType.isBlank()) {
            throw EpayClubClientException.requiredField("paymentType");
        }
        if(authOption == null || authOption.isBlank()) {
            throw EpayClubClientException.requiredField("authOption");
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

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String type) {
        this.paymentType = type;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getAuthOption() {
        return authOption;
    }

    public void setAuthOption(String authOption) {
        this.authOption = authOption;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getBackgroundImage() {
        return backgroundImage;
    }

    public void setBackgroundImage(String backgroundImage) {
        this.backgroundImage = backgroundImage;
    }

    public String getLimit() {
        return limit;
    }

    public void setLimit(String limit) {
        this.limit = limit;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String name;
        private String description;
        private Double amount;
        private String currency;
        private String paymentType;
        private String website ;
        private String authOption;
        private String mobile;
        private String backgroundImage;
        private String limit;

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

        public Builder paymentType(String paymentType) {
            this.paymentType = paymentType;
            return this;
        }

        public Builder website(String website) {
            this.website = this.website;
            return this;
        }

        public Builder authOption(String authOption) {
            this.authOption = authOption;
            return this;
        }

        public Builder mobile(String mobile) {
            this.mobile = mobile;
            return this;
        }

        public Builder backgroundImage(String backgroundImage) {
            this.backgroundImage = backgroundImage;
            return this;
        }

        public Builder limit(String limit) {
            this.limit = limit;
            return this;
        }

        public CreateLinkRequest build() {
            CreateLinkRequest request = new CreateLinkRequest(this);
            request.validate();
            return request;
        }
    }
}
