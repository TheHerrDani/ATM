package com.daniel.sipos.zinkworks.repository.repositories;

import com.daniel.sipos.zinkworks.repository.entities.Account;
import com.daniel.sipos.zinkworks.repository.repositories.dataaccess.AccountDataAccess;
import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AccountRepository {
  @Autowired
  AccountDataAccess accountDataAccess;

  public Account findAccountByAccountNumber(String accountNumber) {
    return accountDataAccess.findByAccountNumber(accountNumber)
        .orElseThrow(
            () -> new NoSuchElementException("There is no account with the given account number")
        );
  }
}
