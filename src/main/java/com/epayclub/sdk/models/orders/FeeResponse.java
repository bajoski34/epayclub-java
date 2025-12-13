package com.epayclub.sdk.models.orders;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Response model for fee calculation.
 */
public class FeeResponse {

    @JsonProperty("status")
    private String status;

    @JsonProperty("statusId")
    private String statusId;

    @JsonProperty("responseCode")
    private String responseCode;

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

    public FeeData getData() {
        return data;
    }

    public void setData(FeeData data) {
        this.data = data;
    }

    public static class FeeData {

        @JsonProperty("fee")
        private Double fee;

        @JsonProperty("total")
        private Double total;

        @JsonProperty("amount")
        private Double amount;

        @JsonProperty("currency")
        private String currency;

        public FeeData() {
        }

        public Double getFee() {
            return fee;
        }

        public void setFee(Double fee) {
            this.fee = fee;
        }

        public Double getTotal() {
            return total;
        }

        public void setTotal(Double total) {
            this.total = total;
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
    }
}
