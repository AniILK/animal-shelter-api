package org.projecttest.animalshelterapi.controllers;


import org.projecttest.animalshelterapi.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalControllerAdvice {

    // Handle not found errors (404)
    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public String handleResourceNotFoundException(ResourceNotFoundException ex) {
        return ex.getMessage();
    }

    // Handle bad requests (400)
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public String handleIllegalArgumentException(IllegalArgumentException ex) {
        return ex.getMessage();
    }

    // Handle any other unexpected server errors (500)
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public String handleGenericException(Exception ex) {
        return STR."Internal server error: \{ex.getMessage()}";
    }
}
