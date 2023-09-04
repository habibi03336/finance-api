package com.finance.service.companySearch;

import com.finance.domain.CompanyAliasEntity;
import com.finance.domain.CompanyEntity;
import com.finance.dto.CompanyDTO;
import com.finance.repository.CompanyAliasRepository;
import com.finance.repository.CompanyRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class CompanySearchServiceImpl implements CompanySearchService {

    private CompanyRepository companyRepository;
    private CompanyAliasRepository companyAliasRepository;

    public CompanySearchServiceImpl(CompanyRepository cr, CompanyAliasRepository car){
        companyRepository = cr;
        companyAliasRepository = car;
    }

    @Override
    public List<CompanyDTO> searchCompany(String token) {
        Set<CompanyAliasEntity> companyAliases = companyAliasRepository.findDistinctCompanyCodeByAliasStartingWith(token);
        List<CompanyEntity> companies = new ArrayList<>();
        for(CompanyAliasEntity alias : companyAliases){
            companies.add(companyRepository.findByCompanyCode(alias.getCompanyCode()));
        }
        return companies.stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    private CompanyDTO mapToDTO(CompanyEntity company) {
        CompanyDTO companyDTO = new CompanyDTO();
        companyDTO.companyCode = company.getCompanyCode();
        companyDTO.name = company.getCompanyName();
        companyDTO.stockCode = company.getStockCode();
        return companyDTO;
    }
}
