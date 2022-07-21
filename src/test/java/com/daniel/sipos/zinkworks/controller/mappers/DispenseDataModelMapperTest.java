package com.daniel.sipos.zinkworks.controller.mappers;

import static com.daniel.sipos.zinkworks.service.services.AtmServiceTest.createAtmDispenseChange;
import static com.daniel.sipos.zinkworks.util.AccountConstants.ACCOUNT_DOMAIN;
import static com.daniel.sipos.zinkworks.util.AccountConstants.DISPENSABLE_MONEY;
import static com.daniel.sipos.zinkworks.util.AccountDetailsConstants.ACTUAL_BALANCE;
import static com.daniel.sipos.zinkworks.util.AccountDetailsConstants.TWO_HUNDRED;
import static com.daniel.sipos.zinkworks.util.AtmConstants.EXPECTED_SUM_2;
import static com.daniel.sipos.zinkworks.util.AtmConstants.TEN;
import static org.assertj.core.api.Assertions.assertThat;

import com.daniel.sipos.zinkworks.controller.models.DispenseDataModel;
import com.daniel.sipos.zinkworks.service.domain.DispenseDataDomain;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
public class DispenseDataModelMapperTest {

  @Autowired
  private DispenseDataModelMapper dispenseDataModelMapper;

  @Test
  public void toModel() {
    DispenseDataDomain dispenseDataDomain =
        DispenseDataDomain.builder().accountDomain(ACCOUNT_DOMAIN)
            .atmDispenseChange(createAtmDispenseChange(TEN, TEN, TEN, TEN)).build();
    DispenseDataModel dispenseDataModel = dispenseDataModelMapper.toModel(dispenseDataDomain);

    assertThat(dispenseDataModel.getActualBalance()).isEqualTo(ACTUAL_BALANCE);
    assertThat(dispenseDataModel.getEuroFiftyCount()).isEqualTo(TEN);
    assertThat(dispenseDataModel.getEuroTwentyCount()).isEqualTo(TEN);
    assertThat(dispenseDataModel.getEuroTenCount()).isEqualTo(TEN);
    assertThat(dispenseDataModel.getEuroFiveCount()).isEqualTo(TEN);
    assertThat(dispenseDataModel.getDispensedAmount()).isEqualTo(EXPECTED_SUM_2);
    assertThat(dispenseDataModel.getOverdraft()).isEqualTo(TWO_HUNDRED);
    assertThat(dispenseDataModel.getRemainingDispensableAmount()).isEqualTo(DISPENSABLE_MONEY);
  }
}
