package com.daniel.sipos.zinkworks.service.services;

import com.daniel.sipos.zinkworks.exceptions.OverLimitException;
import com.daniel.sipos.zinkworks.repository.repositories.AccountDetailsRepository;
import com.daniel.sipos.zinkworks.repository.repositories.AccountRepository;
import com.daniel.sipos.zinkworks.service.domain.AccountDetailsDomain;
import com.daniel.sipos.zinkworks.service.domain.AccountDomain;
import com.daniel.sipos.zinkworks.service.domain.AtmDispenseChange;
import com.daniel.sipos.zinkworks.service.mappers.AccountDetailsMapper;
import com.daniel.sipos.zinkworks.service.mappers.AccountMapper;
import java.math.BigDecimal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AccountService {
  @Autowired
  AccountRepository accountRepository;

  @Autowired
  AccountDetailsRepository accountDetailsRepository;

  @Autowired
  AccountMapper accountMapper;

  @Autowired
  AccountDetailsMapper accountDetailsMapper;

  @Autowired
  AtmService atmService;

  @Transactional
  public AccountDomain dispenseMoney(Long atmId, Long accountNumber, BigDecimal requested) {
    AccountDomain accountDomain =
        accountMapper.toDomain(accountRepository.findAccountByAccountNumber(accountNumber));
    if (accountDomain.getDispensableMoney().compareTo(requested) <= 0) {
      throw new OverLimitException("Account does not have enough money for this transaction");
    } else {
      AtmDispenseChange atmDispenseChange = atmService.checkAtmStorage(atmId, requested);
      if (atmDispenseChange.isItPossible()) {
        AccountDetailsDomain accountDetails = accountDomain.getAccountDetails();
        BigDecimal newBalance = accountDetails.getActualBalance().min(requested);
        AccountDetailsDomain newAccountDetails = AccountDetailsDomain.builder()
            .id(accountDetails.getId())
            .overdraft(accountDetails.getOverdraft())
            .actualBalance(newBalance)
            .build();
        accountDetailsRepository.saveOrUpdate(accountDetailsMapper.toEntity(newAccountDetails));
        atmService.updateStorage(atmDispenseChange, atmId);
      }
    }
    return accountMapper.toDomain(accountRepository.findAccountByAccountNumber(accountNumber));
  }
}
