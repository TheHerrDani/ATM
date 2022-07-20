package com.daniel.sipos.zinkworks.service.domain;

import static com.daniel.sipos.zinkworks.util.Util.FIFTY;
import static com.daniel.sipos.zinkworks.util.Util.FIVE;
import static com.daniel.sipos.zinkworks.util.Util.TEN;
import static com.daniel.sipos.zinkworks.util.Util.TWENTY;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class AtmDomain {
  private long id;

  private long euroFiftyCount;

  private long euroTwentyCount;

  private long euroTenCount;

  private long euroFiveCount;

  public long getAllMoney() {
    return (this.getEuroFiftyCount() * FIFTY) +
        (this.getEuroTwentyCount() * TWENTY) +
        (this.getEuroTenCount() * TEN) +
        (this.getEuroFiveCount() * FIVE);
  }
}
