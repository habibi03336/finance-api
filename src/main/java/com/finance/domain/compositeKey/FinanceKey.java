package com.finance.domain.compositeKey;

import java.io.Serializable;

public class FinanceKey implements Serializable {
    private String reportCode;
    private String reportType;

    public FinanceKey(String reportCode, String reportType) {
        this.reportCode = reportCode;
        this.reportType = reportType;
    }

    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (o == null) return false;
        if (this.getClass() != o.getClass()) return false;
        FinanceKey financeKey = (FinanceKey) o;
        return financeKey.reportCode.equals(reportCode) && financeKey.reportType.equals(reportType);
    }

}
