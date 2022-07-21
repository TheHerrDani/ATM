package com.daniel.sipos.zinkworks.util;

public class Util {

  // Suppresses default constructor, ensuring non-instantiability.
  private Util() {
  }

  public static final long FIFTY = 50L;
  public static final long TWENTY = 20L;
  public static final long TEN = 10L;
  public static final long FIVE = 5L;

  public static long calculateMoney(long fiftyCount, long twentyCount, long tenCount,
                                    long fiveCount) {
    return fiftyCount * FIFTY + twentyCount * TWENTY + tenCount * TEN + fiveCount * FIVE;
  }
}
