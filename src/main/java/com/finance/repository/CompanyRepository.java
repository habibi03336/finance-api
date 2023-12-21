package com.finance.repository;

import com.finance.domain.CompanyEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CompanyRepository extends JpaRepository<CompanyEntity, String> {

    CompanyEntity findByCompanyCode(String companyCode);
    CompanyEntity findByStockCode(String stockCode);
}
