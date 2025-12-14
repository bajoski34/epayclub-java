package com.epayclub.sdk.models.orders;

import com.epayclub.sdk.errors.EpayClubClientException;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

/**
 * Request model for creating an order using nested structure:
 * {
 *   "customer": { ... },
 *   "order": { ... },
 *   "payment": { ... },
 *   "paymentMeta": { ... }
 * }
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateOrderRequest {

    @JsonProperty("customer")
    private Customer customer;

    @JsonProperty("order")
    private Order order;

    @JsonProperty("payment")
    private Payment payment;

    @JsonProperty("paymentMeta")
    private Map<String, Object> paymentMeta;

    public CreateOrderRequest() {
    }

    private CreateOrderRequest(Builder builder) {
        this.customer = builder.customer;
        this.order = builder.order;
        this.payment = builder.payment;
        this.paymentMeta = builder.paymentMeta;
    }

    /**
     * Validate required fields for the API.
     */
    public void validate() {
        if (order == null) {
            throw EpayClubClientException.requiredField("order");
        }
        if (order.getAmount() == null || order.getAmount() <= 0) {
            throw EpayClubClientException.requiredField("order.amount (must be positive)");
        }
        if (order.getCurrency() == null || order.getCurrency().isBlank()) {
            throw EpayClubClientException.requiredField("order.currency");
        }
        if (order.getReference() == null || order.getReference().isBlank()) {
            throw EpayClubClientException.requiredField("order.reference");
        }

        if (customer == null) {
            throw EpayClubClientException.requiredField("customer");
        }
        boolean hasEmail = customer.getEmail() != null && !customer.getEmail().isBlank();
        boolean hasMobile = customer.getMobile() != null && !customer.getMobile().isBlank();
        if (!hasEmail && !hasMobile) {
            throw EpayClubClientException.requiredField("customer.email or customer.mobile");
        }
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public Map<String, Object> getPaymentMeta() {
        return paymentMeta;
    }

    public void setPaymentMeta(Map<String, Object> paymentMeta) {
        this.paymentMeta = paymentMeta;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Customer customer;
        private Order order;
        private Payment payment;
        private Map<String, Object> paymentMeta;

        public Builder customer(Customer customer) {
            this.customer = customer;
            return this;
        }

        public Builder order(Order order) {
            this.order = order;
            return this;
        }

        public Builder payment(Payment payment) {
            this.payment = payment;
            return this;
        }

        public Builder paymentMeta(Map<String, Object> paymentMeta) {
            this.paymentMeta = paymentMeta;
            return this;
        }

        // Convenience helpers to build nested objects without creating them manually
        public Builder customer(String email, String mobile, String firstname, String lastname, String country) {
            this.customer = new Customer(email, mobile, firstname, lastname, country);
            return this;
        }

        public Builder order(Double amount, String reference, String currency, String description) {
            this.order = new Order(amount, reference, currency, description);
            return this;
        }

        public Builder payment(String redirectUrl, Integer paymentlinkid, Integer frequencyId, Integer numberOfPayments) {
            this.payment = new Payment(redirectUrl, paymentlinkid, frequencyId, numberOfPayments);
            return this;
        }

        public CreateOrderRequest build() {
            CreateOrderRequest r = new CreateOrderRequest(this);
            r.validate();
            return r;
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Customer {
        @JsonProperty("email")
        private String email;

        @JsonProperty("mobile")
        private String mobile;

        @JsonProperty("firstname")
        private String firstname;

        @JsonProperty("lastname")
        private String lastname;

        @JsonProperty("country")
        private String country;

        public Customer() {
        }

        public Customer(String email, String mobile, String firstname, String lastname, String country) {
            this.email = email;
            this.mobile = mobile;
            this.firstname = firstname;
            this.lastname = lastname;
            this.country = country;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getFirstname() {
            return firstname;
        }

        public void setFirstname(String firstname) {
            this.firstname = firstname;
        }

        public String getLastname() {
            return lastname;
        }

        public void setLastname(String lastname) {
            this.lastname = lastname;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Order {
        @JsonProperty("amount")
        private Double amount;

        @JsonProperty("reference")
        private String reference;

        @JsonProperty("currency")
        private String currency;

        @JsonProperty("description")
        private String description;

        public Order() {
        }

        public Order(Double amount, String reference, String currency, String description) {
            this.amount = amount;
            this.reference = reference;
            this.currency = currency;
            this.description = description;
        }

        public Double getAmount() {
            return amount;
        }

        public void setAmount(Double amount) {
            this.amount = amount;
        }

        public String getReference() {
            return reference;
        }

        public void setReference(String reference) {
            this.reference = reference;
        }

        public String getCurrency() {
            return currency;
        }

        public void setCurrency(String currency) {
            this.currency = currency;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Payment {
        @JsonProperty("RedirectUrl")
        private String redirectUrl;

        @JsonProperty("paymentlinkid")
        private Integer paymentlinkid;

        @JsonProperty("frequencyId")
        private Integer frequencyId;

        @JsonProperty("numberOfPayments")
        private Integer numberOfPayments;

        public Payment() {
        }

        public Payment(String redirectUrl, Integer paymentlinkid, Integer frequencyId, Integer numberOfPayments) {
            this.redirectUrl = redirectUrl;
            this.paymentlinkid = paymentlinkid;
            this.frequencyId = frequencyId;
            this.numberOfPayments = numberOfPayments;
        }

        public String getRedirectUrl() {
            return redirectUrl;
        }

        public void setRedirectUrl(String redirectUrl) {
            this.redirectUrl = redirectUrl;
        }

        public Integer getPaymentlinkid() {
            return paymentlinkid;
        }

        public void setPaymentlinkid(Integer paymentlinkid) {
            this.paymentlinkid = paymentlinkid;
        }

        public Integer getFrequencyId() {
            return frequencyId;
        }

        public void setFrequencyId(Integer frequencyId) {
            this.frequencyId = frequencyId;
        }

        public Integer getNumberOfPayments() {
            return numberOfPayments;
        }

        public void setNumberOfPayments(Integer numberOfPayments) {
            this.numberOfPayments = numberOfPayments;
        }
    }
}
