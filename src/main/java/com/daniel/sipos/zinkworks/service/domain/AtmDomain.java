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
public class AtmDomain {
  private Long id;

  private Long euroFiftyCount;

  private Long euroTwentyCount;

  private Long euroTenCount;

  private Long euroFiveCount;

  private BigDecimal allMoney;
}
