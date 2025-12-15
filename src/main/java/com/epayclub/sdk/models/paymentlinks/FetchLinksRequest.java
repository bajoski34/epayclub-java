package com.epayclub.sdk.models.paymentlinks;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Request model for fetching payment links.
 */
public class FetchLinksRequest {

    @JsonProperty("id")
    private Integer id;

    public FetchLinksRequest() {
    }

    private FetchLinksRequest(Builder builder) {
        this.id = builder.id;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Integer id;

        public Builder id(Integer id) {
            this.id = id;
            return this;
        }

        public FetchLinksRequest build() {
            return new FetchLinksRequest(this);
        }
    }
}
