package com.finance.repository;

import com.finance.domain.CompanyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface CompanyRepository extends JpaRepository<CompanyEntity, String> {

    Optional<CompanyEntity> findByCompanyCode(String companyCode);
    Optional<CompanyEntity> findByStockCode(String stockCode);
}
