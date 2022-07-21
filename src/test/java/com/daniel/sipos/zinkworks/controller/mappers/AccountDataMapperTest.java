package com.daniel.sipos.zinkworks.controller.mappers;

import static com.daniel.sipos.zinkworks.util.AccountConstants.ACCOUNT_DOMAIN;
import static com.daniel.sipos.zinkworks.util.AccountConstants.ACCOUNT_NUMBER;
import static com.daniel.sipos.zinkworks.util.AccountConstants.DISPENSABLE_MONEY;
import static com.daniel.sipos.zinkworks.util.AccountDetailsConstants.ACTUAL_BALANCE;
import static com.daniel.sipos.zinkworks.util.AccountDetailsConstants.TWO_HUNDRED;
import static org.assertj.core.api.Assertions.assertThat;

import com.daniel.sipos.zinkworks.controller.models.AccountDataModel;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
public class AccountDataMapperTest {

  @Autowired
  private AccountDataMapper accountDataMapper;

  @Test
  public void toModel() {
    AccountDataModel accountDataModel = accountDataMapper.toModel(ACCOUNT_DOMAIN);

    assertThat(accountDataModel.getActualBalance()).isEqualTo(ACTUAL_BALANCE);
    assertThat(accountDataModel.getOverdraft()).isEqualTo(TWO_HUNDRED);
    assertThat(accountDataModel.getAccountNumber()).isEqualTo(ACCOUNT_NUMBER);
    assertThat(accountDataModel.getDispensableMoney()).isEqualTo(DISPENSABLE_MONEY);
  }
}
