package com.finance;

import com.finance.repository.CompanyAliasRepository;
import com.finance.repository.CompanyRepository;
import com.finance.repository.DollarExchangeRateRepository;
import com.finance.repository.FinanceRepository;
import com.finance.service.companySearch.CompanySearchService;
import com.finance.service.finance.ExchangeOffice;
import com.finance.service.finance.ExchangeOfficeImpl;
import com.finance.service.finance.FinanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;

@Configuration
@PropertySources({
        @PropertySource("classpath:.env")
})
public class SpringConfig {
    private final CompanyAliasRepository companyAliasRepository;
    private final CompanyRepository companyRepository;
    private final DollarExchangeRateRepository dollarExchangeRateRepository;
    private FinanceRepository financeRepository;

    private ExchangeOffice exchangeOffice;

    @Autowired
    public SpringConfig(CompanyAliasRepository car, CompanyRepository cr, DollarExchangeRateRepository derr, FinanceRepository fr) {
        this.companyAliasRepository = car;
        this.companyRepository = cr;
        this.dollarExchangeRateRepository = derr;
        this.financeRepository = fr;
    }

    @Bean
    public ExchangeOffice exchangeOffice(){
        exchangeOffice = new ExchangeOfficeImpl(dollarExchangeRateRepository);
        return exchangeOffice;
    }

    @Bean
    @DependsOn({"exchangeOffice"})
    public FinanceService financeService() {
        return new FinanceService(exchangeOffice, companyRepository, financeRepository);
    }

    @Bean
    public CompanySearchService companySearchService() {
        return new CompanySearchService(companyRepository, companyAliasRepository);
    }
}