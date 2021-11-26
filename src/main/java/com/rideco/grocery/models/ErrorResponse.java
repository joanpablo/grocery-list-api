package com.rideco.grocery.models;

public class ErrorResponse {
    private String errorMessage;
    private ErrorResponseCode errorCode;

    public ErrorResponse(final String errorMessage, final ErrorResponseCode errorCode) {
        this.errorMessage = errorMessage;
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public ErrorResponseCode getErrorCode() {
        return errorCode;
    }
}
