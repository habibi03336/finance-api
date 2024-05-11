package com.finance.exception;

public abstract class DataNotExistException extends Exception {
    private String code;
    protected abstract String setCode();
    public DataNotExistException() {
        code = setCode();
    }
    public DataNotExistException(String message) {
        super(message);
        code = setCode();
    }
    public String getCode(){
        return code;
    }
}
