package com.blueyonder.jwtloginservice.exceptions;

public class IncorrectPassword extends Exception{
    public IncorrectPassword() {
    }

    public IncorrectPassword(String message) {
        super(message);
    }

    public IncorrectPassword(String message, Throwable cause) {
        super(message, cause);
    }

    public IncorrectPassword(Throwable cause) {
        super(cause);
    }

    public IncorrectPassword(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
