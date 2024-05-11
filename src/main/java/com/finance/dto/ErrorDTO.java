package com.finance.dto;

public class ErrorDTO {
    private String errorCode;
    private String message;
    public ErrorDTO(String errorCode, String message){
        this.errorCode = errorCode;
        this.message = message;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getMessage() {
        return message;
    }
}
