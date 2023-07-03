package com.fame.famewheels.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.fame.famewheels.dto.ApiResponse;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponse> ResourceNotFoundException(ResourceNotFoundException ex)
	{
		String message = ex.getMessage();
		ApiResponse apiResponse = new ApiResponse(message,false);
		return new ResponseEntity<ApiResponse>(apiResponse,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ApiResponse> NullExceptions(DataIntegrityViolationException e, HttpServletRequest request) {
//		String msg=e.getMessage();
		String msg="Required fields could not be null!! Please fill all required fields!";

		ApiResponse apiResonse= new ApiResponse(msg, false);
//        return exceptionInfoHandler.getErrorInfoResponseEntity(req, e, EXCEPTION_DUPLICATE_EMAIL, HttpStatus.CONFLICT);
		return new ResponseEntity<ApiResponse>(apiResonse, HttpStatus.BAD_REQUEST);
    }
	
	

	    @ExceptionHandler(MethodArgumentNotValidException.class)
	    public ResponseEntity<Map<String, String>> handleValidationExceptions(
	      MethodArgumentNotValidException ex) {
	    	Map<String, String> errors=new HashMap<>();
	        ex.getBindingResult().getAllErrors().forEach((error) -> {
	            String fieldName = ((FieldError) error).getField();
	            String errorMessage = error.getDefaultMessage();
	            errors.put(fieldName, errorMessage);
	        });
	        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
	    }
}
