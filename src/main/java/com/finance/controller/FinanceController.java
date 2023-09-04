package com.finance.controller;


import com.finance.dto.FinanceDTO;
import com.finance.service.finance.FinanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class FinanceController {

    private final FinanceService financeService;

    @Autowired
    public FinanceController(FinanceService financeService) {
        this.financeService = financeService;
    }

    @GetMapping("/finance")
    @ResponseBody
    public FinanceDTO finance(
            @RequestParam("companyCode") String companyCode,
            @RequestParam("year") int year,
            @RequestParam("quarter") int quarter,
            @RequestParam("currency") String currency
    ) throws Exception {
        return financeService.getQuarterFinance(companyCode, year, quarter, currency);
    }
}
