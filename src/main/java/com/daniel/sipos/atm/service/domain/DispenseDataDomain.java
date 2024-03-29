package com.daniel.sipos.atm.service.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Builder
@AllArgsConstructor
@EqualsAndHashCode
@Getter
public class DispenseDataDomain {
  private AtmDispenseChange atmDispenseChange;
  private AccountDomain accountDomain;
}
