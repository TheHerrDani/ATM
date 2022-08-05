package com.daniel.sipos.atm.controller.models;

import javax.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
public class AccountDataModel {

  @NotNull
  private String accountNumber;

  @NotNull
  private long dispensableMoney;

  @NotNull
  private long actualBalance;

  @NotNull
  private long overdraft;
}
