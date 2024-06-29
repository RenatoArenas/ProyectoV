package com.cibertec.proyectov.security;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.cibertec.proyectov.dto.ResponseData;
import com.cibertec.proyectov.exceptions.UniqueException;

import io.jsonwebtoken.ExpiredJwtException;


@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler
{

    @ExceptionHandler({Exception.class})
    public ResponseEntity<ResponseData<Object>> handleGeneralException(Exception exception, WebRequest webRequest)
    {
    	if (exception instanceof ExpiredJwtException) {
    		 ResponseData<Object> res = new ResponseData<Object>(exception.getMessage(), 0);
    	
        	return new ResponseEntity<>(res, new HttpHeaders(), HttpStatus.UNAUTHORIZED);
        }

		ResponseData<Object> res = new ResponseData<Object>("Ha ocurrido un error", 0);
    	return new ResponseEntity<>(res, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @ExceptionHandler({AuthenticationException.class})
    public ResponseEntity<ResponseData<Object>> handleAccessDeniedException(Exception exception, WebRequest webRequest)
    {
    	
    	ResponseData<Object> res = new ResponseData<Object>(exception.getMessage(), 0);
    	
        return new ResponseEntity<>(res, new HttpHeaders(), HttpStatus.UNAUTHORIZED);
    }
    
    @ExceptionHandler({RuntimeException.class})
    public ResponseEntity<ResponseData<Object>> handleRunTimeException(RuntimeException exception, WebRequest webRequest)
    {
    	
    	ResponseData<Object> res = new ResponseData<Object>(exception.getMessage(), 0);
    	
        return new ResponseEntity<>(res, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }
    
    @ExceptionHandler({UniqueException.class})
    public ResponseEntity<ResponseData<Object>> handleUniqueException(UniqueException ex, WebRequest webRequest)
    {
    	
    	Map<String,String> errorMap=new HashMap<>();
    	
        errorMap.put(ex.getProperty(), ex.getMessage());
    	ResponseData<Object> res = new ResponseData<Object>(ex.getMessage(), 0, errorMap);
    	
        return new ResponseEntity<>(res, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }
    
    @Override
    protected ResponseEntity<Object>  handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
    	Map<String,String> errorMap=new HashMap<>();
    	
    	List<FieldError> errors = ex.getBindingResult().getFieldErrors();
    	
    	errors.forEach(error->
        {
            errorMap.put(error.getField(),error.getDefaultMessage());
        });
    	
    	ResponseData<Object> res = new ResponseData<Object>(errors.get(0).getDefaultMessage(), 0, errorMap);
    	
        return new ResponseEntity<>(res, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }


}