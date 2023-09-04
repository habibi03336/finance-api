package com.finance.service.finance;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class FinanceTest {
    @Test
    void 환전기능기본테스트Test() {
        // given
        List<Account> accounts = new ArrayList<>();
        accounts.add(new Account(Account.Type.sales, 100_000));
        accounts.add(new Account(Account.Type.operatingProfit, 50_000));
        accounts.add(new Account(Account.Type.netProfit, 30_000));
        accounts.add(new Account(Account.Type.equity, 40_000));
        accounts.add(new Account(Account.Type.debt, 45_000));
        accounts.add(new Account(Account.Type.cashEquivalents, 25_000));
        Finance finance = new Finance(
                "1",
                "1",
                "K-IFRS 연결",
                "KRW",
                2020,
                1,
                3,
                accounts
        );

        ExchangeOffice exchangeOffice = mock(ExchangeOfficeImpl.class);
        when(exchangeOffice.getAverageRate(2020, 1, 3, "KRW", "JPY")).thenReturn(110d/1200d);
        when(exchangeOffice.getFinalRate(2020, 1, "KRW", "JPY")).thenReturn(105d/1300d);

        // execute
        finance.changeCurrency("JPY", exchangeOffice);
        accounts = finance.getAccounts();
        Map<Account.Type, Long> accountAmount = new HashMap<>();
        for(Account account: accounts){
            accountAmount.put(account.getType(), account.getAmount());
        }

        // expected
        assertTrue(finance.getCurrency().equals("JPY"));
        assertEquals(9166l, accountAmount.get(Account.Type.sales));
        assertEquals(4583l, accountAmount.get(Account.Type.operatingProfit));
        assertEquals(2750l, accountAmount.get(Account.Type.netProfit));
        assertEquals(3230l, accountAmount.get(Account.Type.equity));
        assertEquals(3634l, accountAmount.get(Account.Type.debt));
        assertEquals(2019l, accountAmount.get(Account.Type.cashEquivalents));
    }

    @Test
    void 손익계산서계정변동분Finance생성Test() {
        // given
        List<Account> twelveMonthAccounts = new ArrayList<>();
        twelveMonthAccounts.add(new Account(Account.Type.sales, 200_000_000_000l));
        twelveMonthAccounts.add(new Account(Account.Type.operatingProfit, 5_000l));
        twelveMonthAccounts.add(new Account(Account.Type.netProfit, -123_456_789l));
        twelveMonthAccounts.add(new Account(Account.Type.equity, 1_500_000_000_000l));
        twelveMonthAccounts.add(new Account(Account.Type.debt, 3_000_000_000l));
        twelveMonthAccounts.add(new Account(Account.Type.cashEquivalents, 100_000l));
        Finance twelveMonthFinance = new Finance(
                "null", "null", "null", "KRW", 2022,4, 12, twelveMonthAccounts
        );

        List<Account> nineMonthAccounts = new ArrayList<>();
        nineMonthAccounts.add(new Account(Account.Type.sales, 123_456_789_000l));
        nineMonthAccounts.add(new Account(Account.Type.operatingProfit, 3_000l));
        nineMonthAccounts.add(new Account(Account.Type.netProfit, -12_345_678l));
        twelveMonthAccounts.add(new Account(Account.Type.equity, 1_500_000_000_000l));
        twelveMonthAccounts.add(new Account(Account.Type.debt, 3_000_000_000l));
        twelveMonthAccounts.add(new Account(Account.Type.cashEquivalents, 100_000l));
        Finance nineMonthFinance = new Finance(
                "null", "null", "null", "KRW", 2022,3, 9, nineMonthAccounts
        );

        // execute
        Finance incomeDiffFiance = Finance.generateIncomeDiffFinance(twelveMonthFinance, nineMonthFinance);
        List<Account> accounts = incomeDiffFiance.getAccounts();
        Map<Account.Type, Long> accountAmount = new HashMap<>();
        for(Account account: accounts){
            accountAmount.put(account.getType(), account.getAmount());
        }

        // check
        assertEquals(200_000_000_000l - 123_456_789_000l, accountAmount.get(Account.Type.sales));
        assertEquals(5_000l - 3_000l, accountAmount.get(Account.Type.operatingProfit));
        assertEquals((-123_456_789l) - (-12_345_678l), accountAmount.get(Account.Type.netProfit));
        assertEquals(1_500_000_000_000l, accountAmount.get(Account.Type.equity));
        assertEquals(3_000_000_000l, accountAmount.get(Account.Type.debt));
        assertEquals(100_000l, accountAmount.get(Account.Type.cashEquivalents));
    }
}