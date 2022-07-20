package com.daniel.sipos.zinkworks.service.mappers.servicecontroller;

import com.daniel.sipos.zinkworks.controller.models.AccountDataModel;
import com.daniel.sipos.zinkworks.service.domain.AccountDomain;
import org.springframework.stereotype.Service;

@Service
public class AccountDataMapper {

  public AccountDataModel toModel(AccountDomain accountDomain) {
    return AccountDataModel.builder()
        .accountNumber(accountDomain.getAccountNumber())
        .actualBalance(accountDomain.getAccountDetails().getActualBalance())
        .overdraft(accountDomain.getAccountDetails().getActualBalance())
        .dispensedAmount(accountDomain.getDispensableMoney())
        .build();
  }

}
