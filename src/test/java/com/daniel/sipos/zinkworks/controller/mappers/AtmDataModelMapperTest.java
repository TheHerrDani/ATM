package com.daniel.sipos.zinkworks.controller.mappers;

import static com.daniel.sipos.zinkworks.util.AtmConstants.ATM_DOMAIN;
import static com.daniel.sipos.zinkworks.util.AtmConstants.EXPECTED_SUM_2;
import static com.daniel.sipos.zinkworks.util.AtmConstants.TEN;
import static org.assertj.core.api.Assertions.assertThat;

import com.daniel.sipos.zinkworks.controller.models.AtmDataModel;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
public class AtmDataModelMapperTest {

  @Autowired
  private AtmDataModelMapper atmDataModelMapper;

  @Test
  public void toModel() {
    AtmDataModel atmDataModel = atmDataModelMapper.toModel(ATM_DOMAIN);

    assertThat(atmDataModel.getAtmAllMoney()).isEqualTo(EXPECTED_SUM_2);
    assertThat(atmDataModel.getEuroFiftyCount()).isEqualTo(TEN);
    assertThat(atmDataModel.getEuroTwentyCount()).isEqualTo(TEN);
    assertThat(atmDataModel.getEuroTenCount()).isEqualTo(TEN);
    assertThat(atmDataModel.getEuroFiveCount()).isEqualTo(TEN);
  }
}
