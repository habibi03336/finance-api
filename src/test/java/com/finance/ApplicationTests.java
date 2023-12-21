package com.finance;

import com.finance.controller.CompanySearchController;
import com.finance.dto.CompanyDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class ApplicationTests {

	@Autowired
	private CompanySearchController companySearchController;

	@Test
	void contextLoads() {
	}

	@Test
	void getCompanyByStockCode(){
		String stockCode = "005930";
		CompanyDTO companyDTO = companySearchController.getCompanyByStockCode(stockCode);
		assertEquals(companyDTO.name, "삼성전자");
		assertEquals(companyDTO.stockCode, stockCode);
	}

}
