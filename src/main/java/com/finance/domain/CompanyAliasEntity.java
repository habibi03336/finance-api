package com.finance.domain;
import com.finance.domain.compositeKey.CompanyAliasKey;
import jakarta.persistence.*;

@Entity
@IdClass(CompanyAliasKey.class)
@Table(name = "company_aliases")
public class CompanyAliasEntity {
    @Id
    @Column(name = "company_code")
    private String companyCode;
    @Id
    private String alias;
    public String getCompanyCode() {
        return companyCode;
    }

    public String getAlias() {
        return alias;
    }
}
