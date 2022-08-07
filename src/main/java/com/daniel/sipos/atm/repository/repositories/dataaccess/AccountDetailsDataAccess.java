package com.daniel.sipos.atm.repository.repositories.dataaccess;

import com.daniel.sipos.atm.repository.entities.AccountDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountDetailsDataAccess extends JpaRepository<AccountDetails, Long> {
}
