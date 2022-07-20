package com.daniel.sipos.zinkworks.service.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class AccountDomain {
  private long id;

  private AccountDetailsDomain accountDetails;

  private String accountNumber;

  private String pin;

  public long getDispensableMoney() {
    return this.getAccountDetails().getActualBalance() + this.getAccountDetails().getOverdraft();
  }
}
