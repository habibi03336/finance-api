package com.finance.repository;

import com.finance.domain.FinanceEntity;

public interface FinanceRepository {
    FinanceEntity findByCompanyCodeAndReportTypeAndYearAndQuarter(String companyCode, String reportType, int year, int quarter);
}
