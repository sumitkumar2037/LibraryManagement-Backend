package com.jwt.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.jwt.dto.ErrorDto;

@RestControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(AppException.class)
    public ResponseEntity<?> handleAppException(AppException ex) {
        return ResponseEntity
                .status(ex.getStatus())
                .body(new ErrorDto(ex.getMessage(), ex.getStatus().value()));
    }

    
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGeneralException(Exception ex) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorDto("Something went wrong", 500));
    }

}
