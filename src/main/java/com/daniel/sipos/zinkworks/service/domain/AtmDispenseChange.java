package com.daniel.sipos.zinkworks.service.domain;

import lombok.Data;

@Data
public class AtmDispenseChange {

  private boolean isItPossible;

  private Long euroFiftyCount;

  private Long euroTwentyCount;

  private Long euroTenCount;

  private Long euroFiveCount;
}
