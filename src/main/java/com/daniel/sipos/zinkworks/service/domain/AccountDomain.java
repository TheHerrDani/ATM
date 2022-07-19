package com.daniel.sipos.zinkworks.service.domain;

import java.math.BigDecimal;
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
public class AccountDomain {
  private Long id;

  private AccountDetailsDomain accountDetails;

  private BigDecimal dispensableMoney;

  private Long accountNumber;

  private Integer pin;
}
