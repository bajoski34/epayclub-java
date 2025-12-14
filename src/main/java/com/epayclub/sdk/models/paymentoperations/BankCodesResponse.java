package com.epayclub.sdk.models.paymentoperations;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Response model for bank codes.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class BankCodesResponse {

    @JsonProperty("status")
    private String status;

    @JsonProperty("statusCode")
    private String statusCode;

    @JsonProperty("message")
    private String message;

    @JsonProperty("data")
    private List<BankCode> data;

    public BankCodesResponse() {
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

    public List<BankCode> getData() {
        return data;
    }

    public void setData(List<BankCode> data) {
        this.data = data;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class BankCode {

        @JsonProperty("name")
        private String name;

        @JsonProperty("countryId")
        private Integer countryId;

        @JsonProperty("bankCode")
        private String bankCode;

        @JsonProperty("isMicrofinance")
        private Boolean isMicrofinance;

        @JsonProperty("isMortgage")
        private Boolean isMortgage;

        @JsonProperty("ussdBankCode")
        private String ussdBankCode;

        @JsonProperty("logo")
        private String logo;

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

        public BankCode() {
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getCountryId() {
            return countryId;
        }

        public void setCountryId(Integer countryId) {
            this.countryId = countryId;
        }

        public String getBankCode() {
            return bankCode;
        }

        public void setBankCode(String bankCode) {
            this.bankCode = bankCode;
        }

        public Boolean getIsMicrofinance() {
            return isMicrofinance;
        }

        public void setIsMicrofinance(Boolean isMicrofinance) {
            this.isMicrofinance = isMicrofinance;
        }

        public Boolean getIsMortgage() {
            return isMortgage;
        }

        public void setIsMortgage(Boolean isMortgage) {
            this.isMortgage = isMortgage;
        }

        public String getUssdBankCode() {
            return ussdBankCode;
        }

        public void setUssdBankCode(String ussdBankCode) {
            this.ussdBankCode = ussdBankCode;
        }

        public String getLogo() {
            return logo;
        }

        public void setLogo(String logo) {
            this.logo = logo;
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
