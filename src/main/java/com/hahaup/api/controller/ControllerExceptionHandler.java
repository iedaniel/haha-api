package com.hahaup.api.controller;

import com.hahaup.api.model.dto.BaseResponse;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public BaseResponse<?> handle(Exception e) {
        if (e instanceof DataIntegrityViolationException) {
            if (e.getCause() instanceof ConstraintViolationException) {
                ConstraintViolationException ee = (ConstraintViolationException) e.getCause();
                return new BaseResponse<>(new RuntimeException(ee.getSQLException()));
            }
        }
        return new BaseResponse<>(e);
    }
}