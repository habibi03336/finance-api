package com.finance.domain;

public class FinanceEntity {
    private String reportCode;
    private String reportType;
    private String companyCode;
    private int year;
    private int quarter;
    private int cumulativeMonth;
    private String currency;
    private long equity;
    private long debt;
    private long sales;
    private long operatingProfit;
    private long netProfit;
    private long cash;

    public String getReportCode() {
        return reportCode;
    }

    public String getReportType() {
        return reportType;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public int getYear() {
        return year;
    }

    public int getQuarter() {
        return quarter;
    }

    public int getCumulativeMonth() {
        return cumulativeMonth;
    }

    public String getCurrency() {
        return currency;
    }

    public long getEquity() {
        return equity;
    }

    public long getDebt() {
        return debt;
    }

    public long getSales() {
        return sales;
    }

    public long getOperatingProfit() {
        return operatingProfit;
    }

    public long getNetProfit() {
        return netProfit;
    }

    public long getCash() {
        return cash;
    }
}
