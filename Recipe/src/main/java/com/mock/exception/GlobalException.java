package com.mock.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalException {

    @ExceptionHandler(RecipeException.class)
    public ResponseEntity<MyErrorDetails> recipeException(RecipeException ie, WebRequest req) {
        System.out.println("inside myHandler method...");
        MyErrorDetails myErrorDetails = new MyErrorDetails(LocalDateTime.now(), ie.getMessage(), req.getDescription(false));
        ResponseEntity<MyErrorDetails> responseEntity = new ResponseEntity<>(myErrorDetails, HttpStatus.BAD_REQUEST);
        return responseEntity;
    }

    @ExceptionHandler(UserException.class)
    public ResponseEntity<MyErrorDetails> userException(UserException ie, WebRequest req) {
        System.out.println("inside myHandler method...");
        MyErrorDetails err = new MyErrorDetails(LocalDateTime.now(), ie.getMessage(), req.getDescription(false));
        ResponseEntity<MyErrorDetails> responseEntity = new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
        return responseEntity;
    }

    @ExceptionHandler(CurrentUserSessionException.class)
    public ResponseEntity<MyErrorDetails> currentUserSessionException(CurrentUserSessionException ie, WebRequest req) {
        System.out.println("inside myHandler method...");
        MyErrorDetails err = new MyErrorDetails(LocalDateTime.now(), ie.getMessage(), req.getDescription(false));
        ResponseEntity<MyErrorDetails> responseEntity = new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
        return responseEntity;
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<MyErrorDetails> noHandler(NoHandlerFoundException ie, WebRequest req) {
        System.out.println("inside myHandler method...");
        MyErrorDetails err = new MyErrorDetails(LocalDateTime.now(), ie.getMessage(), req.getDescription(false));
        ResponseEntity<MyErrorDetails> responseEntity = new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
        return responseEntity;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<MyErrorDetails> validationHandler(MethodArgumentNotValidException me) {
        System.out.println("inside myHandler method...");
        MyErrorDetails err = new MyErrorDetails(LocalDateTime.now(), "Validation Error", me.getBindingResult().getFieldError().getDefaultMessage());
        ResponseEntity<MyErrorDetails> responseEntity = new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
        return responseEntity;
    }
}
