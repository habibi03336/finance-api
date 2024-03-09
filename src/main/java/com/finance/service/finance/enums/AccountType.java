package com.finance.service.finance.enums;

public enum AccountType {
    sales("sales", true),
    operatingProfit("operating_profit", true),
    netProfit( "net_profit", true),
    equity("equity", false),
    debt("debt", false),
    cashEquivalents("cash_equivalents", false);

    private String name;
    private boolean isIncome;

    public String getName(){
        return name;
    }

    public boolean isIncome(){
        return isIncome;
    }
    AccountType(String name, boolean isIncome) {
            this.name = name;
            this.isIncome = isIncome;
    }
}
