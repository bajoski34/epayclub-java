package com.epayclub.sdk.errors;

/**
 * Exception thrown for client-side errors such as validation failures,
 * configuration errors, or serialization issues.
 */
public class EpayClubClientException extends EpayClubException {

    /**
     * Constructs a new EpayClubClientException.
     *
     * @param message the error message
     */
    public EpayClubClientException(String message) {
        super(message);
    }

    /**
     * Constructs a new EpayClubClientException with a cause.
     *
     * @param message the error message
     * @param cause   the underlying cause
     */
    public EpayClubClientException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Creates a validation error for a required field.
     *
     * @param fieldName the name of the field
     * @return a new EpayClubClientException
     */
    public static EpayClubClientException requiredField(String fieldName) {
        return new EpayClubClientException(
                String.format("Required field '%s' is null or empty", fieldName));
    }

    /**
     * Creates an error for missing encryption key.
     *
     * @return a new EpayClubClientException
     */
    public static EpayClubClientException missingEncryptionKey() {
        return new EpayClubClientException(
                "Encryption was requested but no merchantEncryptionKey is configured on the client");
    }

    /**
     * Creates a serialization error.
     *
     * @param cause the underlying cause
     * @return a new EpayClubClientException
     */
    public static EpayClubClientException serializationError(Throwable cause) {
        return new EpayClubClientException("Failed to serialize request body", cause);
    }

    /**
     * Creates a deserialization error.
     *
     * @param cause the underlying cause
     * @return a new EpayClubClientException
     */
    public static EpayClubClientException deserializationError(Throwable cause) {
        return new EpayClubClientException("Failed to deserialize response body", cause);
    }

    @Override
    public String toString() {
        return String.format("EpayClubClientException{message='%s'}", getMessage());
    }
}
