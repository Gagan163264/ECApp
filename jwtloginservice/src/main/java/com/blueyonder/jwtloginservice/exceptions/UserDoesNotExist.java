package com.blueyonder.jwtloginservice.exceptions;

public class UserDoesNotExist extends Exception{
    public UserDoesNotExist() {
    }

    public UserDoesNotExist(String message) {
        super(message);
    }

    public UserDoesNotExist(String message, Throwable cause) {
        super(message, cause);
    }

    public UserDoesNotExist(Throwable cause) {
        super(cause);
    }

    public UserDoesNotExist(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
