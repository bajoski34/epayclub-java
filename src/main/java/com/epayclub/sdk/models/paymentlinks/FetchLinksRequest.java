package com.epayclub.sdk.models.paymentlinks;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Request model for fetching payment links.
 */
public class FetchLinksRequest {

    @JsonProperty("page")
    private Integer page;

    @JsonProperty("limit")
    private Integer limit;

    @JsonProperty("active")
    private Boolean active;

    public FetchLinksRequest() {
    }

    private FetchLinksRequest(Builder builder) {
        this.page = builder.page;
        this.limit = builder.limit;
        this.active = builder.active;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    /**
     * Converts the request to query parameters.
     *
     * @return query string
     */
    public String toQueryString() {
        StringBuilder sb = new StringBuilder();
        if (page != null) {
            sb.append("page=").append(page);
        }
        if (limit != null) {
            if (sb.length() > 0) sb.append("&");
            sb.append("limit=").append(limit);
        }
        if (active != null) {
            if (sb.length() > 0) sb.append("&");
            sb.append("active=").append(active);
        }
        return sb.toString();
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Integer page;
        private Integer limit;
        private Boolean active;

        public Builder page(Integer page) {
            this.page = page;
            return this;
        }

        public Builder limit(Integer limit) {
            this.limit = limit;
            return this;
        }

        public Builder active(Boolean active) {
            this.active = active;
            return this;
        }

        public FetchLinksRequest build() {
            return new FetchLinksRequest(this);
        }
    }
}
