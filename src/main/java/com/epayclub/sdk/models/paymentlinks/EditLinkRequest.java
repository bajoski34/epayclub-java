package com.epayclub.sdk.models.paymentlinks;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Request model for editing a payment link. Matches expected JSON keys.
 */
public class EditLinkRequest {

    @JsonProperty("id")
    private String id;

    @JsonProperty("Name")
    private String name;

    @JsonProperty("Description")
    private String description;

    @JsonProperty("PaymentType")
    private String paymentType;

    @JsonProperty("Amount")
    private String amount;

    @JsonProperty("Mobile")
    private String mobile;

    @JsonProperty("BackgroundImage")
    private String backgroundImage;

    @JsonProperty("Website")
    private String website;

    @JsonProperty("AuthOption")
    private String authOption;

    @JsonProperty("Limit")
    private String limit;

    public EditLinkRequest() {
    }

    private EditLinkRequest(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.description = builder.description;
        this.paymentType = builder.paymentType;
        this.amount = builder.amount;
        this.mobile = builder.mobile;
        this.backgroundImage = builder.backgroundImage;
        this.website = builder.website;
        this.authOption = builder.authOption;
        this.limit = builder.limit;
    }

    /**
     * Validate the request. Currently no required fields are enforced to match
     * the provided JSON structure. This method is left intentionally empty so
     * builder.build() can still call it without throwing.
     */
    public void validate() {
        // no-op: no required fields defined in the requested JSON structure
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
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
        private String id;
        private String name;
        private String description;
        private String paymentType;
        private String amount;
        private String mobile;
        private String backgroundImage;
        private String website;
        private String authOption;
        private String limit;

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder paymentType(String paymentType) {
            this.paymentType = paymentType;
            return this;
        }

        public Builder amount(String amount) {
            this.amount = amount;
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

        public Builder website(String website) {
            this.website = website;
            return this;
        }

        public Builder authOption(String authOption) {
            this.authOption = authOption;
            return this;
        }

        public Builder limit(String limit) {
            this.limit = limit;
            return this;
        }

        public EditLinkRequest build() {
            EditLinkRequest request = new EditLinkRequest(this);
            request.validate();
            return request;
        }
    }
}
