package com.daniel.sipos.zinkworks.util;

import static com.daniel.sipos.zinkworks.util.AccountConstants.ACCOUNT_NUMBER;
import static com.daniel.sipos.zinkworks.util.AtmConstants.ATM_ID;

import com.daniel.sipos.zinkworks.controller.models.DispenseMoneyModel;

public class AccountControllerConstants {

  // Suppresses default constructor, ensuring non-instantiability.
  private AccountControllerConstants() {
  }

  public static final long ONE_HUNDRED_FIFTY = 150L;
  public static final DispenseMoneyModel DISPENSE_MONEY_MODEL =
      DispenseMoneyModel.builder()
          .accountNumber(ACCOUNT_NUMBER)
          .atmId(ATM_ID)
          .requestedMoney(ONE_HUNDRED_FIFTY)
          .build();
}
