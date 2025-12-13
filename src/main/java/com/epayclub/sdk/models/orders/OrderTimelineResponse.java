package com.epayclub.sdk.models.orders;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Response model for order timeline.
 */
public class OrderTimelineResponse {

    @JsonProperty("status")
    private String status;

    @JsonProperty("statusId")
    private String statusId;

    @JsonProperty("responseCode")
    private String responseCode;

    @JsonProperty("message")
    private String message;

    @JsonProperty("data")
    private List<TimelineEvent> data;

    public OrderTimelineResponse() {
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatusId() {
        return statusId;
    }

    public void setStatusId(String statusId) {
        this.statusId = statusId;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<TimelineEvent> getData() {
        return data;
    }

    public void setData(List<TimelineEvent> data) {
        this.data = data;
    }

    public static class TimelineEvent {

        @JsonProperty("eventType")
        private String eventType;

        @JsonProperty("description")
        private String description;

        @JsonProperty("timestamp")
        private String timestamp;

        @JsonProperty("metadata")
        private Object metadata;

        public TimelineEvent() {
        }

        public String getEventType() {
            return eventType;
        }

        public void setEventType(String eventType) {
            this.eventType = eventType;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(String timestamp) {
            this.timestamp = timestamp;
        }

        public Object getMetadata() {
            return metadata;
        }

        public void setMetadata(Object metadata) {
            this.metadata = metadata;
        }
    }
}
