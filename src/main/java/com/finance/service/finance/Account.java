package com.finance.service.finance;

public class Account {
    private Type type;
    private Long amount;

    public Account(Type type, Long amount){
        this.type = type;
        this.amount = amount;
    }

    public Type getType() {
        return type;
    }
    public Long getAmount() {
        return amount;
    }

    public void setAmount(long amount){
        this.amount = amount;
    }

    public boolean isIncome(){
        return type.isIncome;
    }

    public String getName(){
        return type.name;
    }

    public enum Type {
        sales("sales", true),
        operatingProfit("operating_profit", true),
        netProfit( "net_profit", true),
        equity("equity", false),
        debt("debt", false),
        cashEquivalents("cash_equivalents", false);

        private String name;
        private boolean isIncome;

        Type(String name, boolean isIncome) {
            this.name = name;
            this.isIncome = isIncome;
        }
    }
}