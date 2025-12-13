package com.epayclub.sdk.errors;

import java.util.Map;

/**
 * Base exception for all EpayClub SDK errors.
 * Contains HTTP status, EpayClub response fields, request details, and raw/parsed body.
 */
public class EpayClubException extends RuntimeException {

    private final Integer httpStatus;
    private final String status;
    private final String statusId;
    private final String responseCode;
    private final String requestMethod;
    private final String requestPath;
    private final String rawBody;
    private final Map<String, Object> parsedBody;

    /**
     * Constructs a new EpayClubException.
     *
     * @param message       the error message
     * @param httpStatus    the HTTP status code (may be null for client-side errors)
     * @param status        the EpayClub status field from response
     * @param statusId      the EpayClub statusId field from response
     * @param responseCode  the EpayClub responseCode field from response
     * @param requestMethod the HTTP method used
     * @param requestPath   the request URL/path
     * @param rawBody       the raw response body
     * @param parsedBody    the parsed response body as a Map
     */
    public EpayClubException(String message, Integer httpStatus, String status, String statusId,
                             String responseCode, String requestMethod, String requestPath,
                             String rawBody, Map<String, Object> parsedBody) {
        super(message);
        this.httpStatus = httpStatus;
        this.status = status;
        this.statusId = statusId;
        this.responseCode = responseCode;
        this.requestMethod = requestMethod;
        this.requestPath = requestPath;
        this.rawBody = rawBody;
        this.parsedBody = parsedBody;
    }

    /**
     * Constructs a new EpayClubException with a cause.
     *
     * @param message the error message
     * @param cause   the underlying cause
     */
    public EpayClubException(String message, Throwable cause) {
        super(message, cause);
        this.httpStatus = null;
        this.status = null;
        this.statusId = null;
        this.responseCode = null;
        this.requestMethod = null;
        this.requestPath = null;
        this.rawBody = null;
        this.parsedBody = null;
    }

    /**
     * Constructs a new EpayClubException with just a message.
     *
     * @param message the error message
     */
    public EpayClubException(String message) {
        super(message);
        this.httpStatus = null;
        this.status = null;
        this.statusId = null;
        this.responseCode = null;
        this.requestMethod = null;
        this.requestPath = null;
        this.rawBody = null;
        this.parsedBody = null;
    }

    /**
     * Returns the HTTP status code.
     *
     * @return the HTTP status code, or null if not applicable
     */
    public Integer getHttpStatus() {
        return httpStatus;
    }

    /**
     * Returns the EpayClub status field.
     *
     * @return the status field
     */
    public String getStatus() {
        return status;
    }

    /**
     * Returns the EpayClub statusId field.
     *
     * @return the statusId field
     */
    public String getStatusId() {
        return statusId;
    }

    /**
     * Returns the EpayClub responseCode field.
     *
     * @return the responseCode field
     */
    public String getResponseCode() {
        return responseCode;
    }

    /**
     * Returns the HTTP method used for the request.
     *
     * @return the request method
     */
    public String getRequestMethod() {
        return requestMethod;
    }

    /**
     * Returns the request URL/path.
     *
     * @return the request path
     */
    public String getRequestPath() {
        return requestPath;
    }

    /**
     * Returns the raw response body.
     *
     * @return the raw body
     */
    public String getRawBody() {
        return rawBody;
    }

    /**
     * Returns the parsed response body as a Map.
     *
     * @return the parsed body
     */
    public Map<String, Object> getParsedBody() {
        return parsedBody;
    }

    @Override
    public String toString() {
        return String.format("EpayClubException{message='%s', httpStatus=%d, status='%s', " +
                        "statusId='%s', responseCode='%s', requestMethod='%s', requestPath='%s'}",
                getMessage(), httpStatus, status, statusId, responseCode, requestMethod, requestPath);
    }
}
