package com.finance.domain.compositeKey;

import java.io.Serializable;

public class DollarExchangeRateKey implements Serializable {
    private int year;
    private int month;
    private String currency;

    public DollarExchangeRateKey(int year, int month, String currency) {
        this.year = year;
        this.month = month;
        this.currency = currency;
    }

    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (o == null) return false;
        if (this.getClass() != o.getClass()) return false;
        DollarExchangeRateKey dollarExchangeRateKey = (DollarExchangeRateKey) o;
        return dollarExchangeRateKey.year == year && dollarExchangeRateKey.month == month && dollarExchangeRateKey.currency.equals(currency);
    }

}
