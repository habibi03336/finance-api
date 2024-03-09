package com.finance.service.companySearch;

import com.finance.Exception.DataNotExistException;
import com.finance.domain.CompanyEntity;
import com.finance.dto.CompanyDTO;
import com.finance.repository.CompanyAliasRepository;
import com.finance.repository.CompanyRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CompanySearchService {

    private CompanyRepository companyRepository;
    private CompanyAliasRepository companyAliasRepository;

    private int DEFAULT_SEARCH_LIMIT = 5;

    public CompanySearchService(CompanyRepository cr, CompanyAliasRepository car){
        companyRepository = cr;
        companyAliasRepository = car;
    }

    public List<CompanyDTO> searchCompany(String token) {
        return searchCompany(token, DEFAULT_SEARCH_LIMIT);
    }

    public List<CompanyDTO> searchCompany(String token, int searchLimit) {
        List<String> companyCodes = companyAliasRepository.findAllDistinctCompanyCodeByAliasStartingWith(token, searchLimit);
        List<CompanyEntity> companies = new ArrayList<>();
        for(String companyCode : companyCodes){
            companies.add(companyRepository.findByCompanyCode(companyCode).get());
        }
        return companies.stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    private String STOCK_CODE_NOT_EXIST = "%s 주식코드를 가진 기업이 없습니다.";

    public CompanyDTO getCompanyByStockCode(String stockCode) throws DataNotExistException {
        CompanyEntity companyEntity = companyRepository.findByStockCode(stockCode)
                .orElseThrow(()->new DataNotExistException(String.format(STOCK_CODE_NOT_EXIST, stockCode)));
        return mapToDTO(companyEntity);
    }

    private CompanyDTO mapToDTO(CompanyEntity company) {
        CompanyDTO companyDTO = new CompanyDTO();
        companyDTO.companyCode = company.getCompanyCode();
        companyDTO.name = company.getCompanyName();
        companyDTO.stockCode = company.getStockCode();
        return companyDTO;
    }
}
