package com.daniel.sipos.atm.controller.mappers;

import com.daniel.sipos.atm.controller.models.AccountDataModel;
import com.daniel.sipos.atm.service.domain.AccountDomain;
import org.springframework.stereotype.Service;

@Service
public class AccountDataMapper {

  public AccountDataModel toModel(AccountDomain accountDomain) {
    return AccountDataModel.builder()
        .accountNumber(accountDomain.getAccountNumber())
        .actualBalance(accountDomain.getAccountDetails().getActualBalance())
        .overdraft(accountDomain.getAccountDetails().getOverdraft())
        .dispensableMoney(accountDomain.getDispensableMoney())
        .build();
  }

}
