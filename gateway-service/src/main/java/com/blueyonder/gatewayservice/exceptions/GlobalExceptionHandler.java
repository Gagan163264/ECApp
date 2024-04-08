package com.blueyonder.gatewayservice.exceptions;

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
    private static class Response{
        private String message;

    }

    private ResponseEntity<Object> makeResponse(ErrorObject obj){
        return new ResponseEntity<>(new Response(obj.getMessage()),obj.getStatus());
    }
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Object> handleUnauthAccess(Exception ex){
        ErrorObject errorObj = new ErrorObject(HttpStatus.UNAUTHORIZED, ex);
        return new ResponseEntity<Object>(errorObj, errorObj.getStatus());
    }
}
