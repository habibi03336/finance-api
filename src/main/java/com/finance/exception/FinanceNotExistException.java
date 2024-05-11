package com.finance.exception;

public class FinanceNotExistException extends DataNotExistException {
    public FinanceNotExistException() {}
    public FinanceNotExistException(String message) { super(message); }
    @Override
    protected String setCode() {
        return "dne01";
    }
}
