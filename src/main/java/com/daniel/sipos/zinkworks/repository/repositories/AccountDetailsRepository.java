package com.daniel.sipos.atm.repository.repositories;

import com.daniel.sipos.atm.repository.entities.AccountDetails;
import com.daniel.sipos.atm.repository.repositories.dataaccess.AccountDetailsDataAccess;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class AccountDetailsRepository {

  private final AccountDetailsDataAccess accountDetailsDataAccess;

  public AccountDetails saveOrUpdate(AccountDetails accountDetails) {
    return accountDetailsDataAccess.saveAndFlush(accountDetails);
  }
}
