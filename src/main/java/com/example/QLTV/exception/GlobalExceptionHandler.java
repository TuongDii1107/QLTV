package com.example.QLTV.exception;

import com.example.QLTV.ApiResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ApiException.class)
    public ApiResponse<?> handleApiException(ApiException ex) {
        return ApiResponse.error(
                ex.getErrorCode().getCode(),
                ex.getMessage()
        );
    }

    @ExceptionHandler(Exception.class)
    public ApiResponse<?> handleOther(Exception ex) {
        return ApiResponse.error("INTERNAL_ERROR", ex.getMessage());
    }
}
