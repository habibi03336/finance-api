package com.finance.advice;

import com.finance.exception.DataNotExistException;
import com.finance.dto.ErrorDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    private final String INTERNAL_SERVER_ERROR_MESSAGE = "서버 내부 오류가 발생했습니다.";
    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ErrorDTO handlePublicApiException(Exception ex)
    {
        log.error("Internal Server Error", ex);
        return new ErrorDTO(
                "999", INTERNAL_SERVER_ERROR_MESSAGE);
    }

    @ExceptionHandler(value = DataNotExistException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorDTO handlePublicApiException(DataNotExistException ex)
    {
        log.warn(ex.toString());
        return new ErrorDTO(
                ex.getCode(), ex.getMessage());
    }
}
