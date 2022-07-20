package com.daniel.sipos.zinkworks.service.mappers.servicecontroller;

import com.daniel.sipos.zinkworks.controller.models.DispenseDataModel;
import com.daniel.sipos.zinkworks.service.domain.AccountDomain;
import com.daniel.sipos.zinkworks.service.domain.AtmDispenseChange;
import org.springframework.stereotype.Service;

@Service
public class DispenseDataModelMapper {

  public DispenseDataModel toModel(AtmDispenseChange atmDispenseChange,
                                   AccountDomain accountDomain) {
    return DispenseDataModel.builder()
        .accountNumber(accountDomain.getAccountNumber())
        .actualBalance(accountDomain.getAccountDetails().getActualBalance())
        .overdraft(accountDomain.getAccountDetails().getOverdraft())
        .dispensedAmount(atmDispenseChange.getAllMoney())
        .remainingDispensableAmount(accountDomain.getDispensableMoney())
        .euroFiftyCount(atmDispenseChange.getEuroFiftyCount())
        .euroTwentyCount(atmDispenseChange.getEuroTwentyCount())
        .euroTenCount(atmDispenseChange.getEuroTenCount())
        .euroFiveCount(atmDispenseChange.getEuroFiveCount())
        .build();
  }
}
