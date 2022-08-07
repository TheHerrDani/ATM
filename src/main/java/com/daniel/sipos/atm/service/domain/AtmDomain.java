package com.daniel.sipos.atm.service.domain;

import com.daniel.sipos.atm.util.Util;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Builder
@ToString
@AllArgsConstructor
@Getter
@EqualsAndHashCode
public class AtmDomain {
  private long id;

  private long euroFiftyCount;

  private long euroTwentyCount;

  private long euroTenCount;

  private long euroFiveCount;

  public long getAllMoney() {
    return Util.calculateMoney(euroFiftyCount, euroTwentyCount, euroTenCount, euroFiveCount);
  }
}
