package com.epayclub.sdk.models.orders;

import com.epayclub.sdk.errors.EpayClubClientException;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Request model for fee calculation.
 */
public class FeeRequest {

    @JsonProperty("amount")
    private Double amount;

    @JsonProperty("currency")
    private String currency;

    @JsonProperty("paymentMethod")
    private String paymentMethod;

    public FeeRequest() {
    }

    private FeeRequest(Builder builder) {
        this.amount = builder.amount;
        this.currency = builder.currency;
        this.paymentMethod = builder.paymentMethod;
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

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Double amount;
        private String currency;
        private String paymentMethod;

        public Builder amount(Double amount) {
            this.amount = amount;
            return this;
        }

        public Builder currency(String currency) {
            this.currency = currency;
            return this;
        }

        public Builder paymentMethod(String paymentMethod) {
            this.paymentMethod = paymentMethod;
            return this;
        }

        public FeeRequest build() {
            FeeRequest request = new FeeRequest(this);
            request.validate();
            return request;
        }
    }
}
