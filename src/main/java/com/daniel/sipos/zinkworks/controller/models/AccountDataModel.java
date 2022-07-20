package com.daniel.sipos.zinkworks.controller.models;

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
  private long dispensedAmount;

  @NotNull
  private long actualBalance;

  @NotNull
  private long overdraft;
}
