package com.epayclub.sdk.models.paymentlinks;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Data model for payment link matching the provided JSON structure.
 */
public class PaymentLinkData {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("paymentType")
    private String paymentType;

    @JsonProperty("logo")
    private String logo;

    @JsonProperty("amount")
    private Double amount;

    @JsonProperty("dateCreated")
    private String dateCreated;

    @JsonProperty("reference")
    private String reference;

    @JsonProperty("createdBy")
    private String createdBy;

    @JsonProperty("creatorEmail")
    private String creatorEmail;

    @JsonProperty("isActive")
    private Boolean isActive;

    @JsonProperty("currency")
    private String currency;

    @JsonProperty("limit")
    private Integer limit;

    @JsonProperty("paymentLinkUrl")
    private String paymentLinkUrl;

    @JsonProperty("appEnvironmentId")
    private Integer appEnvironmentId;

    @JsonProperty("paymentLinkType")
    private String paymentLinkType;

    @JsonProperty("paymentLinkCode")
    private String paymentLinkCode;

    @JsonProperty("description")
    private String description;

    public PaymentLinkData() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getCreatorEmail() {
        return creatorEmail;
    }

    public void setCreatorEmail(String creatorEmail) {
        this.creatorEmail = creatorEmail;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public String getPaymentLinkUrl() {
        return paymentLinkUrl;
    }

    public void setPaymentLinkUrl(String paymentLinkUrl) {
        this.paymentLinkUrl = paymentLinkUrl;
    }

    public Integer getAppEnvironmentId() {
        return appEnvironmentId;
    }

    public void setAppEnvironmentId(Integer appEnvironmentId) {
        this.appEnvironmentId = appEnvironmentId;
    }

    public String getPaymentLinkType() {
        return paymentLinkType;
    }

    public void setPaymentLinkType(String paymentLinkType) {
        this.paymentLinkType = paymentLinkType;
    }

    public String getPaymentLinkCode() {
        return paymentLinkCode;
    }

    public void setPaymentLinkCode(String paymentLinkCode) {
        this.paymentLinkCode = paymentLinkCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
