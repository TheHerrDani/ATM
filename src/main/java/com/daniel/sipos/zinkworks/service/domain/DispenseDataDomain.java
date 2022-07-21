package com.daniel.sipos.zinkworks.service.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class DispenseDataDomain {
  private AtmDispenseChange atmDispenseChange;
  private AccountDomain accountDomain;

}
