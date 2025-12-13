package com.epayclub.sdk.models.paymentlinks;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Response model for fetching payment link types.
 */
public class LinkTypesResponse {

    @JsonProperty("status")
    private String status;

    @JsonProperty("statusId")
    private String statusId;

    @JsonProperty("responseCode")
    private String responseCode;

    @JsonProperty("message")
    private String message;

    @JsonProperty("data")
    private List<LinkType> data;

    public LinkTypesResponse() {
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

    public List<LinkType> getData() {
        return data;
    }

    public void setData(List<LinkType> data) {
        this.data = data;
    }

    public static class LinkType {

        @JsonProperty("type")
        private String type;

        @JsonProperty("name")
        private String name;

        @JsonProperty("description")
        private String description;

        public LinkType() {
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }
    }
}
