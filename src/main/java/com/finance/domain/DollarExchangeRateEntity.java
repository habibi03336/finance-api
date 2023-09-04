package com.finance.domain;

import com.finance.domain.compositeKey.DollarExchangeRateKey;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;

@Entity
@IdClass(DollarExchangeRateKey.class)
public class DollarExchangeRateEntity {
    @Id
    private int year;

    @Id
    private int month;

    @Id
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
