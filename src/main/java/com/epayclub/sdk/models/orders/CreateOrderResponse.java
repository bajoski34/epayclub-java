package com.epayclub.sdk.models.orders;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Response model for order creation.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateOrderResponse {

    @JsonProperty("status")
    private String status;

    @JsonProperty("statusCode")
    private String statusCode;

    @JsonProperty("message")
    private String message;

    @JsonProperty("data")
    private Data data;

    public CreateOrderResponse() {
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

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Data {

        @JsonProperty("order")
        private Order order;

        @JsonProperty("subsidiary")
        private Subsidiary subsidiary;

        @JsonProperty("customer")
        private Customer customer;

        @JsonProperty("payment")
        private Payment payment;

        @JsonProperty("otherPaymentOptions")
        private List<OtherPaymentOption> otherPaymentOptions;

        @JsonProperty("savedCards")
        private List<Object> savedCards;

        @JsonProperty("subsidiaryOrderSummary")
        private SubsidiaryOrderSummary subsidiaryOrderSummary;

        public Data() {
        }

        public Order getOrder() {
            return order;
        }

        public void setOrder(Order order) {
            this.order = order;
        }

        public Subsidiary getSubsidiary() {
            return subsidiary;
        }

        public void setSubsidiary(Subsidiary subsidiary) {
            this.subsidiary = subsidiary;
        }

        public Customer getCustomer() {
            return customer;
        }

        public void setCustomer(Customer customer) {
            this.customer = customer;
        }

        public Payment getPayment() {
            return payment;
        }

        public void setPayment(Payment payment) {
            this.payment = payment;
        }

        public List<OtherPaymentOption> getOtherPaymentOptions() {
            return otherPaymentOptions;
        }

        public void setOtherPaymentOptions(List<OtherPaymentOption> otherPaymentOptions) {
            this.otherPaymentOptions = otherPaymentOptions;
        }

        public List<Object> getSavedCards() {
            return savedCards;
        }

        public void setSavedCards(List<Object> savedCards) {
            this.savedCards = savedCards;
        }

        public SubsidiaryOrderSummary getSubsidiaryOrderSummary() {
            return subsidiaryOrderSummary;
        }

        public void setSubsidiaryOrderSummary(SubsidiaryOrderSummary subsidiaryOrderSummary) {
            this.subsidiaryOrderSummary = subsidiaryOrderSummary;
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Order {

        @JsonProperty("reference")
        private String reference;

        @JsonProperty("processorReference")
        private String processorReference;

        @JsonProperty("orderPaymentReference")
        private String orderPaymentReference;

        @JsonProperty("amount")
        private Double amount;

        @JsonProperty("fee")
        private Double fee;

        @JsonProperty("feeRate")
        private Double feeRate;

        @JsonProperty("statusId")
        private Integer statusId;

        @JsonProperty("status")
        private String status;

        @JsonProperty("currency")
        private String currency;

        @JsonProperty("narration")
        private String narration;

        @JsonProperty("paymentLinkId")
        private Integer paymentLinkId;

        @JsonProperty("recurringPaymentId")
        private Integer recurringPaymentId;

        @JsonProperty("paymentLinkReference")
        private String paymentLinkReference;

        @JsonProperty("recurringPaymentReference")
        private String recurringPaymentReference;

        public Order() {
        }

        public String getReference() {
            return reference;
        }

        public void setReference(String reference) {
            this.reference = reference;
        }

        public String getProcessorReference() {
            return processorReference;
        }

        public void setProcessorReference(String processorReference) {
            this.processorReference = processorReference;
        }

        public String getOrderPaymentReference() {
            return orderPaymentReference;
        }

        public void setOrderPaymentReference(String orderPaymentReference) {
            this.orderPaymentReference = orderPaymentReference;
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

        public Double getFeeRate() {
            return feeRate;
        }

        public void setFeeRate(Double feeRate) {
            this.feeRate = feeRate;
        }

        public Integer getStatusId() {
            return statusId;
        }

        public void setStatusId(Integer statusId) {
            this.statusId = statusId;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getCurrency() {
            return currency;
        }

        public void setCurrency(String currency) {
            this.currency = currency;
        }

        public String getNarration() {
            return narration;
        }

        public void setNarration(String narration) {
            this.narration = narration;
        }

        public Integer getPaymentLinkId() {
            return paymentLinkId;
        }

        public void setPaymentLinkId(Integer paymentLinkId) {
            this.paymentLinkId = paymentLinkId;
        }

        public Integer getRecurringPaymentId() {
            return recurringPaymentId;
        }

        public void setRecurringPaymentId(Integer recurringPaymentId) {
            this.recurringPaymentId = recurringPaymentId;
        }

        public String getPaymentLinkReference() {
            return paymentLinkReference;
        }

        public void setPaymentLinkReference(String paymentLinkReference) {
            this.paymentLinkReference = paymentLinkReference;
        }

        public String getRecurringPaymentReference() {
            return recurringPaymentReference;
        }

        public void setRecurringPaymentReference(String recurringPaymentReference) {
            this.recurringPaymentReference = recurringPaymentReference;
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
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
        private List<Object> customization;

        public Subsidiary() {
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public String getSupportEmail() {
            return supportEmail;
        }

        public void setSupportEmail(String supportEmail) {
            this.supportEmail = supportEmail;
        }

        public List<Object> getCustomization() {
            return customization;
        }

        public void setCustomization(List<Object> customization) {
            this.customization = customization;
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Customer {

        @JsonProperty("email")
        private String email;

        @JsonProperty("firstName")
        private String firstName;

        @JsonProperty("lastName")
        private String lastName;

        @JsonProperty("mobile")
        private String mobile;

        @JsonProperty("country")
        private String country;

        public Customer() {
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

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Payment {

        @JsonProperty("code")
        private String code;

        @JsonProperty("source")
        private String source;

        @JsonProperty("selectedOption")
        private String selectedOption;

        @JsonProperty("accountNumber")
        private String accountNumber;

        @JsonProperty("bankProviderName")
        private String bankProviderName;

        public Payment() {
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getSelectedOption() {
            return selectedOption;
        }

        public void setSelectedOption(String selectedOption) {
            this.selectedOption = selectedOption;
        }

        public String getAccountNumber() {
            return accountNumber;
        }

        public void setAccountNumber(String accountNumber) {
            this.accountNumber = accountNumber;
        }

        public String getBankProviderName() {
            return bankProviderName;
        }

        public void setBankProviderName(String bankProviderName) {
            this.bankProviderName = bankProviderName;
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class OtherPaymentOption {

        @JsonProperty("code")
        private String code;

        @JsonProperty("name")
        private String name;

        @JsonProperty("currency")
        private String currency;

        public OtherPaymentOption() {
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCurrency() {
            return currency;
        }

        public void setCurrency(String currency) {
            this.currency = currency;
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class SubsidiaryOrderSummary {

        @JsonProperty("orderName")
        private String orderName;

        @JsonProperty("totalAmount")
        private Double totalAmount;

        @JsonProperty("reference")
        private String reference;

        @JsonProperty("currency")
        private String currency;

        @JsonProperty("orderItems")
        private List<OrderItem> orderItems;

        public SubsidiaryOrderSummary() {
        }

        public String getOrderName() {
            return orderName;
        }

        public void setOrderName(String orderName) {
            this.orderName = orderName;
        }

        public Double getTotalAmount() {
            return totalAmount;
        }

        public void setTotalAmount(Double totalAmount) {
            this.totalAmount = totalAmount;
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

        public List<OrderItem> getOrderItems() {
            return orderItems;
        }

        public void setOrderItems(List<OrderItem> orderItems) {
            this.orderItems = orderItems;
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class OrderItem {

        @JsonProperty("name")
        private String name;

        @JsonProperty("amount")
        private Double amount;

        public OrderItem() {
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Double getAmount() {
            return amount;
        }

        public void setAmount(Double amount) {
            this.amount = amount;
        }
    }
}
