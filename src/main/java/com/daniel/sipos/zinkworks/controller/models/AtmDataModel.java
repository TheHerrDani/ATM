package com.daniel.sipos.atm.controller.models;

import javax.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
public class AtmDataModel {

  @NotNull
  private long euroFiftyCount;

  @NotNull
  private long euroTwentyCount;

  @NotNull
  private long euroTenCount;

  @NotNull
  private long euroFiveCount;

  @NotNull
  private long atmAllMoney;
}
