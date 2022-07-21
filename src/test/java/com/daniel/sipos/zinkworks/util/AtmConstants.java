package com.daniel.sipos.zinkworks.util;

import com.daniel.sipos.zinkworks.service.domain.AtmDomain;


public class AtmConstants {

  // Suppresses default constructor, ensuring non-instantiability.
  private AtmConstants() {
  }

  public static final long ATM_ID = 1;
  public static final long EXPECTED_SUM = 1500L;
  public static final long EXPECTED_SUM_2 = 850L;
  public static final long FIVE = 5L;
  public static final long TEN = 10L;
  public static final long ATM_ID_TWO = 2;
  public static final long EXPECTED_FIFTY = 10L;
  public static final long EXPECTED_TWENTY = 30L;
  public static final long EXPECTED_TEN = 30L;
  public static final long EXPECTED_FIVE = 20L;
  public static final AtmDomain ATM_DOMAIN = AtmDomain.builder()
      .euroFiftyCount(TEN)
      .euroTwentyCount(TEN)
      .euroTenCount(TEN)
      .euroFiveCount(TEN)
      .build();
}
