package com.epayclub.sdk.models.paymentlinks;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Response model for creating a payment link.
 */
public class CreateLinkResponse {

    @JsonProperty("status")
    private String status;

    @JsonProperty("statusCode")
    private String statusCode;

    @JsonProperty("message")
    private String message;

    @JsonProperty("data")
    private Data data;

    public CreateLinkResponse() {
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

    /**
     * Data wrapper containing paymentLink and subsidiary as shown in the API response.
     */
    public static class Data {

        @JsonProperty("paymentLink")
        private PaymentLinkData paymentLink;

        @JsonProperty("subsidiary")
        private Subsidiary subsidiary;

        public Data() {
        }

        public PaymentLinkData getPaymentLink() {
            return paymentLink;
        }

        public void setPaymentLink(PaymentLinkData paymentLink) {
            this.paymentLink = paymentLink;
        }

        public Subsidiary getSubsidiary() {
            return subsidiary;
        }

        public void setSubsidiary(Subsidiary subsidiary) {
            this.subsidiary = subsidiary;
        }
    }

    /**
     * Minimal Subsidiary representation matching the fields returned by the API.
     */
    public static class Subsidiary {

        @JsonProperty("id")
        private Integer id;

        @JsonProperty("name")
        private String name;

        @JsonProperty("country")
        private String country;

        @JsonProperty("supportEmail")
        private String supportEmail;

        @JsonProperty("customization")
        private Object customization;

        public Subsidiary() {
        }

        public Integer getId() { return id; }
        public void setId(Integer id) { this.id = id; }
        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
        public String getCountry() { return country; }
        public void setCountry(String country) { this.country = country; }
        public String getSupportEmail() { return supportEmail; }
        public void setSupportEmail(String supportEmail) { this.supportEmail = supportEmail; }
        public Object getCustomization() { return customization; }
        public void setCustomization(Object customization) { this.customization = customization; }
    }
}
