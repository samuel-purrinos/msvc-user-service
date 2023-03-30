package com.uichesoh.user.exceptions;

import com.uichesoh.user.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionController {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse> handlerResourceNotFoundException() {
        return ResponseEntity.ok(ApiResponse.builder().message("Resource not found").success(true).status(HttpStatus.NOT_FOUND).build());
    }
}
