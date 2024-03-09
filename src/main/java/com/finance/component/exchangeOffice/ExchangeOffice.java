package com.finance.component.exchangeOffice;

public interface ExchangeOffice {
    double getAverageRate(int year, int quarter, int cumulativeMonth, String fromCurrency, String toCurrency);
    double getFinalRate(int year, int quarter, String fromCurrency, String toCurrency);
}
