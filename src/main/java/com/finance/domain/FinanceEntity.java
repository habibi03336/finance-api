package com.finance.domain;

import com.finance.domain.compositeKey.FinanceKey;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;

@Entity
@IdClass(FinanceKey.class)
public class FinanceEntity {
    @Id
    @Column(name = "report_code")
    private String reportCode;

    @Id
    @Column(name = "report_type")
    private String reportType;

    @Column(name = "company_code")
    private String companyCode;
    private int year;
    private int quarter;

    @Column(name = "cumulative_month")
    private int cumulativeMonth;
    private String currency;
    private long equity;
    private long debt;
    private long sales;

    @Column(name = "operating_profit")
    private long operatingProfit;

    @Column(name = "net_profit")
    private long netProfit;

    @Column(name = "cash_equivalents")
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
