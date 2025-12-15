package com.epayclub.sdk.models.orders;

import com.epayclub.sdk.errors.EpayClubClientException;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Request model for paying an order (updated shape).
 * Desired JSON structure:
 * {
 *   "reference": "ORDER-123456",
 *   "paymentmethod": "C",
 *   "country": "NG",
 *   "card": {
 *     "cardnumber": "4111111111111111",
 *     "cvv": "123",
 *     "expirymonth": "12",
 *     "expiryyear": "30",
 *     "billingaddress": { ... }
 *   }
 * }
 */
public class PayOrderRequest {

    @JsonProperty("reference")
    private String reference;

    // JSON expects lowercase "paymentmethod"
    @JsonProperty("paymentmethod")
    private String paymentMethod;

    @JsonProperty("country")
    private String country;

    @JsonProperty("card")
    private Card card;

    public PayOrderRequest() {
    }

    private PayOrderRequest(Builder builder) {
        this.reference = builder.reference;
        this.paymentMethod = builder.paymentMethod;
        this.country = builder.country;
        this.card = builder.card;
    }

    /**
     * Validates the request has all required fields.
     */
    public void validate() {
        if (reference == null || reference.isBlank()) {
            throw EpayClubClientException.requiredField("reference");
        }
        if (paymentMethod == null || paymentMethod.isBlank()) {
            throw EpayClubClientException.requiredField("paymentmethod");
        }
        // If payment method is card (assume "C") ensure card is present and valid
        if ("C".equalsIgnoreCase(paymentMethod)) {
            if (card == null) {
                throw EpayClubClientException.requiredField("card");
            }
            card.validate();
        }
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String reference;
        private String paymentMethod;
        private String country;
        private Card card;

        public Builder reference(String reference) {
            this.reference = reference;
            return this;
        }

        public Builder paymentMethod(String paymentMethod) {
            this.paymentMethod = paymentMethod;
            return this;
        }

        public Builder country(String country) {
            this.country = country;
            return this;
        }

        public Builder card(Card card) {
            this.card = card;
            return this;
        }

        public PayOrderRequest build() {
            PayOrderRequest request = new PayOrderRequest(this);
            request.validate();
            return request;
        }
    }

    // Nested Card class
    public static class Card {

        @JsonProperty("cardnumber")
        private String cardNumber;

        @JsonProperty("cvv")
        private String cvv;

        @JsonProperty("expirymonth")
        private String expiryMonth;

        @JsonProperty("expiryyear")
        private String expiryYear;

        @JsonProperty("billingaddress")
        private BillingAddress billingAddress;

        public Card() {
        }

        private Card(Card.Builder builder) {
            this.cardNumber = builder.cardNumber;
            this.cvv = builder.cvv;
            this.expiryMonth = builder.expiryMonth;
            this.expiryYear = builder.expiryYear;
            this.billingAddress = builder.billingAddress;
        }

        public void validate() {
            if (cardNumber == null || cardNumber.isBlank()) {
                throw EpayClubClientException.requiredField("card.cardnumber");
            }
            if (cvv == null || cvv.isBlank()) {
                throw EpayClubClientException.requiredField("card.cvv");
            }
            if (expiryMonth == null || expiryMonth.isBlank()) {
                throw EpayClubClientException.requiredField("card.expirymonth");
            }
            if (expiryYear == null || expiryYear.isBlank()) {
                throw EpayClubClientException.requiredField("card.expiryyear");
            }
            if (billingAddress != null) {
                billingAddress.validate();
            }
        }

        public String getCardNumber() {
            return cardNumber;
        }

        public void setCardNumber(String cardNumber) {
            this.cardNumber = cardNumber;
        }

        public String getCvv() {
            return cvv;
        }

        public void setCvv(String cvv) {
            this.cvv = cvv;
        }

        public String getExpiryMonth() {
            return expiryMonth;
        }

        public void setExpiryMonth(String expiryMonth) {
            this.expiryMonth = expiryMonth;
        }

        public String getExpiryYear() {
            return expiryYear;
        }

        public void setExpiryYear(String expiryYear) {
            this.expiryYear = expiryYear;
        }

        public BillingAddress getBillingAddress() {
            return billingAddress;
        }

        public void setBillingAddress(BillingAddress billingAddress) {
            this.billingAddress = billingAddress;
        }

        public static Card.Builder builder() {
            return new Card.Builder();
        }

        public static class Builder {
            private String cardNumber;
            private String cvv;
            private String expiryMonth;
            private String expiryYear;
            private BillingAddress billingAddress;

            public Builder cardNumber(String cardNumber) {
                this.cardNumber = cardNumber;
                return this;
            }

            public Builder cvv(String cvv) {
                this.cvv = cvv;
                return this;
            }

            public Builder expiryMonth(String expiryMonth) {
                this.expiryMonth = expiryMonth;
                return this;
            }

            public Builder expiryYear(String expiryYear) {
                this.expiryYear = expiryYear;
                return this;
            }

            public Builder billingAddress(BillingAddress billingAddress) {
                this.billingAddress = billingAddress;
                return this;
            }

            public Card build() {
                return new Card(this);
            }
        }
    }

    // Nested BillingAddress class
    public static class BillingAddress {

        @JsonProperty("street")
        private String street;

        @JsonProperty("city")
        private String city;

        @JsonProperty("state")
        private String state;

        @JsonProperty("country")
        private String country;

        @JsonProperty("zipcode")
        private String zipcode;

        public BillingAddress() {
        }

        private BillingAddress(Builder builder) {
            this.street = builder.street;
            this.city = builder.city;
            this.state = builder.state;
            this.country = builder.country;
            this.zipcode = builder.zipcode;
        }

        public void validate() {
            // billing address fields are optional but if provided they should not be blank
            if (street != null && street.isBlank()) {
                throw EpayClubClientException.requiredField("card.billingaddress.street");
            }
            if (city != null && city.isBlank()) {
                throw EpayClubClientException.requiredField("card.billingaddress.city");
            }
            if (state != null && state.isBlank()) {
                throw EpayClubClientException.requiredField("card.billingaddress.state");
            }
            if (country != null && country.isBlank()) {
                throw EpayClubClientException.requiredField("card.billingaddress.country");
            }
            if (zipcode != null && zipcode.isBlank()) {
                throw EpayClubClientException.requiredField("card.billingaddress.zipcode");
            }
        }

        public String getStreet() {
            return street;
        }

        public void setStreet(String street) {
            this.street = street;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public String getZipcode() {
            return zipcode;
        }

        public void setZipcode(String zipcode) {
            this.zipcode = zipcode;
        }

        public static Builder builder() {
            return new Builder();
        }

        public static class Builder {
            private String street;
            private String city;
            private String state;
            private String country;
            private String zipcode;

            public Builder street(String street) {
                this.street = street;
                return this;
            }

            public Builder city(String city) {
                this.city = city;
                return this;
            }

            public Builder state(String state) {
                this.state = state;
                return this;
            }

            public Builder country(String country) {
                this.country = country;
                return this;
            }

            public Builder zipcode(String zipcode) {
                this.zipcode = zipcode;
                return this;
            }

            public BillingAddress build() {
                return new BillingAddress(this);
            }
        }
    }
}
