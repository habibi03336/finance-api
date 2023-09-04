package com.finance.domain;
import jakarta.persistence.*;

@Entity
@Table(name = "company")
public class CompanyEntity {
    @Id
    @Column(name = "company_code")
    private String companyCode;
    @Column(name = "name")
    private String companyName;
    @Column(name = "stock_code")
    private String stockCode;

    public String getCompanyCode() {
        return companyCode;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getStockCode() {
        return stockCode;
    }

}
