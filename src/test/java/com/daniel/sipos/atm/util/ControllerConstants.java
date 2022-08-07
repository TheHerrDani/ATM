package com.daniel.sipos.atm.util;

import static com.daniel.sipos.atm.util.AccountConstants.ACCOUNT_NUMBER;
import static com.daniel.sipos.atm.util.AccountConstants.ACCOUNT_NUMBER_2;
import static com.daniel.sipos.atm.util.AtmConstants.ATM_ID;

import com.daniel.sipos.atm.controller.models.DispenseMoneyModel;

public class ControllerConstants {

  // Suppresses default constructor, ensuring non-instantiability.
  private ControllerConstants() {
  }

  public static final String ATM_INFO =
      "{\"euroFiftyCount\":10,\"euroTwentyCount\":30,\"euroTenCount\":30,\"euroFiveCount\":20," +
          "\"atmAllMoney\":1500}";
  public static final String DISPENSE_INFO =
      "{\"accountNumber\":\"123456789\",\"dispensedAmount\":150,\"remainingDispensableAmount\":850," +
          "\"actualBalance\":650,\"overdraft\":200,\"euroFiftyCount\":3,\"euroTwentyCount\":0," +
          "\"euroTenCount\":0,\"euroFiveCount\":0}";
  public static final String ACCOUNT_INFO = "{\"accountNumber\":\"123456789\"," +
      "\"dispensableMoney\":1000," +
      "\"actualBalance\":800,\"overdraft\":200}";

  public static final long ONE_HUNDRED_FIFTY = 150L;
  public static final DispenseMoneyModel DISPENSE_MONEY_MODEL =
      DispenseMoneyModel.builder()
          .accountNumber(ACCOUNT_NUMBER)
          .atmId(ATM_ID)
          .requestedMoney(ONE_HUNDRED_FIFTY)
          .build();
  public static final DispenseMoneyModel BAD_DISPENSE_MONEY_MODEL =
      DispenseMoneyModel.builder()
          .accountNumber(ACCOUNT_NUMBER)
          .atmId(ATM_ID)
          .requestedMoney(132L)
          .build();
  public static final DispenseMoneyModel TOO_MUCH_DISPENSE_MONEY_MODEL =
      DispenseMoneyModel.builder()
          .accountNumber(ACCOUNT_NUMBER)
          .atmId(ATM_ID)
          .requestedMoney(1550L)
          .build();
  public static final DispenseMoneyModel ATM_SHORTAGE_MODEL =
      DispenseMoneyModel.builder()
          .accountNumber(ACCOUNT_NUMBER)
          .atmId(ATM_ID)
          .requestedMoney(1000L)
          .build();
  public static final DispenseMoneyModel ATM_SHORTAGE_MODEL_2 =
      DispenseMoneyModel.builder()
          .accountNumber(ACCOUNT_NUMBER_2)
          .atmId(ATM_ID)
          .requestedMoney(1000L)
          .build();
  public static final DispenseMoneyModel REQUESTED_AMOUNT_ERROR =
      DispenseMoneyModel.builder()
          .accountNumber(ACCOUNT_NUMBER_2)
          .atmId(ATM_ID)
          .requestedMoney(-10L)
          .build();
  public static final DispenseMoneyModel REQUESTED_AMOUNT_ERROR_2 =
      DispenseMoneyModel.builder()
          .accountNumber(ACCOUNT_NUMBER_2)
          .atmId(ATM_ID)
          .requestedMoney(0L)
          .build();
}
