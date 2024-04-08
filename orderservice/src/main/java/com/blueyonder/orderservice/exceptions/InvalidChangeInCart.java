package com.blueyonder.orderservice.exceptions;

public class InvalidChangeInCart extends Exception{
    public InvalidChangeInCart() {
    }

    public InvalidChangeInCart(String message) {
        super(message);
    }

    public InvalidChangeInCart(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidChangeInCart(Throwable cause) {
        super(cause);
    }

    public InvalidChangeInCart(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
