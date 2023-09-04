package com.finance.repository;

import com.finance.domain.FinanceEntity;
import com.finance.domain.compositeKey.FinanceKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FinanceRepository extends JpaRepository<FinanceEntity, FinanceKey> {
    FinanceEntity findByCompanyCodeAndReportTypeAndYearAndQuarter(String companyCode, String reportType, int year, int quarter);
}
