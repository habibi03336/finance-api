package com.finance.repository;

import com.finance.domain.DollarExchangeRateEntity;
import com.finance.domain.compositeKey.DollarExchangeRateKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DollarExchangeRateRepository extends JpaRepository<DollarExchangeRateEntity, DollarExchangeRateKey> {
    DollarExchangeRateEntity findByYearAndMonthAndCurrency(int year, int month, String currency);
}
