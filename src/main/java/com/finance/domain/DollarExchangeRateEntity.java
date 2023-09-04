package com.finance.domain;

public class DollarExchangeRateEntity {
    private int year;
    private int month;
    private String currency;
    private double rate;

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public String getCurrency() {
        return currency;
    }

    public double getRate() {
        return rate;
    }
}
