package com.epayclub.sdk.models.orders;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Response model for order status check matching the new JSON format.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderStatusResponse {

    @JsonProperty("is_final_status")
    private boolean isFinalStatus;

    @JsonProperty("requery_needed")
    private boolean requeryNeeded;

    @JsonProperty("requery_type")
    private String requeryType;

    @JsonProperty("data")
    private Data data;

    @JsonProperty("status")
    private String status;

    @JsonProperty("status_code")
    private String statusCode;

    @JsonProperty("message")
    private String message;

    public OrderStatusResponse() {
    }

    public boolean isFinalStatus() {
        return isFinalStatus;
    }

    public void setFinalStatus(boolean finalStatus) {
        isFinalStatus = finalStatus;
    }

    public boolean isRequeryNeeded() {
        return requeryNeeded;
    }

    public void setRequeryNeeded(boolean requeryNeeded) {
        this.requeryNeeded = requeryNeeded;
    }

    public String getRequeryType() {
        return requeryType;
    }

    public void setRequeryType(String requeryType) {
        this.requeryType = requeryType;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
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

    public static class Data {

        @JsonProperty("payment_reference")
        private String paymentReference;

        @JsonProperty("order_reference")
        private String orderReference;

        @JsonProperty("product_id")
        private Integer productId;

        @JsonProperty("subsidiary_id")
        private Integer subsidiaryId;

        @JsonProperty("wallet_id")
        private Integer walletId;

        @JsonProperty("customer_id")
        private Integer customerId;

        @JsonProperty("total_charged_amount")
        private Long totalChargedAmount;

        @JsonProperty("payment_status")
        private Integer paymentStatus;

        @JsonProperty("currency_id")
        private Integer currencyId;

        @JsonProperty("fee")
        private Long fee;

        @JsonProperty("subsidiary_fee")
        private Long subsidiaryFee;

        @JsonProperty("customer_fee")
        private Long customerFee;

        @JsonProperty("payment_type")
        private String paymentType;

        @JsonProperty("payment_response_code")
        private String paymentResponseCode;

        @JsonProperty("payment_response_message")
        private String paymentResponseMessage;

        @JsonProperty("provider_response_date")
        private String providerResponseDate;

        @JsonProperty("date_payment_confirmed")
        private String datePaymentConfirmed;

        @JsonProperty("narration")
        private String narration;

        @JsonProperty("remarks")
        private String remarks;

        @JsonProperty("parent_transaction_id")
        private Integer parentTransactionId;

        @JsonProperty("id")
        private Integer id;

        @JsonProperty("created_by")
        private Integer createdBy;

        @JsonProperty("updated_by")
        private Integer updatedBy;

        @JsonProperty("deleted_by")
        private Integer deletedBy;

        @JsonProperty("date_created")
        private String dateCreated;

        @JsonProperty("date_updated")
        private String dateUpdated;

        @JsonProperty("date_deleted")
        private String dateDeleted;

        public Data() {
        }

        public String getPaymentReference() {
            return paymentReference;
        }

        public void setPaymentReference(String paymentReference) {
            this.paymentReference = paymentReference;
        }

        public String getOrderReference() {
            return orderReference;
        }

        public void setOrderReference(String orderReference) {
            this.orderReference = orderReference;
        }

        public Integer getProductId() {
            return productId;
        }

        public void setProductId(Integer productId) {
            this.productId = productId;
        }

        public Integer getSubsidiaryId() {
            return subsidiaryId;
        }

        public void setSubsidiaryId(Integer subsidiaryId) {
            this.subsidiaryId = subsidiaryId;
        }

        public Integer getWalletId() {
            return walletId;
        }

        public void setWalletId(Integer walletId) {
            this.walletId = walletId;
        }

        public Integer getCustomerId() {
            return customerId;
        }

        public void setCustomerId(Integer customerId) {
            this.customerId = customerId;
        }

        public Long getTotalChargedAmount() {
            return totalChargedAmount;
        }

        public void setTotalChargedAmount(Long totalChargedAmount) {
            this.totalChargedAmount = totalChargedAmount;
        }

        public Integer getPaymentStatus() {
            return paymentStatus;
        }

        public void setPaymentStatus(Integer paymentStatus) {
            this.paymentStatus = paymentStatus;
        }

        public Integer getCurrencyId() {
            return currencyId;
        }

        public void setCurrencyId(Integer currencyId) {
            this.currencyId = currencyId;
        }

        public Long getFee() {
            return fee;
        }

        public void setFee(Long fee) {
            this.fee = fee;
        }

        public Long getSubsidiaryFee() {
            return subsidiaryFee;
        }

        public void setSubsidiaryFee(Long subsidiaryFee) {
            this.subsidiaryFee = subsidiaryFee;
        }

        public Long getCustomerFee() {
            return customerFee;
        }

        public void setCustomerFee(Long customerFee) {
            this.customerFee = customerFee;
        }

        public String getPaymentType() {
            return paymentType;
        }

        public void setPaymentType(String paymentType) {
            this.paymentType = paymentType;
        }

        public String getPaymentResponseCode() {
            return paymentResponseCode;
        }

        public void setPaymentResponseCode(String paymentResponseCode) {
            this.paymentResponseCode = paymentResponseCode;
        }

        public String getPaymentResponseMessage() {
            return paymentResponseMessage;
        }

        public void setPaymentResponseMessage(String paymentResponseMessage) {
            this.paymentResponseMessage = paymentResponseMessage;
        }

        public String getProviderResponseDate() {
            return providerResponseDate;
        }

        public void setProviderResponseDate(String providerResponseDate) {
            this.providerResponseDate = providerResponseDate;
        }

        public String getDatePaymentConfirmed() {
            return datePaymentConfirmed;
        }

        public void setDatePaymentConfirmed(String datePaymentConfirmed) {
            this.datePaymentConfirmed = datePaymentConfirmed;
        }

        public String getNarration() {
            return narration;
        }

        public void setNarration(String narration) {
            this.narration = narration;
        }

        public String getRemarks() {
            return remarks;
        }

        public void setRemarks(String remarks) {
            this.remarks = remarks;
        }

        public Integer getParentTransactionId() {
            return parentTransactionId;
        }

        public void setParentTransactionId(Integer parentTransactionId) {
            this.parentTransactionId = parentTransactionId;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public Integer getCreatedBy() {
            return createdBy;
        }

        public void setCreatedBy(Integer createdBy) {
            this.createdBy = createdBy;
        }

        public Integer getUpdatedBy() {
            return updatedBy;
        }

        public void setUpdatedBy(Integer updatedBy) {
            this.updatedBy = updatedBy;
        }

        public Integer getDeletedBy() {
            return deletedBy;
        }

        public void setDeletedBy(Integer deletedBy) {
            this.deletedBy = deletedBy;
        }

        public String getDateCreated() {
            return dateCreated;
        }

        public void setDateCreated(String dateCreated) {
            this.dateCreated = dateCreated;
        }

        public String getDateUpdated() {
            return dateUpdated;
        }

        public void setDateUpdated(String dateUpdated) {
            this.dateUpdated = dateUpdated;
        }

        public String getDateDeleted() {
            return dateDeleted;
        }

        public void setDateDeleted(String dateDeleted) {
            this.dateDeleted = dateDeleted;
        }
    }
}
