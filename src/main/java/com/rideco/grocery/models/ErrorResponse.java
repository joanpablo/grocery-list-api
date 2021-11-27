package com.rideco.grocery.models;

/**
 * Represents a response when an error is thrown by one of the services.
 */
public class ErrorResponse {
    private String errorMessage;
    private ErrorResponseCode errorCode;

    public ErrorResponse() {
        this.errorMessage = "";
        this.errorCode = ErrorResponseCode.INTERNAL_SERVER_ERROR;
    }

    /**
     * Gets the message of the error.
     * @return a human understandable error text.
     */
    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * Sets the message of the error.
     * @param errorMessage the text of the message.
     * @return and instance of ErrorResponse for chain concatenation of methods.
     */
    public ErrorResponse setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
        return this;
    }

    /**
     * Gets the code of the error.
     * @return the code of the error.
     */
    public ErrorResponseCode getErrorCode() {
        return errorCode;
    }

    /**
     * Sets the code of the error.
     * @param errorCode the error's code
     * @return and instance of ErrorResponse for chain concatenation of methods.
     */
    public ErrorResponse setErrorCode(ErrorResponseCode errorCode) {
        this.errorCode = errorCode;
        return this;
    }
}
