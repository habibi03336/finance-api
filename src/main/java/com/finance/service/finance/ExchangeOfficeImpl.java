package com.finance.service.finance;

import com.finance.repository.DollarExchangeRateRepository;

public class ExchangeOfficeImpl implements ExchangeOffice {

    private DollarExchangeRateRepository dollarExchangeRateRepository;

    public ExchangeOfficeImpl(DollarExchangeRateRepository repo){
        dollarExchangeRateRepository = repo;
    }

    public double getAverageRate(int year, int quarter, int cumulativeMonth, String fromCurrency, String toCurrency){
        double dollarToFromCurrencyRate = 0d;
        int lastMonth = quarter * 3;
        if(fromCurrency.equals("USD")){
            dollarToFromCurrencyRate = 1;
        } else {
            dollarToFromCurrencyRate = getAverageRateFromDollar(year, lastMonth, cumulativeMonth, fromCurrency);
        }
        if(toCurrency.equals("USD")){
            return (1 / dollarToFromCurrencyRate);
        }
        double dollarToToCurrencyRate = getAverageRateFromDollar(year, lastMonth, cumulativeMonth, toCurrency);
        return  (1 / dollarToFromCurrencyRate) * dollarToToCurrencyRate;
    }

    private double getAverageRateFromDollar(int year, int lastMonth, int cumulativeMonth, String currency) {
        double averageRate = 0d;
        for(int i = 0; i < cumulativeMonth; i += 1){
            int months = year * 12 + lastMonth - 1 - i;
            int month = months % 12 + 1;
            int targetYear = months / 12;
            averageRate += dollarExchangeRateRepository.findByYearAndMonthAndCurrency(targetYear, month , currency).getRate();
        }
        averageRate = averageRate / cumulativeMonth;
        return averageRate;
    }

    public double getFinalRate(int year, int quarter, String fromCurrency, String toCurrency){
        double dollarToFromCurrencyRate;
        int lastMonth = quarter * 3;
        if(fromCurrency.equals("USD")){
            dollarToFromCurrencyRate = 1;
        } else {
            dollarToFromCurrencyRate = dollarExchangeRateRepository.findByYearAndMonthAndCurrency(year, lastMonth, fromCurrency).getRate();
        }
        if(toCurrency.equals("USD")){
            return (1 / dollarToFromCurrencyRate);
        }
        double dollarToToCurrencyRate = dollarExchangeRateRepository.findByYearAndMonthAndCurrency(year, lastMonth, toCurrency).getRate();
        return  (1 / dollarToFromCurrencyRate) * dollarToToCurrencyRate;
    }
}
