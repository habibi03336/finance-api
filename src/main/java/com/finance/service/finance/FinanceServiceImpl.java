package com.finance.service.finance;

import com.finance.domain.CompanyEntity;
import com.finance.domain.FinanceEntity;
import com.finance.dto.CompanyDTO;
import com.finance.dto.FinanceDTO;
import com.finance.repository.CompanyRepository;
import com.finance.repository.FinanceRepository;

import java.util.ArrayList;
import java.util.List;

public class FinanceServiceImpl implements FinanceService {

    private CompanyRepository companyRepository;
    private FinanceRepository financeRepository;
    private ExchangeOffice exchangeOffice;

    public FinanceServiceImpl(ExchangeOffice eo, CompanyRepository cr, FinanceRepository fr){
        exchangeOffice = eo;
        companyRepository = cr;
        financeRepository = fr;
    }

    @Override
    public FinanceDTO getQuarterFinance(String companyCode, int year, int quarter, String currency) {
        Finance finance = generateFinance(companyCode, year, quarter);
        finance.changeCurrency(currency, exchangeOffice);
        Finance quarterFinance = finance;
        if(finance.getCumulativeMonth() > 3){
            Finance priorFinance = generateFinance(companyCode, quarter == 1 ? year - 1 : year, quarter == 1 ? 4 : quarter - 1);
            priorFinance.changeCurrency(currency, exchangeOffice);
            quarterFinance = Finance.generateIncomeDiffFinance(finance, priorFinance);
        }

        CompanyEntity companyEntity = companyRepository.findByCompanyCode(companyCode);
        return mapToDTO(quarterFinance, companyEntity);
    }

    private Finance generateFinance(String companyCode, int year, int quarter) {
        String reportType = "consolidated K-IFRS";
        FinanceEntity statement = financeRepository.findByCompanyCodeAndReportTypeAndYearAndQuarter(companyCode, reportType, year, quarter);
        if(statement == null){
            reportType = "seperate K-IFRS";
            statement = financeRepository.findByCompanyCodeAndReportTypeAndYearAndQuarter(companyCode, reportType, year, quarter);
        }
        List<Account> accounts = new ArrayList<>();
        accounts.add(new Account(Account.Type.sales, statement.getSales()));
        accounts.add(new Account(Account.Type.operatingProfit, statement.getOperatingProfit()));
        accounts.add(new Account(Account.Type.netProfit, statement.getNetProfit()));
        accounts.add(new Account(Account.Type.equity, statement.getEquity()));
        accounts.add(new Account(Account.Type.debt, statement.getDebt()));
        accounts.add(new Account(Account.Type.cashEquivalents, statement.getCash()));
        Finance finance = new Finance(
                companyCode,
                statement.getReportCode(),
                statement.getReportType(),
                statement.getCurrency(),
                statement.getYear(),
                statement.getQuarter(),
                statement.getCumulativeMonth(),
                accounts
        );
        return finance;
    }

    private FinanceDTO mapToDTO(com.finance.service.finance.Finance finance, CompanyEntity companyEntity){
        CompanyDTO companyDTO = new CompanyDTO();
        companyDTO.companyCode = companyEntity.getCompanyCode();
        companyDTO.name = companyEntity.getCompanyName();
        companyDTO.stockCode = companyEntity.getStockCode();

        FinanceDTO financeDTO = new FinanceDTO();
        financeDTO.company = companyDTO;
        financeDTO.reportCode = finance.getStatementCode();
        financeDTO.reportType = finance.getStatementType();
        financeDTO.year = finance.getYear();
        financeDTO.quarter = finance.getQuarter();
        financeDTO.currency = finance.getCurrency();

        List<Account> accounts = finance.getAccounts();
        for(Account account: accounts){
            switch (account.getType()){
                case sales:
                    financeDTO.sales = account.getAmount();
                    break;
                case operatingProfit:
                    financeDTO.operatingProfit = account.getAmount();
                    break;
                case netProfit:
                    financeDTO.netProfit = account.getAmount();
                    break;
                case equity:
                    financeDTO.equity = account.getAmount();
                    break;
                case debt:
                    financeDTO.debt = account.getAmount();
                    break;
                case cashEquivalents:
                    financeDTO.cashEquivalents = account.getAmount();
                    break;
            }
        }
        return financeDTO;
    }
}
