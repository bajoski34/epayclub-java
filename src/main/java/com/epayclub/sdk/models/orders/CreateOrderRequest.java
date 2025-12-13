package com.epayclub.sdk.models.orders;

import com.epayclub.sdk.errors.EpayClubClientException;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Request model for creating an order.
 */
public class CreateOrderRequest {

    @JsonProperty("amount")
    private Double amount;

    @JsonProperty("currency")
    private String currency;

    @JsonProperty("email")
    private String email;

    @JsonProperty("phoneNumber")
    private String phoneNumber;

    @JsonProperty("firstName")
    private String firstName;

    @JsonProperty("lastName")
    private String lastName;

    @JsonProperty("narration")
    private String narration;

    @JsonProperty("redirectUrl")
    private String redirectUrl;

    @JsonProperty("reference")
    private String reference;

    @JsonProperty("metadata")
    private Object metadata;

    public CreateOrderRequest() {
    }

    private CreateOrderRequest(Builder builder) {
        this.amount = builder.amount;
        this.currency = builder.currency;
        this.email = builder.email;
        this.phoneNumber = builder.phoneNumber;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.narration = builder.narration;
        this.redirectUrl = builder.redirectUrl;
        this.reference = builder.reference;
        this.metadata = builder.metadata;
    }

    /**
     * Validates the request has all required fields.
     */
    public void validate() {
        if (amount == null || amount <= 0) {
            throw EpayClubClientException.requiredField("amount (must be positive)");
        }
        if (currency == null || currency.isBlank()) {
            throw EpayClubClientException.requiredField("currency");
        }
        if (email == null || email.isBlank()) {
            throw EpayClubClientException.requiredField("email");
        }
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getNarration() {
        return narration;
    }

    public void setNarration(String narration) {
        this.narration = narration;
    }

    public String getRedirectUrl() {
        return redirectUrl;
    }

    public void setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
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
        private Double amount;
        private String currency;
        private String email;
        private String phoneNumber;
        private String firstName;
        private String lastName;
        private String narration;
        private String redirectUrl;
        private String reference;
        private Object metadata;

        public Builder amount(Double amount) {
            this.amount = amount;
            return this;
        }

        public Builder currency(String currency) {
            this.currency = currency;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder phoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public Builder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder narration(String narration) {
            this.narration = narration;
            return this;
        }

        public Builder redirectUrl(String redirectUrl) {
            this.redirectUrl = redirectUrl;
            return this;
        }

        public Builder reference(String reference) {
            this.reference = reference;
            return this;
        }

        public Builder metadata(Object metadata) {
            this.metadata = metadata;
            return this;
        }

        public CreateOrderRequest build() {
            CreateOrderRequest request = new CreateOrderRequest(this);
            request.validate();
            return request;
        }
    }
}
