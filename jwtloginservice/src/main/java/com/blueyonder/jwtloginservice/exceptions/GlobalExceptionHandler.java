package com.blueyonder.jwtloginservice.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
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

    @ExceptionHandler(InvalidTokenException.class)
    public ResponseEntity<Object> invalidToken(Exception ex){
        ErrorObject errorObj = new ErrorObject(HttpStatus.UNAUTHORIZED, ex);
        return makeResponse(errorObj);
    }

    @ExceptionHandler(UsernameAlreadyExistsException.class)
    public ResponseEntity<Object> userExists(Exception ex){
        ErrorObject errorObj = new ErrorObject(HttpStatus.CONFLICT, ex);
        return makeResponse(errorObj);
    }

    @ExceptionHandler(UserDoesNotExist.class)
    public ResponseEntity<Object> usernotExists(Exception ex){
        ErrorObject errorObj = new ErrorObject(HttpStatus.NOT_FOUND, ex);
        return makeResponse(errorObj);
    }

    @ExceptionHandler(IncorrectPassword.class)
    public ResponseEntity<Object> handleIncorrectPassword(Exception ex){
        ErrorObject errorObj = new ErrorObject(HttpStatus.UNAUTHORIZED, ex);
        return makeResponse(errorObj);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<Object> handleIncorrectCredentials(Exception ex){
        ErrorObject errorObj = new ErrorObject(HttpStatus.UNAUTHORIZED, ex);
        return makeResponse(errorObj);
    }

}
