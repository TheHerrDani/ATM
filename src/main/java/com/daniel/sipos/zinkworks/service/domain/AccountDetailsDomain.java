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
public class AccountDetailsDomain {

  private Long id;

  private BigDecimal actualBalance;

  private BigDecimal overdraft;

}
