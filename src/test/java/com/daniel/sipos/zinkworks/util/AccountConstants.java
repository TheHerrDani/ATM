package com.daniel.sipos.atm.util;

import static com.daniel.sipos.atm.util.AccountDetailsConstants.ACCOUNT_DETAILS_DOMAIN;

import com.daniel.sipos.atm.service.domain.AccountDomain;

public class AccountConstants {

  // Suppresses default constructor, ensuring non-instantiability.
  private AccountConstants() {
  }

  public static final String ACCOUNT_NUMBER = "123456789";
  public static final String ACCOUNT_NUMBER_2 = "987654321";
  public static final String BAD_ACCOUNT_NUMBER = "0123456789";
  public static final String PIN_NUMBER = "1234";

  public static final long DISPENSABLE_MONEY = 600L;
  public static final long TWO_HUNDRED = 200L;

  public static final AccountDomain ACCOUNT_DOMAIN = AccountDomain.builder()
      .accountNumber(ACCOUNT_NUMBER)
      .pin(PIN_NUMBER)
      .accountDetails(ACCOUNT_DETAILS_DOMAIN)
      .build();
}
