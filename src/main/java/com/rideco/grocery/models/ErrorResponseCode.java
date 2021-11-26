package com.rideco.grocery.models;

public enum ErrorResponseCode {
    INTERNAL_SERVER_ERROR("INTERNAL_SERVER_ERROR"),
    VALIDATION_ERROR("VALIDATION_ERROR");

    private final String value;

    ErrorResponseCode(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return this.getValue();
    }
}
