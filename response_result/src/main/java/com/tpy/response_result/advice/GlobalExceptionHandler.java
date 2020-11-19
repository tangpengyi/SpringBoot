package com.tpy.response_result.advice;

import com.tpy.response_result.api.ErrorResult;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResult validationError(MethodArgumentNotValidException ex) {
        ErrorResult errorResult = new ErrorResult();

        FieldError fieldError = ex.getBindingResult().getFieldError();
        errorResult.setMsg(fieldError.getField() + fieldError.getDefaultMessage());
        return errorResult;
    }
}

