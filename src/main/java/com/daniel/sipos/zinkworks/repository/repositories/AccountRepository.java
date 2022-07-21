package com.daniel.sipos.zinkworks.repository.repositories;

import com.daniel.sipos.zinkworks.repository.entities.Account;
import com.daniel.sipos.zinkworks.repository.repositories.dataaccess.AccountDataAccess;
import java.util.NoSuchElementException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class AccountRepository {

  private final AccountDataAccess accountDataAccess;

  public Account findAccountByAccountNumber(String accountNumber) {
    return accountDataAccess.findByAccountNumber(accountNumber)
        .orElseThrow(
            () -> new NoSuchElementException("There is no account with the given account number")
        );
  }
}
