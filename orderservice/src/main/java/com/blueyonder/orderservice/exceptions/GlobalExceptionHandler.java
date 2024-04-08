package com.blueyonder.orderservice.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    private static class Response {
        private String message;
    }

    private ResponseEntity<Object> makeResponse(ErrorObject obj) {
        return new ResponseEntity<>(new Response(obj.getMessage()), obj.getStatus());
    }


    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Object> handleCategoryNotFoundException(Exception ex) {
        ErrorObject errorObj = new ErrorObject(HttpStatus.NOT_FOUND, ex);
        return makeResponse(errorObj);
    }

    @ExceptionHandler(InvalidChangeInCart.class)
    public ResponseEntity<Object> cartZero(Exception ex) {
        ErrorObject errorObj = new ErrorObject(HttpStatus.NOT_ACCEPTABLE, ex);
        return makeResponse(errorObj);
    }

    @ExceptionHandler(TooManyItemsException.class)
    public void handler(Exception ex){
        return;
    }
}
