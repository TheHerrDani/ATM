package com.daniel.sipos.zinkworks.service.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AtmDispenseChange {
  private Integer euroFiftyCount;

  private Integer euroTwentyCount;

  private Integer euroTenCount;

  private Integer euroFiveCount;

  public static AtmDispenseChange buildEmpty() {
    return AtmDispenseChange.builder()
        .euroFiftyCount(0)
        .euroTwentyCount(0)
        .euroTenCount(0)
        .euroFiveCount(0)
        .build();
  }
}
