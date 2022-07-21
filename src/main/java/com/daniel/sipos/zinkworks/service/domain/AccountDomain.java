package com.daniel.sipos.zinkworks.service.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Builder
@ToString
@AllArgsConstructor
@Getter
@EqualsAndHashCode
public class AccountDomain {
  private long id;

  private AccountDetailsDomain accountDetails;

  private String accountNumber;

  private String pin;

  public long getDispensableMoney() {
    return this.getAccountDetails().getActualBalance() + this.getAccountDetails().getOverdraft();
  }
}
