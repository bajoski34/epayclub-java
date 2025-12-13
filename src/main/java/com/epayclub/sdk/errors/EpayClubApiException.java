package com.epayclub.sdk.errors;

import java.util.Map;

/**
 * Exception thrown when the EpayClub API returns an error response.
 * This includes HTTP errors (4xx, 5xx) and API-level errors.
 */
public class EpayClubApiException extends EpayClubException {

    /**
     * Constructs a new EpayClubApiException.
     *
     * @param message       the error message
     * @param httpStatus    the HTTP status code
     * @param status        the EpayClub status field from response
     * @param statusId      the EpayClub statusId field from response
     * @param responseCode  the EpayClub responseCode field from response
     * @param requestMethod the HTTP method used
     * @param requestPath   the request URL/path
     * @param rawBody       the raw response body
     * @param parsedBody    the parsed response body as a Map
     */
    public EpayClubApiException(String message, Integer httpStatus, String status, String statusId,
                                String responseCode, String requestMethod, String requestPath,
                                String rawBody, Map<String, Object> parsedBody) {
        super(message, httpStatus, status, statusId, responseCode, requestMethod, requestPath, rawBody, parsedBody);
    }

    /**
     * Creates an EpayClubApiException from an HTTP response.
     *
     * @param httpStatus    the HTTP status code
     * @param requestMethod the HTTP method used
     * @param requestPath   the request URL/path
     * @param rawBody       the raw response body
     * @param parsedBody    the parsed response body as a Map
     * @return a new EpayClubApiException
     */
    public static EpayClubApiException fromResponse(int httpStatus, String requestMethod, String requestPath,
                                                     String rawBody, Map<String, Object> parsedBody) {
        String status = null;
        String statusId = null;
        String responseCode = null;
        String message = "API request failed with status " + httpStatus;

        if (parsedBody != null) {
            status = getStringOrNull(parsedBody.get("status"));
            statusId = getStringOrNull(parsedBody.get("statusId"));
            responseCode = getStringOrNull(parsedBody.get("responseCode"));
            
            Object msgObj = parsedBody.get("message");
            if (msgObj != null) {
                message = msgObj.toString();
            }
        }

        return new EpayClubApiException(message, httpStatus, status, statusId, responseCode,
                requestMethod, requestPath, rawBody, parsedBody);
    }

    private static String getStringOrNull(Object obj) {
        return obj != null ? obj.toString() : null;
    }

    @Override
    public String toString() {
        return String.format("EpayClubApiException{message='%s', httpStatus=%d, status='%s', " +
                        "statusId='%s', responseCode='%s', requestMethod='%s', requestPath='%s'}",
                getMessage(), getHttpStatus(), getStatus(), getStatusId(), getResponseCode(),
                getRequestMethod(), getRequestPath());
    }
}
