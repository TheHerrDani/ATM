package com.daniel.sipos.zinkworks.service.services;

import static com.daniel.sipos.zinkworks.util.Util.FIVE;

import com.daniel.sipos.zinkworks.controller.models.AccountDataModel;
import com.daniel.sipos.zinkworks.controller.models.DispenseDataModel;
import com.daniel.sipos.zinkworks.exceptions.AtmDenominationException;
import com.daniel.sipos.zinkworks.exceptions.OverLimitException;
import com.daniel.sipos.zinkworks.exceptions.RequestedAmountException;
import com.daniel.sipos.zinkworks.repository.repositories.AccountDetailsRepository;
import com.daniel.sipos.zinkworks.repository.repositories.AccountRepository;
import com.daniel.sipos.zinkworks.service.domain.AccountDetailsDomain;
import com.daniel.sipos.zinkworks.service.domain.AccountDomain;
import com.daniel.sipos.zinkworks.service.domain.AtmDispenseChange;
import com.daniel.sipos.zinkworks.service.mappers.repositoryservice.AccountDetailsMapper;
import com.daniel.sipos.zinkworks.service.mappers.repositoryservice.AccountMapper;
import com.daniel.sipos.zinkworks.service.mappers.servicecontroller.AccountDataMapper;
import com.daniel.sipos.zinkworks.service.mappers.servicecontroller.DispenseDataModelMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class AccountService {
  private final AccountRepository accountRepository;
  private final AccountDetailsRepository accountDetailsRepository;
  private final AccountMapper accountMapper;
  private final AccountDataMapper accountDataMapper;
  private final DispenseDataModelMapper dispenseDataModelMapper;
  private final AccountDetailsMapper accountDetailsMapper;
  private final AtmService atmService;

  @Transactional
  public DispenseDataModel dispenseMoney(Long atmId, String accountNumber, long requested) {
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
    accountDomain =
        accountMapper.toDomain(accountRepository.findAccountByAccountNumber(accountNumber));
    return dispenseDataModelMapper.toModel(atmDispenseChange, accountDomain);
  }

  public AccountDataModel getAccountInformation(String accountNumber) {
    return accountDataMapper.toModel(
        accountMapper.toDomain(
            accountRepository.findAccountByAccountNumber(accountNumber))
    );
  }

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
