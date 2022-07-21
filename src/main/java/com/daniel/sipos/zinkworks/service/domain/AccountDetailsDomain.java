package com.daniel.sipos.zinkworks.service.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Builder
@ToString
@AllArgsConstructor
@Getter
@EqualsAndHashCode
public class AccountDetailsDomain {

  private long id;

  private long actualBalance;

  private long overdraft;

}
