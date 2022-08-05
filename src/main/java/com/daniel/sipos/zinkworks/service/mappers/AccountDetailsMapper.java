package com.daniel.sipos.atm.service.mappers;

import com.daniel.sipos.atm.repository.entities.AccountDetails;
import com.daniel.sipos.atm.service.domain.AccountDetailsDomain;
import org.springframework.stereotype.Service;

@Service
public class AccountDetailsMapper {

  public AccountDetailsDomain toDomain(AccountDetails accountDetails) {
    return AccountDetailsDomain.builder()
        .id(accountDetails.getId())
        .actualBalance(accountDetails.getActualBalance())
        .overdraft(accountDetails.getOverdraft())
        .build();
  }

  public AccountDetails toEntity(AccountDetailsDomain accountDetailsDomain) {
    return AccountDetails.builder()
        .id(accountDetailsDomain.getId())
        .actualBalance(accountDetailsDomain.getActualBalance())
        .overdraft(accountDetailsDomain.getOverdraft())
        .build();
  }
}
