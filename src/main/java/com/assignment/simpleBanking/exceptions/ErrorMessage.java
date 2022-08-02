package com.assignment.simpleBanking.exceptions;

public enum ErrorMessage implements BaseErrorMessage{
    BALANCE_CANNOT_BE_INSUFFICIENT("Balance cannot be insufficient")
    ;
    private String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "ErrorMessage{" +
                "message='" + message + '\'' +
                '}';
    }

    @Override
    public String getMessage() {
        return message;
    }
}
