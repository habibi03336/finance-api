package com.finance.service.finance;

import com.finance.Exception.DataNotExistException;
import com.finance.dto.FinanceDTO;

public interface FinanceService {
    FinanceDTO getQuarterFinance(String companyCode, int year, int quarter, String currency) throws DataNotExistException;
}
