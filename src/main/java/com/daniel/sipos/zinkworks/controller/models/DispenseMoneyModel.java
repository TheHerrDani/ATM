package com.daniel.sipos.atm.controller.models;

import javax.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;


@Data
@Builder
@ToString
public class DispenseMoneyModel {
  @NotNull
  long atmId;

  @NotNull
  String accountNumber;

  @NotNull
  long requestedMoney;
}
