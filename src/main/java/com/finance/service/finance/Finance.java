package com.finance.service.finance;

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

    public Finance(String corpCode, String statementCode, String statementType, String currency, int year, int quarter, int cumulativeMonth, List<Account> accounts){
        this.corpCode = corpCode;
        this.statementCode = statementCode;
        this.statementType = statementType;
        this.currency = currency;
        this.year = year;
        this.quarter = quarter;
        this.cumulativeMonth = cumulativeMonth;
        this.accounts = accounts;
    }

    public void changeCurrency(String toCurrency, ExchangeOffice ex){
        if(toCurrency.equals(currency)) return;
        double averageRatio = ex.getAverageRate(year, quarter, cumulativeMonth, currency, toCurrency);
        double finalRatio = ex.getFinalRate(year, quarter, currency, toCurrency);
        currency = toCurrency;
        for(Account acc : accounts){
            if(acc.isIncome()){
                acc.setAmount((long)(acc.getAmount() * averageRatio));
            } else {
                acc.setAmount((long)(acc.getAmount() * finalRatio));
            }
        }
    }

    public static Finance generateIncomeDiffFinance(Finance operand1, Finance operand2){
        List<Account> accounts  = operand1.getAccounts();
        List<Account> accounts2 = operand2.getAccounts();
        List<Account> diffAccounts = new ArrayList<>();
        for(Account acc : accounts){
            if(acc.isIncome()){
                for(Account acc2: accounts2){
                    if(acc.getType().equals(acc2.getType())){
                        diffAccounts.add(new Account(acc.getType(), acc.getAmount() - acc2.getAmount()));
                        break;
                    }
                }
            } else {
                diffAccounts.add(new Account(acc.getType(), acc.getAmount()));
            }
        }

        return new Finance(
                    operand1.getCorpCode(),
                    operand1.getStatementCode(),
                    operand1.getStatementType(),
                    operand1.getCurrency(),
                    operand1.getYear(),
                    operand1.getQuarter(),
                    operand1.getCumulativeMonth() - operand2.getCumulativeMonth(),
                    diffAccounts
                );
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

    public List<Account> getAccounts() {
        return accounts;
    }
}
