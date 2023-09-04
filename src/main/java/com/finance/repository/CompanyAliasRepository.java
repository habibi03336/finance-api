package com.finance.repository;

import com.finance.domain.CompanyAliasEntity;

import java.util.Set;

public interface CompanyAliasRepository {
    Set<CompanyAliasEntity> findDistinctCompanyCodeByAliasStartingWith(String token);
}
