package com.finance.controller;

import com.finance.exception.DataNotExistException;
import com.finance.dto.CompanyDTO;
import com.finance.service.companySearch.CompanySearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CompanySearchController {
    private final CompanySearchService companySearchService;

    @Autowired
    public CompanySearchController(CompanySearchService companySearchService) {
        this.companySearchService = companySearchService;
    }

    @GetMapping("/company")
    @ResponseBody
    public List<CompanyDTO> SearchCompanies(@RequestParam("token") String token, @RequestParam(required = false) Integer limit){
        if(limit != null){
            return companySearchService.searchCompany(token, limit);
        }
        return companySearchService.searchCompany(token);
    }

    @GetMapping("/companyByStockCode")
    @ResponseBody
    public CompanyDTO getCompanyByStockCode(@RequestParam("stockCode") String token) throws DataNotExistException {
        return companySearchService.getCompanyByStockCode(token);
    }
}
