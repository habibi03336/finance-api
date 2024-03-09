package com.finance.service.finance;

import com.finance.exception.DataNotExistException;
import com.finance.component.exchangeOffice.ExchangeOffice;
import com.finance.domain.CompanyEntity;
import com.finance.domain.FinanceEntity;
import com.finance.dto.CompanyDTO;
import com.finance.dto.FinanceDTO;
import com.finance.repository.CompanyRepository;
import com.finance.repository.FinanceRepository;
import com.finance.service.finance.enums.AccountType;
import java.util.Optional;

public class FinanceService {

    private CompanyRepository companyRepository;
    private FinanceRepository financeRepository;
    private ExchangeOffice exchangeOffice;

    public FinanceService(ExchangeOffice eo, CompanyRepository cr, FinanceRepository fr){
        exchangeOffice = eo;
        companyRepository = cr;
        financeRepository = fr;
    }

    private final String COMPANY_CODE_NOT_EXIST = "%s 기업코드가 존재하지 않습니다..";
    private final String FINANCE_DATA_NOT_EXIST = "%s 기업의 %d년 %d분기의 재무 데이터를 찾을 수 없습니다.";

    public FinanceDTO getQuarterFinance(String companyCode, int year, int quarter, String currency) throws DataNotExistException {
        CompanyEntity companyEntity = companyRepository
                .findByCompanyCode(companyCode)
                .orElseThrow(()->new DataNotExistException(String.format(COMPANY_CODE_NOT_EXIST, companyCode)));
        Finance finance = generateFinance(companyCode, year, quarter)
                .orElseThrow(()->new DataNotExistException(
                        String.format(
                                FINANCE_DATA_NOT_EXIST,
                                companyEntity.getCompanyName(),
                                year,
                                quarter
                        )
                ));
        finance.changeCurrency(currency, exchangeOffice);
        Finance quarterFinance = finance;
        if(finance.getCumulativeMonth() > 3){
            int priorYear = quarter == 1 ? year - 1 : year;
            int priorQuarter = quarter == 1 ? 4 : quarter - 1;
            Finance priorFinance = generateFinance(companyCode, priorYear, priorQuarter)
                    .orElseThrow(()->new DataNotExistException(
                            String.format(
                                    FINANCE_DATA_NOT_EXIST,
                                    companyEntity.getCompanyName(),
                                    priorYear,
                                    priorQuarter
                            )
                    ));
            priorFinance.changeCurrency(currency, exchangeOffice);
            quarterFinance = Finance.generateIncomeDiffFinance(finance, priorFinance);
        }

        return mapToDTO(quarterFinance, companyEntity);
    }

    private String CONSOLIDATED_K_IFRS_REPORT_TYPE = "consolidated K-IFRS";
    private String SEPERATE_K_IFRS_REPORT_TYPE = "seperate K-IFRS";

    private Optional<Finance> generateFinance(String companyCode, int year, int quarter) {
        FinanceEntity statement = financeRepository.findByCompanyCodeAndReportTypeAndYearAndQuarter(companyCode, CONSOLIDATED_K_IFRS_REPORT_TYPE, year, quarter);
        if(statement == null){
            statement = financeRepository.findByCompanyCodeAndReportTypeAndYearAndQuarter(companyCode, SEPERATE_K_IFRS_REPORT_TYPE, year, quarter);
        }
        if(statement == null){
            return Optional.empty();
        }
        Finance finance = new Finance(
                companyCode,
                statement.getReportCode(),
                statement.getReportType(),
                statement.getCurrency(),
                statement.getYear(),
                statement.getQuarter(),
                statement.getCumulativeMonth()
        );
        finance.addAccount(AccountType.sales, statement.getSales());
        finance.addAccount(AccountType.operatingProfit, statement.getOperatingProfit());
        finance.addAccount(AccountType.netProfit, statement.getNetProfit());
        finance.addAccount(AccountType.equity, statement.getEquity());
        finance.addAccount(AccountType.debt, statement.getDebt());
        finance.addAccount(AccountType.cashEquivalents, statement.getCash());
        return Optional.of(finance);
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

        financeDTO.sales = finance.getAccountAmount(AccountType.sales);
        financeDTO.operatingProfit = finance.getAccountAmount(AccountType.operatingProfit);
        financeDTO.netProfit = finance.getAccountAmount(AccountType.netProfit);
        financeDTO.equity = finance.getAccountAmount(AccountType.equity);
        financeDTO.debt = finance.getAccountAmount(AccountType.debt);
        financeDTO.cashEquivalents = finance.getAccountAmount(AccountType.cashEquivalents);

        return financeDTO;
    }
}
