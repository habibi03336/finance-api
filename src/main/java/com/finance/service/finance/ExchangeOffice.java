package com.finance.service.finance;

public interface ExchangeOffice {
    public double getAverageRate(int year, int quarter, int cumulativeMonth, String fromCurrency, String toCurrency);
    public double getFinalRate(int year, int quarter, String fromCurrency, String toCurrency);
}
