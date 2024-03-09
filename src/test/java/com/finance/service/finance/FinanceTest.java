package com.finance.service.finance;

import com.finance.service.finance.enums.AccountType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class FinanceTest {
    @Test
    void 환전기능기본테스트Test() {
        // given
        Finance finance = new Finance(
                "1",
                "1",
                "K-IFRS 연결",
                "KRW",
                2020,
                1,
                3
        );

        finance.addAccount(AccountType.sales, 100_000l);
        finance.addAccount(AccountType.operatingProfit, 50_000l);
        finance.addAccount(AccountType.netProfit, 30_000l);
        finance.addAccount(AccountType.equity, 40_000l);
        finance.addAccount(AccountType.debt, 45_000l);
        finance.addAccount(AccountType.cashEquivalents, 25_000l);

        ExchangeOffice exchangeOffice = mock(ExchangeOfficeImpl.class);
        when(exchangeOffice.getAverageRate(2020, 1, 3, "KRW", "JPY")).thenReturn(110d/1200d);
        when(exchangeOffice.getFinalRate(2020, 1, "KRW", "JPY")).thenReturn(105d/1300d);

        // execute
        finance.changeCurrency("JPY", exchangeOffice);

        // expected
        assertTrue(finance.getCurrency().equals("JPY"));
        assertEquals(9166l, finance.getAccountAmount(AccountType.sales));
        assertEquals(4583l, finance.getAccountAmount(AccountType.operatingProfit));
        assertEquals(2750l, finance.getAccountAmount(AccountType.netProfit));
        assertEquals(3230l, finance.getAccountAmount(AccountType.equity));
        assertEquals(3634l, finance.getAccountAmount(AccountType.debt));
        assertEquals(2019l, finance.getAccountAmount(AccountType.cashEquivalents));
    }

    @Test
    void 손익계산서계정변동분Finance생성Test() {
        // given
        Finance twelveMonthFinance = new Finance(
                "null", "null", "null", "KRW", 2022,4, 12
        );
        twelveMonthFinance.addAccount(AccountType.sales, 200_000_000_000l);
        twelveMonthFinance.addAccount(AccountType.operatingProfit, 5_000l);
        twelveMonthFinance.addAccount(AccountType.netProfit, -123_456_789l);
        twelveMonthFinance.addAccount(AccountType.equity, 1_500_000_000_000l);
        twelveMonthFinance.addAccount(AccountType.debt, 3_000_000_000l);
        twelveMonthFinance.addAccount(AccountType.cashEquivalents, 100_000l);


        Finance nineMonthFinance = new Finance(
                "null", "null", "null", "KRW", 2022,3, 9
        );
        nineMonthFinance.addAccount(AccountType.sales, 123_456_789_000l);
        nineMonthFinance.addAccount(AccountType.operatingProfit, 3_000l);
        nineMonthFinance.addAccount(AccountType.netProfit, -12_345_678l);
        nineMonthFinance.addAccount(AccountType.equity, 1_500_000_000_000l);
        nineMonthFinance.addAccount(AccountType.debt, 3_000_000_000l);
        nineMonthFinance.addAccount(AccountType.cashEquivalents, 100_000l);

        // execute
        Finance incomeDiffFiance = Finance.generateIncomeDiffFinance(twelveMonthFinance, nineMonthFinance);

        // check
        assertEquals(200_000_000_000l - 123_456_789_000l, incomeDiffFiance.getAccountAmount(AccountType.sales));
        assertEquals(5_000l - 3_000l, incomeDiffFiance.getAccountAmount(AccountType.operatingProfit));
        assertEquals((-123_456_789l) - (-12_345_678l), incomeDiffFiance.getAccountAmount(AccountType.netProfit));
        assertEquals(1_500_000_000_000l, incomeDiffFiance.getAccountAmount(AccountType.equity));
        assertEquals(3_000_000_000l, incomeDiffFiance.getAccountAmount(AccountType.debt));
        assertEquals(100_000l, incomeDiffFiance.getAccountAmount(AccountType.cashEquivalents));
    }
}