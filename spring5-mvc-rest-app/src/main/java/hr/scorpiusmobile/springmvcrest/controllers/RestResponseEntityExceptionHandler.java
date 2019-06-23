package hr.scorpiusmobile.springmvcrest.controllers;

import hr.scorpiusmobile.springmvcrest.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Object> handleNotFoundException(Exception exception, WebRequest webRequest) {
        return new ResponseEntity<Object>("Resource not found", HttpStatus.NOT_FOUND);
        //here instead of string "Resource not found" we can return rich JSON object with all info what is wrong
    }
}
