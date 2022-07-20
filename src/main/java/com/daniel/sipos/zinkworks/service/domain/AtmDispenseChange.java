package com.daniel.sipos.zinkworks.service.domain;

import static com.daniel.sipos.zinkworks.util.Util.FIFTY;
import static com.daniel.sipos.zinkworks.util.Util.FIVE;
import static com.daniel.sipos.zinkworks.util.Util.TEN;
import static com.daniel.sipos.zinkworks.util.Util.TWENTY;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AtmDispenseChange {
  private Integer euroFiftyCount;

  private Integer euroTwentyCount;

  private Integer euroTenCount;

  private Integer euroFiveCount;

  public long getAllMoney() {
    return (this.getEuroFiftyCount() * FIFTY) +
        (this.getEuroTwentyCount() * TWENTY) +
        (this.getEuroTenCount() * TEN) +
        (this.getEuroFiveCount() * FIVE);
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
