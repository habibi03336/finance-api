package com.finance.exception;

public class CompanyNotExistException extends DataNotExistException {
    public CompanyNotExistException() {}
    public CompanyNotExistException(String message) { super(message); }
    @Override
    protected String setCode() {
        return "dne02";
    }
}
