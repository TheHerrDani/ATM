package com.daniel.sipos.zinkworks.controller.mappers;

import com.daniel.sipos.zinkworks.controller.models.DispenseDataModel;
import com.daniel.sipos.zinkworks.service.domain.AccountDomain;
import com.daniel.sipos.zinkworks.service.domain.AtmDispenseChange;
import com.daniel.sipos.zinkworks.service.domain.DispenseDataDomain;
import org.springframework.stereotype.Service;

@Service
public class DispenseDataModelMapper {

  public DispenseDataModel toModel(DispenseDataDomain dispenseDataDomain) {
    AccountDomain accountDomain = dispenseDataDomain.getAccountDomain();
    AtmDispenseChange atmDispenseChange = dispenseDataDomain.getAtmDispenseChange();
    return DispenseDataModel.builder()
        .accountNumber(accountDomain.getAccountNumber())
        .actualBalance(accountDomain.getAccountDetails().getActualBalance())
        .overdraft(accountDomain.getAccountDetails().getOverdraft())
        .dispensedAmount(atmDispenseChange.dispensedAmount())
        .remainingDispensableAmount(accountDomain.getDispensableMoney())
        .euroFiftyCount(atmDispenseChange.getEuroFiftyCount())
        .euroTwentyCount(atmDispenseChange.getEuroTwentyCount())
        .euroTenCount(atmDispenseChange.getEuroTenCount())
        .euroFiveCount(atmDispenseChange.getEuroFiveCount())
        .build();
  }
}
