package com.daniel.sipos.atm.controller.models;

import javax.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
public class DispenseDataModel {

  @NotNull
  private String accountNumber;

  @NotNull
  private long dispensedAmount;

  @NotNull
  private long remainingDispensableAmount;

  @NotNull
  private long actualBalance;

  @NotNull
  private long overdraft;

  @NotNull
  private long euroFiftyCount;

  @NotNull
  private long euroTwentyCount;

  @NotNull
  private long euroTenCount;

  @NotNull
  private long euroFiveCount;
}
