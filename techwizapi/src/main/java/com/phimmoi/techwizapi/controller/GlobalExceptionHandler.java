package com.phimmoi.techwizapi.controller;

import com.phimmoi.techwizapi.dto.ApiResponse;
import com.phimmoi.techwizapi.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ApiResponse.Response> handleBusinessException(BusinessException e) {
        log.error(e.getMessage());
        return ApiResponse.badRequest("Loi cmnr :((((((");
    }
}
