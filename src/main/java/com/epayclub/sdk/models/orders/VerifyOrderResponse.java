package com.epayclub.sdk.models.orders;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Response model for order verification.
 */
public class VerifyOrderResponse {

    @JsonProperty("status")
    private String status;

    @JsonProperty("statusId")
    private String statusId;

    @JsonProperty("responseCode")
    private String responseCode;

    @JsonProperty("message")
    private String message;

    @JsonProperty("data")
    private VerifyOrderData data;

    public VerifyOrderResponse() {
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatusId() {
        return statusId;
    }

    public void setStatusId(String statusId) {
        this.statusId = statusId;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public VerifyOrderData getData() {
        return data;
    }

    public void setData(VerifyOrderData data) {
        this.data = data;
    }

    public static class VerifyOrderData {

        @JsonProperty("orderId")
        private String orderId;

        @JsonProperty("orderReference")
        private String orderReference;

        @JsonProperty("orderStatus")
        private String orderStatus;

        @JsonProperty("paymentStatus")
        private String paymentStatus;

        @JsonProperty("amount")
        private Double amount;

        @JsonProperty("fee")
        private Double fee;

        @JsonProperty("currency")
        private String currency;

        @JsonProperty("email")
        private String email;

        @JsonProperty("firstName")
        private String firstName;

        @JsonProperty("lastName")
        private String lastName;

        @JsonProperty("phoneNumber")
        private String phoneNumber;

        @JsonProperty("paymentMethod")
        private String paymentMethod;

        @JsonProperty("transactionId")
        private String transactionId;

        @JsonProperty("verified")
        private Boolean verified;

        @JsonProperty("createdAt")
        private String createdAt;

        @JsonProperty("paidAt")
        private String paidAt;

        public VerifyOrderData() {
        }

        public String getOrderId() {
            return orderId;
        }

        public void setOrderId(String orderId) {
            this.orderId = orderId;
        }

        public String getOrderReference() {
            return orderReference;
        }

        public void setOrderReference(String orderReference) {
            this.orderReference = orderReference;
        }

        public String getOrderStatus() {
            return orderStatus;
        }

        public void setOrderStatus(String orderStatus) {
            this.orderStatus = orderStatus;
        }

        public String getPaymentStatus() {
            return paymentStatus;
        }

        public void setPaymentStatus(String paymentStatus) {
            this.paymentStatus = paymentStatus;
        }

        public Double getAmount() {
            return amount;
        }

        public void setAmount(Double amount) {
            this.amount = amount;
        }

        public Double getFee() {
            return fee;
        }

        public void setFee(Double fee) {
            this.fee = fee;
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

        public String getPhoneNumber() {
            return phoneNumber;
        }

        public void setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
        }

        public String getPaymentMethod() {
            return paymentMethod;
        }

        public void setPaymentMethod(String paymentMethod) {
            this.paymentMethod = paymentMethod;
        }

        public String getTransactionId() {
            return transactionId;
        }

        public void setTransactionId(String transactionId) {
            this.transactionId = transactionId;
        }

        public Boolean getVerified() {
            return verified;
        }

        public void setVerified(Boolean verified) {
            this.verified = verified;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getPaidAt() {
            return paidAt;
        }

        public void setPaidAt(String paidAt) {
            this.paidAt = paidAt;
        }
    }
}
