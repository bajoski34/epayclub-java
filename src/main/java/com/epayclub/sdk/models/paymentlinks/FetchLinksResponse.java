package com.epayclub.sdk.models.paymentlinks;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.util.List;

/**
 * Response model for fetching payment links.
 */
@JsonDeserialize(using = FetchLinksResponse.FetchLinksResponseDeserializer.class)
public class FetchLinksResponse {

    @JsonProperty("status")
    private String status;

    @JsonProperty("statusId")
    private String statusId;

    @JsonProperty("responseCode")
    private String responseCode;

    @JsonProperty("message")
    private String message;

    @JsonProperty("data")
    private List<PaymentLinkData> data;

    @JsonProperty("pagination")
    private PaginationData pagination;

    public FetchLinksResponse() {
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

    public List<PaymentLinkData> getData() {
        return data;
    }

    public void setData(List<PaymentLinkData> data) {
        this.data = data;
    }

    public PaginationData getPagination() {
        return pagination;
    }

    public void setPagination(PaginationData pagination) {
        this.pagination = pagination;
    }

    public static class PaginationData {

        @JsonProperty("page")
        private Integer page;

        @JsonProperty("limit")
        private Integer limit;

        @JsonProperty("total")
        private Integer total;

        @JsonProperty("totalPages")
        private Integer totalPages;

        public PaginationData() {
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

        public Integer getTotal() {
            return total;
        }

        public void setTotal(Integer total) {
            this.total = total;
        }

        public Integer getTotalPages() {
            return totalPages;
        }

        public void setTotalPages(Integer totalPages) {
            this.totalPages = totalPages;
        }
    }

    /**
     * Custom deserializer that accepts either an array root (then treat it as `data`) or an object with fields.
     */
    public static class FetchLinksResponseDeserializer extends StdDeserializer<FetchLinksResponse> {

        public FetchLinksResponseDeserializer() {
            this(null);
        }

        protected FetchLinksResponseDeserializer(Class<?> vc) {
            super(vc);
        }

        @Override
        public FetchLinksResponse deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
            if (p.isExpectedStartArrayToken()) {
                // root is an array => treat as data
                List<PaymentLinkData> list = p.readValueAs(new TypeReference<List<PaymentLinkData>>() {});
                FetchLinksResponse resp = new FetchLinksResponse();
                resp.setData(list);
                // populate pagination.total from array length so calling code/tests that expect pagination won't NPE
                PaginationData pg = new PaginationData();
                pg.setTotal(list == null ? 0 : list.size());
                pg.setPage(1);
                pg.setLimit(list == null ? 0 : list.size());
                pg.setTotalPages(1);
                resp.setPagination(pg);
                return resp;
            }

            // otherwise, read as object node and map fields manually to avoid recursion
            JsonNode node = p.getCodec().readTree(p);
            FetchLinksResponse resp = new FetchLinksResponse();

            if (node.has("status")) {
                resp.setStatus(node.get("status").asText(null));
            }
            if (node.has("statusId")) {
                resp.setStatusId(node.get("statusId").asText(null));
            }
            if (node.has("responseCode")) {
                resp.setResponseCode(node.get("responseCode").asText(null));
            }
            if (node.has("message")) {
                resp.setMessage(node.get("message").asText(null));
            }
            if (node.has("data") && !node.get("data").isNull()) {
                try {
                    com.fasterxml.jackson.databind.ObjectMapper mapper = (com.fasterxml.jackson.databind.ObjectMapper) p.getCodec();
                    List<PaymentLinkData> converted = mapper.convertValue(node.get("data"),
                            mapper.getTypeFactory().constructCollectionType(List.class, PaymentLinkData.class));
                    resp.setData(converted);
                } catch (IllegalArgumentException ex) {
                    // ignore and leave data null
                }
            }
            if (node.has("pagination") && !node.get("pagination").isNull()) {
                try {
                    com.fasterxml.jackson.databind.ObjectMapper mapper = (com.fasterxml.jackson.databind.ObjectMapper) p.getCodec();
                    PaginationData pg = mapper.convertValue(node.get("pagination"), PaginationData.class);
                    resp.setPagination(pg);
                } catch (IllegalArgumentException ex) {
                    // ignore
                }
            }

            return resp;
        }
    }
}
