package com.daniel.sipos.atm.util;

import com.daniel.sipos.atm.repository.entities.AccountDetails;
import com.daniel.sipos.atm.service.domain.AccountDetailsDomain;


public class AccountDetailsConstants {

  // Suppresses default constructor, ensuring non-instantiability.
  private AccountDetailsConstants() {
  }

  public static final long ACCOUNT_DETAILS_ID_ZERO = 0L;
  public static final long ACCOUNT_DETAILS_ID = 3L;
  public static final long ACTUAL_BALANCE = 400L;
  public static final long EIGHT_HUNDRED = 800L;
  public static final long TWO_HUNDRED = 200L;


  public static final AccountDetailsDomain ACCOUNT_DETAILS_DOMAIN = AccountDetailsDomain.builder()
      .id(ACCOUNT_DETAILS_ID)
      .actualBalance(ACTUAL_BALANCE)
      .overdraft(TWO_HUNDRED)
      .build();

  public static AccountDetails createAccountDetails(long accountDetailsId,
                                                    long actualBalance,
                                                    long overdraft) {
    return AccountDetails.builder()
        .id(accountDetailsId)
        .actualBalance(actualBalance)
        .overdraft(overdraft)
        .build();
  }
}
