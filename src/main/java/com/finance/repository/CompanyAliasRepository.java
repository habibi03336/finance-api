package com.finance.repository;

import com.finance.domain.CompanyAliasEntity;
import com.finance.domain.compositeKey.CompanyAliasKey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface CompanyAliasRepository extends JpaRepository<CompanyAliasEntity, CompanyAliasKey> {
    Set<CompanyAliasEntity> findDistinctCompanyCodeByAliasStartingWith(String token);
}
