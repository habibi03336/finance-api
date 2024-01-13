package com.finance.controller;

import com.finance.Exception.DataNotExistException;
import com.finance.dto.ErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ErrorDTO handlePublicApiException(Exception ex)
    {
        return new ErrorDTO(
                HttpStatus.INTERNAL_SERVER_ERROR.value(), "서버 내부 오류가 발생했습니다.");
    }

    @ExceptionHandler(value = DataNotExistException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorDTO handlePublicApiException(DataNotExistException ex)
    {
        return new ErrorDTO(
                HttpStatus.BAD_REQUEST.value(), ex.getMessage());
    }
}
