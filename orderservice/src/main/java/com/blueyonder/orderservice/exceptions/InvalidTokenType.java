package com.blueyonder.orderservice.exceptions;

public class InvalidTokenType extends Exception{
    public InvalidTokenType() {
    }

    public InvalidTokenType(String message) {
        super(message);
    }

    public InvalidTokenType(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidTokenType(Throwable cause) {
        super(cause);
    }

    public InvalidTokenType(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
