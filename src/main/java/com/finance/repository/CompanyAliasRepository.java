package com.finance.repository;

import com.finance.domain.CompanyAliasEntity;
import com.finance.domain.compositeKey.CompanyAliasKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CompanyAliasRepository extends JpaRepository<CompanyAliasEntity, CompanyAliasKey> {
    @Query(nativeQuery = true, value="select distinct company_code from company_aliases where alias like :token || '%' limit :searchLimit")
    List<String> findAllDistinctCompanyCodeByAliasStartingWith(@Param("token") String token, @Param("searchLimit") int searchLimit);
}
