package com.epayclub.sdk.models.orders;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

/**
 * Response model for fee calculation.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class FeeResponse {

    @JsonProperty("status")
    private String status;

    // replaced legacy fields with statusCode to match API
    @JsonProperty("statusCode")
    private String statusCode;

    @JsonProperty("message")
    private String message;

    @JsonProperty("data")
    private FeeData data;

    public FeeResponse() {
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public FeeData getData() {
        return data;
    }

    public void setData(FeeData data) {
        this.data = data;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class FeeData {

        @JsonProperty("fee")
        private Double fee;

        @JsonProperty("amount")
        private Double amount;

        @JsonProperty("subsidiaryFee")
        private Double subsidiaryFee;

        @JsonProperty("customerFee")
        private Double customerFee;

        @JsonProperty("totalChargedAmount")
        private Double totalChargedAmount;

        @JsonProperty("paymentOption")
        private String paymentOption;

        public FeeData() {
        }

        public Double getFee() {
            return fee;
        }

        public void setFee(Double fee) {
            this.fee = fee;
        }

        public Double getAmount() {
            return amount;
        }

        public void setAmount(Double amount) {
            this.amount = amount;
        }

        public Double getSubsidiaryFee() {
            return subsidiaryFee;
        }

        public void setSubsidiaryFee(Double subsidiaryFee) {
            this.subsidiaryFee = subsidiaryFee;
        }

        public Double getCustomerFee() {
            return customerFee;
        }

        public void setCustomerFee(Double customerFee) {
            this.customerFee = customerFee;
        }

        public Double getTotalChargedAmount() {
            return totalChargedAmount;
        }

        public void setTotalChargedAmount(Double totalChargedAmount) {
            this.totalChargedAmount = totalChargedAmount;
        }

        public String getPaymentOption() {
            return paymentOption;
        }

        public void setPaymentOption(String paymentOption) {
            this.paymentOption = paymentOption;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            FeeData feeData = (FeeData) o;
            return Objects.equals(fee, feeData.fee) && Objects.equals(amount, feeData.amount) && Objects.equals(subsidiaryFee, feeData.subsidiaryFee) && Objects.equals(customerFee, feeData.customerFee) && Objects.equals(totalChargedAmount, feeData.totalChargedAmount) && Objects.equals(paymentOption, feeData.paymentOption);
        }

        @Override
        public int hashCode() {
            return Objects.hash(fee, amount, subsidiaryFee, customerFee, totalChargedAmount, paymentOption);
        }
    }
}
