package com.epayclub.sdk.models.orders;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Response model for paying an order.
 */
public class PayOrderResponse {

    @JsonProperty("status")
    private String status;

    @JsonProperty("statusCode")
    private String statusCode;

    @JsonProperty("message")
    private String message;

    @JsonProperty("data")
    private Data data;

    public PayOrderResponse() {
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

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public static class Data {

        @JsonProperty("paymentDetail")
        private PaymentDetail paymentDetail;

        @JsonProperty("bankTransferDetails")
        private Object bankTransferDetails;

        @JsonProperty("orderPayment")
        private OrderPayment orderPayment;

        // Backwards-compatible fields some callers/tests expect
        @JsonProperty("transactionId")
        private String transactionId;

        @JsonProperty("paymentStatus")
        private String paymentStatus;

        public Data() {
        }

        public PaymentDetail getPaymentDetail() {
            return paymentDetail;
        }

        public void setPaymentDetail(PaymentDetail paymentDetail) {
            this.paymentDetail = paymentDetail;
        }

        public Object getBankTransferDetails() {
            return bankTransferDetails;
        }

        public void setBankTransferDetails(Object bankTransferDetails) {
            this.bankTransferDetails = bankTransferDetails;
        }

        public OrderPayment getOrderPayment() {
            return orderPayment;
        }

        public void setOrderPayment(OrderPayment orderPayment) {
            this.orderPayment = orderPayment;
        }

        public String getTransactionId() {
            return transactionId;
        }

        public void setTransactionId(String transactionId) {
            this.transactionId = transactionId;
        }

        public String getPaymentStatus() {
            return paymentStatus;
        }

        public void setPaymentStatus(String paymentStatus) {
            this.paymentStatus = paymentStatus;
        }
    }

    public static class PaymentDetail {

        @JsonProperty("redirectUrl")
        private String redirectUrl;

        @JsonProperty("recipientAccount")
        private Object recipientAccount;

        @JsonProperty("paymentReference")
        private String paymentReference;

        public PaymentDetail() {
        }

        public String getRedirectUrl() {
            return redirectUrl;
        }

        public void setRedirectUrl(String redirectUrl) {
            this.redirectUrl = redirectUrl;
        }

        public Object getRecipientAccount() {
            return recipientAccount;
        }

        public void setRecipientAccount(Object recipientAccount) {
            this.recipientAccount = recipientAccount;
        }

        public String getPaymentReference() {
            return paymentReference;
        }

        public void setPaymentReference(String paymentReference) {
            this.paymentReference = paymentReference;
        }
    }

    public static class OrderPayment {

        @JsonProperty("orderId")
        private Integer orderId;

        @JsonProperty("orderPaymentReference")
        private String orderPaymentReference;

        @JsonProperty("currency")
        private String currency;

        @JsonProperty("statusId")
        private Integer statusId;

        @JsonProperty("orderPaymentResponseCode")
        private String orderPaymentResponseCode;

        @JsonProperty("orderPaymentResponseMessage")
        private String orderPaymentResponseMessage;

        @JsonProperty("orderPaymentInstrument")
        private Object orderPaymentInstrument;

        @JsonProperty("remarks")
        private String remarks;

        @JsonProperty("totalAmount")
        private Integer totalAmount;

        @JsonProperty("fee")
        private Integer fee;

        public OrderPayment() {
        }

        public Integer getOrderId() {
            return orderId;
        }

        public void setOrderId(Integer orderId) {
            this.orderId = orderId;
        }

        public String getOrderPaymentReference() {
            return orderPaymentReference;
        }

        public void setOrderPaymentReference(String orderPaymentReference) {
            this.orderPaymentReference = orderPaymentReference;
        }

        public String getCurrency() {
            return currency;
        }

        public void setCurrency(String currency) {
            this.currency = currency;
        }

        public Integer getStatusId() {
            return statusId;
        }

        public void setStatusId(Integer statusId) {
            this.statusId = statusId;
        }

        public String getOrderPaymentResponseCode() {
            return orderPaymentResponseCode;
        }

        public void setOrderPaymentResponseCode(String orderPaymentResponseCode) {
            this.orderPaymentResponseCode = orderPaymentResponseCode;
        }

        public String getOrderPaymentResponseMessage() {
            return orderPaymentResponseMessage;
        }

        public void setOrderPaymentResponseMessage(String orderPaymentResponseMessage) {
            this.orderPaymentResponseMessage = orderPaymentResponseMessage;
        }

        public Object getOrderPaymentInstrument() {
            return orderPaymentInstrument;
        }

        public void setOrderPaymentInstrument(Object orderPaymentInstrument) {
            this.orderPaymentInstrument = orderPaymentInstrument;
        }

        public String getRemarks() {
            return remarks;
        }

        public void setRemarks(String remarks) {
            this.remarks = remarks;
        }

        public Integer getTotalAmount() {
            return totalAmount;
        }

        public void setTotalAmount(Integer totalAmount) {
            this.totalAmount = totalAmount;
        }

        public Integer getFee() {
            return fee;
        }

        public void setFee(Integer fee) {
            this.fee = fee;
        }
    }
}
