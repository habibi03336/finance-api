package com.finance.service.companySearch;

import com.finance.dto.CompanyDTO;

import java.util.List;

public interface CompanySearchService {
    List<CompanyDTO> searchCompany(String token);
    List<CompanyDTO> searchCompany(String token, int searchLimit);
    CompanyDTO getCompanyByStockCode(String stockCode);
}
