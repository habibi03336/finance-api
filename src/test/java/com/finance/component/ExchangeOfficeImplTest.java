package com.finance.component;

import com.finance.component.exchangeOffice.ExchangeOffice;
import com.finance.component.exchangeOffice.ExchangeOfficeImpl;
import com.finance.domain.DollarExchangeRateEntity;
import com.finance.repository.DollarExchangeRateRepository;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ExchangeOfficeImplTest {
    @Test
    void 평균환율계산Test() {
        // given
        DollarExchangeRateRepository dollarExchangeRateRepository = mock(DollarExchangeRateRepository.class);
        double[] rates = new double[]{ 1200d, 1100d, 1150d, 1120.5d, 1150.92d, 1098.12d };
        List<DollarExchangeRateEntity> exchangeRateEntities = new ArrayList<>();
        for(double rate : rates){
            DollarExchangeRateEntity entity = mock(DollarExchangeRateEntity.class);
            when(entity.getRate()).thenReturn(rate);
            exchangeRateEntities.add(entity);
        }
        when(dollarExchangeRateRepository.findByYearAndMonthAndCurrency(2022, 3, "KRW")).thenReturn(exchangeRateEntities.get(0));
        when(dollarExchangeRateRepository.findByYearAndMonthAndCurrency(2022, 2, "KRW")).thenReturn(exchangeRateEntities.get(1));
        when(dollarExchangeRateRepository.findByYearAndMonthAndCurrency(2022, 1, "KRW")).thenReturn(exchangeRateEntities.get(2));
        when(dollarExchangeRateRepository.findByYearAndMonthAndCurrency(2021, 12, "KRW")).thenReturn(exchangeRateEntities.get(3));
        when(dollarExchangeRateRepository.findByYearAndMonthAndCurrency(2021, 11, "KRW")).thenReturn(exchangeRateEntities.get(4));
        when(dollarExchangeRateRepository.findByYearAndMonthAndCurrency(2021, 10, "KRW")).thenReturn(exchangeRateEntities.get(5));
        ExchangeOffice eo = new ExchangeOfficeImpl(dollarExchangeRateRepository);

        // execute
        double averageRate = eo.getAverageRate(2022, 1, 6,"USD", "KRW");

        // check
        double expected = Arrays.stream(rates).sum() / 6;
        assertEquals(expected, averageRate);
    }
}
