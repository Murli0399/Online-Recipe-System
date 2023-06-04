package com.mock.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class GlobalException {

	@ExceptionHandler(RecipeException.class)
	public ResponseEntity<MyErrorDetails> bookException(RecipeException ie,WebRequest req)  {
		System.out.println("inside myHandler method...");
		
	MyErrorDetails err=new MyErrorDetails(LocalDateTime.now(), ie.getMessage(), req.getDescription(false));

		ResponseEntity<MyErrorDetails> re=new ResponseEntity<>(err,HttpStatus.BAD_REQUEST);
		
		
		
		return re;	
	}
	@ExceptionHandler(UserException.class)
	public ResponseEntity<MyErrorDetails> userException(UserException ie,WebRequest req)  {
		System.out.println("inside myHandler method...");
		
		MyErrorDetails err=new MyErrorDetails(LocalDateTime.now(), ie.getMessage(), req.getDescription(false));
		
		ResponseEntity<MyErrorDetails> re=new ResponseEntity<>(err,HttpStatus.BAD_REQUEST);
		
		
		
		return re;	
	}
	@ExceptionHandler(CurrentUserSessionException.class)
	public ResponseEntity<MyErrorDetails> userException(CurrentUserSessionException ie,WebRequest req)  {
		System.out.println("inside myHandler method...");
		
		MyErrorDetails err=new MyErrorDetails(LocalDateTime.now(), ie.getMessage(), req.getDescription(false));
		
		ResponseEntity<MyErrorDetails> re=new ResponseEntity<>(err,HttpStatus.BAD_REQUEST);
		
		
		
		return re;	
	}
	@ExceptionHandler(NoHandlerFoundException.class)
	public ResponseEntity<MyErrorDetails> noHandler(NoHandlerFoundException ie,WebRequest req)  {
		System.out.println("inside myHandler method...");
		
		MyErrorDetails err=new MyErrorDetails(LocalDateTime.now(), ie.getMessage(), req.getDescription(false));
		
		ResponseEntity<MyErrorDetails> re=new ResponseEntity<>(err,HttpStatus.BAD_REQUEST);
		
		
		
		return re;	
	}
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<MyErrorDetails> validationHandler(MethodArgumentNotValidException me)  {
		System.out.println("inside myHandler method...");
		
		MyErrorDetails err=new MyErrorDetails(LocalDateTime.now(),"Validation Error",me.getBindingResult().getFieldError().getDefaultMessage());
		
		ResponseEntity<MyErrorDetails> re=new ResponseEntity<>(err,HttpStatus.BAD_REQUEST);
		
		
		
		return re;	
	}
}
