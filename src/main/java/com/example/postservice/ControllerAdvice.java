package com.example.postservice;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@org.springframework.web.bind.annotation.ControllerAdvice
@ResponseBody
public class ControllerAdvice {

    @ExceptionHandler(PostNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleException(PostNotFoundException exception) {
        return String.format("%s",exception.getMessage()) ;
    }
}
