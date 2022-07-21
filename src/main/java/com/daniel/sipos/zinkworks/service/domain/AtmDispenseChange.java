package com.daniel.sipos.zinkworks.service.domain;

import com.daniel.sipos.zinkworks.util.Util;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
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
}
