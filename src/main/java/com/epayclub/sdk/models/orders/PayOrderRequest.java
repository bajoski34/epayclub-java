package com.epayclub.sdk.models.orders;

import com.epayclub.sdk.errors.EpayClubClientException;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Request model for paying an order.
 */
public class PayOrderRequest {

    @JsonProperty("orderId")
    private String orderId;

    @JsonProperty("paymentMethod")
    private String paymentMethod;

    @JsonProperty("bankCode")
    private String bankCode;

    @JsonProperty("accountNumber")
    private String accountNumber;

    @JsonProperty("cardNumber")
    private String cardNumber;

    @JsonProperty("expiryMonth")
    private String expiryMonth;

    @JsonProperty("expiryYear")
    private String expiryYear;

    @JsonProperty("cvv")
    private String cvv;

    @JsonProperty("pin")
    private String pin;

    public PayOrderRequest() {
    }

    private PayOrderRequest(Builder builder) {
        this.orderId = builder.orderId;
        this.paymentMethod = builder.paymentMethod;
        this.bankCode = builder.bankCode;
        this.accountNumber = builder.accountNumber;
        this.cardNumber = builder.cardNumber;
        this.expiryMonth = builder.expiryMonth;
        this.expiryYear = builder.expiryYear;
        this.cvv = builder.cvv;
        this.pin = builder.pin;
    }

    /**
     * Validates the request has all required fields.
     */
    public void validate() {
        if (orderId == null || orderId.isBlank()) {
            throw EpayClubClientException.requiredField("orderId");
        }
        if (paymentMethod == null || paymentMethod.isBlank()) {
            throw EpayClubClientException.requiredField("paymentMethod");
        }
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getExpiryMonth() {
        return expiryMonth;
    }

    public void setExpiryMonth(String expiryMonth) {
        this.expiryMonth = expiryMonth;
    }

    public String getExpiryYear() {
        return expiryYear;
    }

    public void setExpiryYear(String expiryYear) {
        this.expiryYear = expiryYear;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String orderId;
        private String paymentMethod;
        private String bankCode;
        private String accountNumber;
        private String cardNumber;
        private String expiryMonth;
        private String expiryYear;
        private String cvv;
        private String pin;

        public Builder orderId(String orderId) {
            this.orderId = orderId;
            return this;
        }

        public Builder paymentMethod(String paymentMethod) {
            this.paymentMethod = paymentMethod;
            return this;
        }

        public Builder bankCode(String bankCode) {
            this.bankCode = bankCode;
            return this;
        }

        public Builder accountNumber(String accountNumber) {
            this.accountNumber = accountNumber;
            return this;
        }

        public Builder cardNumber(String cardNumber) {
            this.cardNumber = cardNumber;
            return this;
        }

        public Builder expiryMonth(String expiryMonth) {
            this.expiryMonth = expiryMonth;
            return this;
        }

        public Builder expiryYear(String expiryYear) {
            this.expiryYear = expiryYear;
            return this;
        }

        public Builder cvv(String cvv) {
            this.cvv = cvv;
            return this;
        }

        public Builder pin(String pin) {
            this.pin = pin;
            return this;
        }

        public PayOrderRequest build() {
            PayOrderRequest request = new PayOrderRequest(this);
            request.validate();
            return request;
        }
    }
}
