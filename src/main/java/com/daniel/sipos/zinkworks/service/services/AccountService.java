package com.daniel.sipos.zinkworks.service.services;

import static com.daniel.sipos.zinkworks.util.Util.FIVE;

import com.daniel.sipos.zinkworks.exceptions.AtmDenominationException;
import com.daniel.sipos.zinkworks.exceptions.OverLimitException;
import com.daniel.sipos.zinkworks.exceptions.RequestedAmountException;
import com.daniel.sipos.zinkworks.repository.repositories.AccountDetailsRepository;
import com.daniel.sipos.zinkworks.repository.repositories.AccountRepository;
import com.daniel.sipos.zinkworks.service.domain.AccountDetailsDomain;
import com.daniel.sipos.zinkworks.service.domain.AccountDomain;
import com.daniel.sipos.zinkworks.service.domain.AtmDispenseChange;
import com.daniel.sipos.zinkworks.service.mappers.AccountDetailsMapper;
import com.daniel.sipos.zinkworks.service.mappers.AccountMapper;
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
  public AccountDomain dispenseMoney(Long atmId, String accountNumber, long requested) {
    authenticateUser();
    checkRequestedAmount(requested);
    AccountDomain accountDomain =
        accountMapper.toDomain(accountRepository.findAccountByAccountNumber(accountNumber));
    checkOverLimit(requested, accountDomain.getDispensableMoney());
    AtmDispenseChange atmDispenseChange = atmService.createAtmDispenseChange(atmId, requested);
    AccountDetailsDomain accountDetails = accountDomain.getAccountDetails();
    long newBalance = accountDetails.getActualBalance() - requested;
    AccountDetailsDomain newAccountDetails = AccountDetailsDomain.builder()
        .id(accountDetails.getId())
        .overdraft(accountDetails.getOverdraft())
        .actualBalance(newBalance)
        .build();
    accountDetailsRepository.saveOrUpdate(accountDetailsMapper.toEntity(newAccountDetails));
    atmService.updateStorage(atmDispenseChange, atmId);
    return accountMapper.toDomain(accountRepository.findAccountByAccountNumber(accountNumber));
  }

  private void authenticateUser() {
  }

  //TODO test
  void checkOverLimit(long requested, long available) {
    if (available < requested) {
      throw new OverLimitException("Account does not have enough money for this transaction");
    }
  }

  void checkRequestedAmount(long requested) {
    if (requested < 0) {
      throw new RequestedAmountException("Requested amount must be a positive number");
    }
    if (requested == 0) {
      throw new RequestedAmountException("Requested amount must be bigger than 0");
    }
    if (requested % FIVE != 0) {
      throw new AtmDenominationException("Requested Amount must be divisible by 5");
    }
  }
}
