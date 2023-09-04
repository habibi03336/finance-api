package com.finance.repository;

import com.finance.domain.CompanyEntity;


public interface CompanyRepository {

    CompanyEntity findByCompanyCode(String companyCode);
}
