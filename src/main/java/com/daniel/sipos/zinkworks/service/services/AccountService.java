package com.daniel.sipos.zinkworks.service.services;

import static com.daniel.sipos.zinkworks.util.Util.FIVE;

import com.daniel.sipos.zinkworks.controller.mappers.AccountDataMapper;
import com.daniel.sipos.zinkworks.controller.models.AccountDataModel;
import com.daniel.sipos.zinkworks.exceptions.AtmDenominationException;
import com.daniel.sipos.zinkworks.exceptions.OverLimitException;
import com.daniel.sipos.zinkworks.exceptions.RequestedAmountException;
import com.daniel.sipos.zinkworks.repository.repositories.AccountDetailsRepository;
import com.daniel.sipos.zinkworks.repository.repositories.AccountRepository;
import com.daniel.sipos.zinkworks.service.domain.AccountDetailsDomain;
import com.daniel.sipos.zinkworks.service.domain.AccountDomain;
import com.daniel.sipos.zinkworks.service.domain.AtmDispenseChange;
import com.daniel.sipos.zinkworks.service.domain.DispenseDataDomain;
import com.daniel.sipos.zinkworks.service.mappers.AccountDetailsMapper;
import com.daniel.sipos.zinkworks.service.mappers.AccountMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class AccountService {
  public static final String MUST_BE_DIVISIBLE_BY_5 = "Requested Amount must be divisible by 5";
  public static final String ACCOUNT_BALANCE_SHORTAGE =
      "Account does not have enough money for this transaction";
  public static final String MUST_BE_A_POSITIVE_NUMBER =
      "Requested amount must be a positive number";
  public static final String CAN_NOT_BE_0 = "Requested amount can not be 0";
  private final AccountRepository accountRepository;
  private final AccountDetailsRepository accountDetailsRepository;
  private final AccountMapper accountMapper;
  private final AccountDataMapper accountDataMapper;
  private final AccountDetailsMapper accountDetailsMapper;
  private final AtmService atmService;

  @Transactional
  public AccountDataModel getAccountInformation(String accountNumber) {
    return accountDataMapper.toModel(
        accountMapper.toDomain(
            accountRepository.findAccountByAccountNumber(accountNumber))
    );
  }

  @Transactional
  public DispenseDataDomain dispenseMoney(long atmId, String accountNumber, long requested) {
    AccountDomain accountDomain = checkAccountInformation(accountNumber, requested);
    AtmDispenseChange atmDispenseChange = atmService.createAtmDispenseChange(atmId, requested);
    AccountDetailsDomain accountDetails = accountDomain.getAccountDetails();
    updateDatabase(atmId, requested, atmDispenseChange, accountDetails);
    accountDomain =
        accountMapper.toDomain(accountRepository.findAccountByAccountNumber(accountNumber));
    return DispenseDataDomain.builder().accountDomain(accountDomain)
        .atmDispenseChange(atmDispenseChange).build();
  }

  private void updateDatabase(long atmId, long requested, AtmDispenseChange atmDispenseChange,
                              AccountDetailsDomain accountDetails) {
    long newBalance = accountDetails.getActualBalance() - requested;
    AccountDetailsDomain newAccountDetails = AccountDetailsDomain.builder()
        .id(accountDetails.getId())
        .overdraft(accountDetails.getOverdraft())
        .actualBalance(newBalance)
        .build();
    accountDetailsRepository.saveOrUpdate(accountDetailsMapper.toEntity(newAccountDetails));
    atmService.updateStorage(atmDispenseChange, atmId);
  }

  private AccountDomain checkAccountInformation(String accountNumber, long requested) {
    checkRequestedAmount(requested);
    AccountDomain accountDomain =
        accountMapper.toDomain(accountRepository.findAccountByAccountNumber(accountNumber));
    checkOverLimit(requested, accountDomain.getDispensableMoney());
    return accountDomain;
  }

  private void checkOverLimit(long requested, long available) {
    if (available < requested) {
      throw new OverLimitException(ACCOUNT_BALANCE_SHORTAGE);
    }
  }

  void checkRequestedAmount(long requested) {
    if (requested < 0) {
      throw new RequestedAmountException(MUST_BE_A_POSITIVE_NUMBER);
    }
    if (requested == 0) {
      throw new RequestedAmountException(CAN_NOT_BE_0);
    }
    if (requested % FIVE != 0) {
      throw new AtmDenominationException(MUST_BE_DIVISIBLE_BY_5);
    }
  }

}
