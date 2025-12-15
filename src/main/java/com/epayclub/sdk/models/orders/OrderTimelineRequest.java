package com.epayclub.sdk.models.orders;

import com.epayclub.sdk.errors.EpayClubClientException;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Request model for order timeline.
 * <p>
 * JSON shape expected:
 * {
 *   "eventname": "order",
 *   "orderreference": "ORDER-123456"
 * }
 */
public class OrderTimelineRequest {

    @JsonProperty("eventname")
    private String eventName;

    @JsonProperty("orderreference")
    private String orderReference;

    public OrderTimelineRequest() {
    }

    public OrderTimelineRequest(String eventName, String orderReference) {
        this.eventName = eventName;
        this.orderReference = orderReference;
        validate();
    }

    /**
     * Validates the request has all required fields.
     */
    public void validate() {
        if (eventName == null || eventName.isBlank()) {
            throw EpayClubClientException.requiredField("eventname");
        }
        if (orderReference == null || orderReference.isBlank()) {
            throw EpayClubClientException.requiredField("orderreference");
        }
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getOrderReference() {
        return orderReference;
    }

    public void setOrderReference(String orderReference) {
        this.orderReference = orderReference;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String eventName;
        private String orderReference;

        public Builder eventName(String eventName) {
            this.eventName = eventName;
            return this;
        }

        public Builder orderReference(String orderReference) {
            this.orderReference = orderReference;
            return this;
        }

        public OrderTimelineRequest build() {
            return new OrderTimelineRequest(eventName, orderReference);
        }
    }
}
