package com.daniel.sipos.zinkworks.service.mappers;

import com.daniel.sipos.zinkworks.repository.entities.Account;
import com.daniel.sipos.zinkworks.service.domain.AccountDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountMapper {

  @Autowired
  AccountDetailsMapper accountDetailsMapper;

  public AccountDomain toDomain(Account account) {
    return AccountDomain.builder()
        .id(account.getId())
        .pin(account.getPin())
        .accountNumber(account.getAccountNumber())
        .accountDetails(accountDetailsMapper.toDomain(account.getAccountDetails()))
        .build();
  }

  public Account toEntity(AccountDomain accountDomain) {
    return Account.builder()
        .id(accountDomain.getId())
        .pin(accountDomain.getPin())
        .accountNumber(accountDomain.getAccountNumber())
        .accountDetails(accountDetailsMapper.toEntity(accountDomain.getAccountDetails()))
        .build();
  }
}
