package com.daniel.sipos.zinkworks.service.mappers;

import com.daniel.sipos.zinkworks.repository.entities.Account;
import com.daniel.sipos.zinkworks.service.domain.AccountDetailsDomain;
import com.daniel.sipos.zinkworks.service.domain.AccountDomain;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AccountMapper {

  private final AccountDetailsMapper accountDetailsMapper;

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
