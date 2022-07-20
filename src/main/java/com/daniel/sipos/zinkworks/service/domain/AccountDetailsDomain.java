package com.daniel.sipos.zinkworks.service.domain;

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

  private long id;

  private long actualBalance;

  private long overdraft;

}
