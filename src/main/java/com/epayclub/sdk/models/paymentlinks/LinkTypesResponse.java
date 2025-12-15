package com.epayclub.sdk.models.paymentlinks;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Response model for fetching payment link types.
 */
public class LinkTypesResponse {

    @JsonProperty("paymentLinkTypes")
    private List<PaymentLinkType> paymentLinkTypes;

    @JsonProperty("status")
    private String status;

    @JsonProperty("statusCode")
    private String statusCode;

    @JsonProperty("message")
    private String message;

    public LinkTypesResponse() {
    }

    public List<PaymentLinkType> getPaymentLinkTypes() {
        return paymentLinkTypes;
    }

    public void setPaymentLinkTypes(List<PaymentLinkType> paymentLinkTypes) {
        this.paymentLinkTypes = paymentLinkTypes;
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

    public static class PaymentLinkType {

        @JsonProperty("paymentLinkName")
        private String paymentLinkName;

        @JsonProperty("description")
        private String description;

        @JsonProperty("status")
        private Boolean status;

        @JsonProperty("code")
        private String code;

        @JsonProperty("id")
        private Integer id;

        @JsonProperty("dateCreated")
        private String dateCreated;

        @JsonProperty("dateUpdated")
        private String dateUpdated;

        @JsonProperty("dateDeleted")
        private String dateDeleted;

        @JsonProperty("createdBy")
        private Integer createdBy;

        @JsonProperty("updatedBy")
        private Integer updatedBy;

        @JsonProperty("deletedBy")
        private Integer deletedBy;

        public PaymentLinkType() {
        }

        public String getPaymentLinkName() {
            return paymentLinkName;
        }

        public void setPaymentLinkName(String paymentLinkName) {
            this.paymentLinkName = paymentLinkName;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public Boolean getStatus() {
            return status;
        }

        public void setStatus(Boolean status) {
            this.status = status;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
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
    }
}
