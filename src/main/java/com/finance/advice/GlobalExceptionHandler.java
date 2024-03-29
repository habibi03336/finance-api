package com.finance.advice;

import com.finance.exception.DataNotExistException;
import com.finance.dto.ErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private String INTERNAL_SERVER_ERROR_MESSAGE = "서버 내부 오류가 발생했습니다.";
    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ErrorDTO handlePublicApiException(Exception ex)
    {
        return new ErrorDTO(
                HttpStatus.INTERNAL_SERVER_ERROR.value(), INTERNAL_SERVER_ERROR_MESSAGE);
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
