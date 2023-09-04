package com.finance.repository;

import com.finance.domain.DollarExchangeRateEntity;

public interface DollarExchangeRateRepository {
    DollarExchangeRateEntity findByYearAndMonthAndCurrency(int year, int month, String currency);
}
