package com.finance.domain.compositeKey;

import java.io.Serializable;

public class CompanyAliasKey implements Serializable {

    private String companyCode;
    private String alias;


    public CompanyAliasKey() {
    }

    public CompanyAliasKey(String companyCode, String alias) {
        this.companyCode = companyCode;
        this.alias = alias;
    }

    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (o == null) return false;
        if (this.getClass() != o.getClass()) return false;
        CompanyAliasKey companyAliasKey = (CompanyAliasKey) o;
        return companyAliasKey.companyCode.equals(companyCode) && companyAliasKey.alias.equals(alias);
    }

    @Override
    public int hashCode(){
        return 0;
    }

}
