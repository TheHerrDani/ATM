package com.daniel.sipos.zinkworks.service.mappers.repositoryservice;

import com.daniel.sipos.zinkworks.repository.entities.Account;
import com.daniel.sipos.zinkworks.service.domain.AccountDetailsDomain;
import com.daniel.sipos.zinkworks.service.domain.AccountDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountMapper {

  @Autowired
  AccountDetailsMapper accountDetailsMapper;

  public AccountDomain toDomain(Account account) {
    AccountDetailsDomain accountDetails =
        accountDetailsMapper.toDomain(account.getAccountDetails());
    return AccountDomain.builder()
        .id(account.getId())
        .pin(account.getPin())
        .accountNumber(account.getAccountNumber())
        .accountDetails(accountDetails)
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
