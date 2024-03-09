package com.finance.service.finance;

import com.finance.component.exchangeOffice.ExchangeOffice;
import com.finance.service.finance.enums.AccountType;

import java.util.ArrayList;
import java.util.List;

public class Finance {
    private String corpCode;
    private String statementCode;
    private String statementType;
    private String currency;
    private int cumulativeMonth;
    private int year;
    private int quarter;
    private List<Account> accounts;

    public Finance(String corpCode, String statementCode, String statementType, String currency, int year, int quarter, int cumulativeMonth){
        this.corpCode = corpCode;
        this.statementCode = statementCode;
        this.statementType = statementType;
        this.currency = currency;
        this.year = year;
        this.quarter = quarter;
        this.cumulativeMonth = cumulativeMonth;
        this.accounts = new ArrayList<>();
    }

    public void changeCurrency(String toCurrency, ExchangeOffice ex){
        if(toCurrency.equals(currency)) return;
        double averageRatio = ex.getAverageRate(year, quarter, cumulativeMonth, currency, toCurrency);
        double finalRatio = ex.getFinalRate(year, quarter, currency, toCurrency);
        currency = toCurrency;
        for(Account acc : accounts){
            if(acc.type.isIncome()){
                acc.amount = (long)(acc.amount * averageRatio);
            } else {
                acc.amount = (long)(acc.amount * finalRatio);
            }
        }
    }

    public static Finance generateIncomeDiffFinance(Finance operand1, Finance operand2){
        List<Account> accounts  = operand1.accounts;
        List<Account> accounts2 = operand2.accounts;
        Finance newFinance = new Finance(
                operand1.getCorpCode(),
                operand1.getStatementCode(),
                operand1.getStatementType(),
                operand1.getCurrency(),
                operand1.getYear(),
                operand1.getQuarter(),
                operand1.getCumulativeMonth() - operand2.getCumulativeMonth()
        );
        for(Account acc : accounts){
            if(acc.type.isIncome()){
                for(Account acc2: accounts2){
                    if(acc.type.equals(acc2.type)){
                        newFinance.addAccount(acc.type, acc.amount - acc2.amount);
                        break;
                    }
                }
            } else {
                newFinance.addAccount(acc.type, acc.amount);
            }
        }
        return newFinance;
    }

    public void addAccount(AccountType accountType, long amount){
        accounts.add(new Account(accountType, amount));
    }

    public String getCorpCode() {
        return corpCode;
    }

    public String getStatementCode() {
        return statementCode;
    }

    public String getStatementType() {
        return statementType;
    }

    public String getCurrency() {
        return currency;
    }

    public int getYear() {
        return year;
    }

    public int getQuarter() {
        return quarter;
    }

    public int getCumulativeMonth(){
        return cumulativeMonth;
    }

    public long getAccountAmount(AccountType accountType){
        return accounts.stream().filter((acc) -> acc.type.equals(accountType)).findFirst().orElseThrow().amount;
    }

    public static class Account {
        private AccountType type;
        private long amount;

        public Account(AccountType type, Long amount) {
            this.type = type;
            this.amount = amount;
        }
    }
}
