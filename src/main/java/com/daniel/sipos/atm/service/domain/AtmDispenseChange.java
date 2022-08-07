package com.daniel.sipos.atm.service.domain;

import com.daniel.sipos.atm.util.Util;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Builder
@AllArgsConstructor
@EqualsAndHashCode
@Getter
public class AtmDispenseChange {
  private long euroFiftyCount;

  private long euroTwentyCount;

  private long euroTenCount;

  private long euroFiveCount;

  public long dispensedAmount() {
    return Util.calculateMoney(euroFiftyCount, euroTwentyCount, euroTenCount, euroFiveCount);
  }

  public static AtmDispenseChange buildEmpty() {
    return AtmDispenseChange.builder()
        .euroFiftyCount(0)
        .euroTwentyCount(0)
        .euroTenCount(0)
        .euroFiveCount(0)
        .build();
  }

  public void increaseFiftyByOne() {
    this.euroFiftyCount += 1;
  }

  public void increaseTwentyByOne() {
    this.euroTwentyCount += 1;
  }

  public void increaseTenByOne() {
    this.euroTenCount += 1;
  }

  public void increaseFiveByOne() {
    this.euroFiveCount += 1;
  }
}
