package com.daniel.sipos.zinkworks.service.domain;

import static com.daniel.sipos.zinkworks.util.Util.FIFTY;
import static com.daniel.sipos.zinkworks.util.Util.FIVE;
import static com.daniel.sipos.zinkworks.util.Util.TEN;
import static com.daniel.sipos.zinkworks.util.Util.TWENTY;

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
