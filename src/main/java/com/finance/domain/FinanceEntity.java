package com.finance.domain;

import com.finance.domain.compositeKey.FinanceKey;
import jakarta.persistence.*;

@Entity
@IdClass(FinanceKey.class)
@Table(name = "finance")
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
    private Long equity;
    private Long debt;
    private Long sales;

    @Column(name = "operating_profit")
    private Long operatingProfit;

    @Column(name = "net_profit")
    private Long netProfit;

    @Column(name = "cash_equivalents")
    private Long cash;

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

    public Long getEquity() {
        return equity;
    }

    public Long getDebt() {
        return debt;
    }

    public Long getSales() {
        return sales;
    }

    public Long getOperatingProfit() {
        return operatingProfit;
    }

    public Long getNetProfit() {
        return netProfit;
    }

    public Long getCash() {
        return cash;
    }
}
