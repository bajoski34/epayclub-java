package com.epayclub.sdk.models.orders;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Response model for order verification.
 */
public class VerifyOrderResponse {

    @JsonProperty("data")
    private VerifyOrderData data;

    @JsonProperty("status")
    private String status;

    @JsonProperty("statusCode")
    private String statusCode;

    @JsonProperty("message")
    private String message;

    public VerifyOrderResponse() {
    }

    public VerifyOrderData getData() {
        return data;
    }

    public void setData(VerifyOrderData data) {
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

    public static class VerifyOrderData {

        @JsonProperty("orderReference")
        private String orderReference;

        @JsonProperty("paymentReference")
        private String paymentReference;

        @JsonProperty("productName")
        private String productName;

        @JsonProperty("totalAmountCharged")
        private Double totalAmountCharged;

        @JsonProperty("statusId")
        private Integer statusId;

        @JsonProperty("status")
        private String status;

        @JsonProperty("paymentMethod")
        private String paymentMethod;

        @JsonProperty("paymentResponseCode")
        private String paymentResponseCode;

        @JsonProperty("paymentResponseMessage")
        private String paymentResponseMessage;

        @JsonProperty("narration")
        private String narration;

        @JsonProperty("remarks")
        private String remarks;

        @JsonProperty("currencyId")
        private Integer currencyId;

        @JsonProperty("paymentLinkId")
        private Integer paymentLinkId;

        @JsonProperty("paymentLinkReference")
        private String paymentLinkReference;

        @JsonProperty("recurringPaymentId")
        private Integer recurringPaymentId;

        @JsonProperty("recurringPaymentReference")
        private String recurringPaymentReference;

        @JsonProperty("currencyName")
        private String currencyName;

        @JsonProperty("fee")
        private Double fee;

        @JsonProperty("feeRate")
        private Double feeRate;

        @JsonProperty("subsidiaryFee")
        private Double subsidiaryFee;

        @JsonProperty("customerFee")
        private Double customerFee;

        @JsonProperty("dateCreated")
        private String dateCreated;

        @JsonProperty("dateUpdated")
        private String dateUpdated;

        @JsonProperty("datePaymentConfirmed")
        private String datePaymentConfirmed;

        @JsonProperty("orderPayments")
        private List<OrderPayment> orderPayments;

        @JsonProperty("customer")
        private Customer customer;

        @JsonProperty("cardDetails")
        private List<CardDetail> cardDetails;

        @JsonProperty("paymentLink")
        private Object paymentLink;

        public VerifyOrderData() {
        }

        public String getOrderReference() {
            return orderReference;
        }

        public void setOrderReference(String orderReference) {
            this.orderReference = orderReference;
        }

        public String getPaymentReference() {
            return paymentReference;
        }

        public void setPaymentReference(String paymentReference) {
            this.paymentReference = paymentReference;
        }

        public String getProductName() {
            return productName;
        }

        public void setProductName(String productName) {
            this.productName = productName;
        }

        public Double getTotalAmountCharged() {
            return totalAmountCharged;
        }

        public void setTotalAmountCharged(Double totalAmountCharged) {
            this.totalAmountCharged = totalAmountCharged;
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

        public String getPaymentMethod() {
            return paymentMethod;
        }

        public void setPaymentMethod(String paymentMethod) {
            this.paymentMethod = paymentMethod;
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

        public Integer getCurrencyId() {
            return currencyId;
        }

        public void setCurrencyId(Integer currencyId) {
            this.currencyId = currencyId;
        }

        public Integer getPaymentLinkId() {
            return paymentLinkId;
        }

        public void setPaymentLinkId(Integer paymentLinkId) {
            this.paymentLinkId = paymentLinkId;
        }

        public String getPaymentLinkReference() {
            return paymentLinkReference;
        }

        public void setPaymentLinkReference(String paymentLinkReference) {
            this.paymentLinkReference = paymentLinkReference;
        }

        public Integer getRecurringPaymentId() {
            return recurringPaymentId;
        }

        public void setRecurringPaymentId(Integer recurringPaymentId) {
            this.recurringPaymentId = recurringPaymentId;
        }

        public String getRecurringPaymentReference() {
            return recurringPaymentReference;
        }

        public void setRecurringPaymentReference(String recurringPaymentReference) {
            this.recurringPaymentReference = recurringPaymentReference;
        }

        public String getCurrencyName() {
            return currencyName;
        }

        public void setCurrencyName(String currencyName) {
            this.currencyName = currencyName;
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

        public String getDatePaymentConfirmed() {
            return datePaymentConfirmed;
        }

        public void setDatePaymentConfirmed(String datePaymentConfirmed) {
            this.datePaymentConfirmed = datePaymentConfirmed;
        }

        /**
         * Derived property used by tests and consumers to indicate whether the order
         * appears to have any payment verification information. This is computed
         * from the presence of order payments, a payment reference, payment response
         * code, or certain statusId values.
         */
        public Boolean getVerified() {
            if (orderPayments != null && !orderPayments.isEmpty()) {
                return Boolean.TRUE;
            }
            if (paymentReference != null && !paymentReference.isEmpty()) {
                return Boolean.TRUE;
            }
            if (paymentResponseCode != null && !paymentResponseCode.isEmpty()) {
                return Boolean.TRUE;
            }
            if (statusId != null) {
                // treat certain status ids as indicating a verified/payment attempt
                if (statusId == 2 || statusId == 3 || statusId == 4) {
                    return Boolean.TRUE;
                }
            }
            return Boolean.FALSE;
        }

        /**
         * Converts the numeric payment status id into a textual payment status used
         * across the SDK tests and consumers.
         */
        public String getPaymentStatus() {
            if (statusId == null) return null;
            switch (statusId) {
                case 1:
                    return "pending";
                case 2:
                    return "processing";
                case 3:
                    return "failed";
                case 4:
                    return "paid";
                default:
                    return "failed";
            }
        }

        public List<OrderPayment> getOrderPayments() {
            return orderPayments;
        }

        public void setOrderPayments(List<OrderPayment> orderPayments) {
            this.orderPayments = orderPayments;
        }

        public Customer getCustomer() {
            return customer;
        }

        public void setCustomer(Customer customer) {
            this.customer = customer;
        }

        public List<CardDetail> getCardDetails() {
            return cardDetails;
        }

        public void setCardDetails(List<CardDetail> cardDetails) {
            this.cardDetails = cardDetails;
        }

        public Object getPaymentLink() {
            return paymentLink;
        }

        public void setPaymentLink(Object paymentLink) {
            this.paymentLink = paymentLink;
        }

        public static class OrderPayment {

            @JsonProperty("orderId")
            private Integer orderId;

            @JsonProperty("orderPaymentReference")
            private String orderPaymentReference;

            @JsonProperty("paymentOptionId")
            private Integer paymentOptionId;

            @JsonProperty("paymentOption")
            private String paymentOption;

            @JsonProperty("statusId")
            private Integer statusId;

            @JsonProperty("status")
            private String status;

            @JsonProperty("responseCode")
            private String responseCode;

            @JsonProperty("responseMessage")
            private String responseMessage;

            @JsonProperty("orderPaymentInstrument")
            private Object orderPaymentInstrument;

            @JsonProperty("remarks")
            private String remarks;

            @JsonProperty("dateCreated")
            private String dateCreated;

            @JsonProperty("dateUpdated")
            private String dateUpdated;

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

            public Integer getPaymentOptionId() {
                return paymentOptionId;
            }

            public void setPaymentOptionId(Integer paymentOptionId) {
                this.paymentOptionId = paymentOptionId;
            }

            public String getPaymentOption() {
                return paymentOption;
            }

            public void setPaymentOption(String paymentOption) {
                this.paymentOption = paymentOption;
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

            public String getResponseCode() {
                return responseCode;
            }

            public void setResponseCode(String responseCode) {
                this.responseCode = responseCode;
            }

            public String getResponseMessage() {
                return responseMessage;
            }

            public void setResponseMessage(String responseMessage) {
                this.responseMessage = responseMessage;
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
        }

        public static class Customer {

            @JsonProperty("customerId")
            private Integer customerId;

            @JsonProperty("firstName")
            private String firstName;

            @JsonProperty("lastName")
            private String lastName;

            @JsonProperty("emailAddress")
            private String emailAddress;

            @JsonProperty("countryShortName")
            private String countryShortName;

            @JsonProperty("customerGroup")
            private String customerGroup;

            @JsonProperty("countryId")
            private Integer countryId;

            @JsonProperty("globalStatusId")
            private Integer globalStatusId;

            @JsonProperty("globalStatus")
            private String globalStatus;

            @JsonProperty("mobileNumber")
            private String mobileNumber;

            @JsonProperty("isBlacklisted")
            private Boolean isBlacklisted;

            @JsonProperty("reasonBlacklisted")
            private String reasonBlacklisted;

            @JsonProperty("dateCreated")
            private String dateCreated;

            @JsonProperty("dateUpdated")
            private String dateUpdated;

            public Customer() {
            }

            public Integer getCustomerId() {
                return customerId;
            }

            public void setCustomerId(Integer customerId) {
                this.customerId = customerId;
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

            public String getEmailAddress() {
                return emailAddress;
            }

            public void setEmailAddress(String emailAddress) {
                this.emailAddress = emailAddress;
            }

            public String getCountryShortName() {
                return countryShortName;
            }

            public void setCountryShortName(String countryShortName) {
                this.countryShortName = countryShortName;
            }

            public String getCustomerGroup() {
                return customerGroup;
            }

            public void setCustomerGroup(String customerGroup) {
                this.customerGroup = customerGroup;
            }

            public Integer getCountryId() {
                return countryId;
            }

            public void setCountryId(Integer countryId) {
                this.countryId = countryId;
            }

            public Integer getGlobalStatusId() {
                return globalStatusId;
            }

            public void setGlobalStatusId(Integer globalStatusId) {
                this.globalStatusId = globalStatusId;
            }

            public String getGlobalStatus() {
                return globalStatus;
            }

            public void setGlobalStatus(String globalStatus) {
                this.globalStatus = globalStatus;
            }

            public String getMobileNumber() {
                return mobileNumber;
            }

            public void setMobileNumber(String mobileNumber) {
                this.mobileNumber = mobileNumber;
            }

            public Boolean getIsBlacklisted() {
                return isBlacklisted;
            }

            public void setIsBlacklisted(Boolean isBlacklisted) {
                this.isBlacklisted = isBlacklisted;
            }

            public String getReasonBlacklisted() {
                return reasonBlacklisted;
            }

            public void setReasonBlacklisted(String reasonBlacklisted) {
                this.reasonBlacklisted = reasonBlacklisted;
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
        }

        public static class CardDetail {

            @JsonProperty("orderPaymentId")
            private Integer orderPaymentId;

            @JsonProperty("status")
            private Boolean status;

            @JsonProperty("country")
            private String country;

            @JsonProperty("cardToken")
            private String cardToken;

            @JsonProperty("cardExpiryMonth")
            private String cardExpiryMonth;

            @JsonProperty("cardExpiryYear")
            private String cardExpiryYear;

            @JsonProperty("cardType")
            private String cardType;

            @JsonProperty("cardIssuer")
            private String cardIssuer;

            @JsonProperty("cardFirstSixDigits")
            private String cardFirstSixDigits;

            @JsonProperty("cardLastFourDigits")
            private String cardLastFourDigits;

            @JsonProperty("dateCreated")
            private String dateCreated;

            @JsonProperty("appEnvironmentId")
            private Integer appEnvironmentId;

            public CardDetail() {
            }

            public Integer getOrderPaymentId() {
                return orderPaymentId;
            }

            public void setOrderPaymentId(Integer orderPaymentId) {
                this.orderPaymentId = orderPaymentId;
            }

            public Boolean getStatus() {
                return status;
            }

            public void setStatus(Boolean status) {
                this.status = status;
            }

            public String getCountry() {
                return country;
            }

            public void setCountry(String country) {
                this.country = country;
            }

            public String getCardToken() {
                return cardToken;
            }

            public void setCardToken(String cardToken) {
                this.cardToken = cardToken;
            }

            public String getCardExpiryMonth() {
                return cardExpiryMonth;
            }

            public void setCardExpiryMonth(String cardExpiryMonth) {
                this.cardExpiryMonth = cardExpiryMonth;
            }

            public String getCardExpiryYear() {
                return cardExpiryYear;
            }

            public void setCardExpiryYear(String cardExpiryYear) {
                this.cardExpiryYear = cardExpiryYear;
            }

            public String getCardType() {
                return cardType;
            }

            public void setCardType(String cardType) {
                this.cardType = cardType;
            }

            public String getCardIssuer() {
                return cardIssuer;
            }

            public void setCardIssuer(String cardIssuer) {
                this.cardIssuer = cardIssuer;
            }

            public String getCardFirstSixDigits() {
                return cardFirstSixDigits;
            }

            public void setCardFirstSixDigits(String cardFirstSixDigits) {
                this.cardFirstSixDigits = cardFirstSixDigits;
            }

            public String getCardLastFourDigits() {
                return cardLastFourDigits;
            }

            public void setCardLastFourDigits(String cardLastFourDigits) {
                this.cardLastFourDigits = cardLastFourDigits;
            }

            public String getDateCreated() {
                return dateCreated;
            }

            public void setDateCreated(String dateCreated) {
                this.dateCreated = dateCreated;
            }

            public Integer getAppEnvironmentId() {
                return appEnvironmentId;
            }

            public void setAppEnvironmentId(Integer appEnvironmentId) {
                this.appEnvironmentId = appEnvironmentId;
            }
        }

    }
}
