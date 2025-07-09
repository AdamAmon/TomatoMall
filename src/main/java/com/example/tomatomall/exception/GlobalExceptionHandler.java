package com.example.tomatomall.exception;

import com.example.tomatomall.vo.Response;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = TomatoException.class)
    public Response<String> handleAIExternalExpection(TomatoException tomato){
        tomato.printStackTrace();
        return Response.buildFailure(tomato.getMessage(),"401");
    }
}
