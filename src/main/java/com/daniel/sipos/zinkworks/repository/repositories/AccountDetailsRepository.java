package com.daniel.sipos.zinkworks.repository.repositories;

import com.daniel.sipos.zinkworks.repository.entities.AccountDetails;
import com.daniel.sipos.zinkworks.repository.repositories.dataaccess.AccountDetailsDataAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AccountDetailsRepository {
  @Autowired
  AccountDetailsDataAccess accountDetailsDataAccess;

  public AccountDetails saveOrUpdate(AccountDetails accountDetails) {
    return accountDetailsDataAccess.saveAndFlush(accountDetails);
  }
}
