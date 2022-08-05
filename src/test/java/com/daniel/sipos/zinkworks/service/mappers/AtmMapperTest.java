package com.daniel.sipos.atm.service.mappers;

import static com.daniel.sipos.atm.util.AtmConstants.ATM_DOMAIN;
import static com.daniel.sipos.atm.util.AtmConstants.EXPECTED_FIFTY;
import static com.daniel.sipos.atm.util.AtmConstants.EXPECTED_FIVE;
import static com.daniel.sipos.atm.util.AtmConstants.EXPECTED_SUM;
import static com.daniel.sipos.atm.util.AtmConstants.EXPECTED_SUM_2;
import static com.daniel.sipos.atm.util.AtmConstants.EXPECTED_TEN;
import static com.daniel.sipos.atm.util.AtmConstants.EXPECTED_TWENTY;
import static com.daniel.sipos.atm.util.AtmConstants.TEN;
import static org.assertj.core.api.Assertions.assertThat;

import com.daniel.sipos.atm.repository.entities.Atm;
import com.daniel.sipos.atm.service.domain.AtmDomain;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
public class AtmMapperTest {

  @Autowired
  private AtmMapper atmMapper;

  @Test
  public void toDomain() {
    Atm atm = Atm.builder()
        .euroFiftyCount(EXPECTED_FIFTY)
        .euroTwentyCount(EXPECTED_TWENTY)
        .euroTenCount(EXPECTED_TEN)
        .euroFiveCount(EXPECTED_FIVE)
        .build();

    AtmDomain atmDomain = atmMapper.toDomain(atm);

    assertThat(atmDomain.getEuroFiftyCount()).isEqualTo(EXPECTED_FIFTY);
    assertThat(atmDomain.getEuroTwentyCount()).isEqualTo(EXPECTED_TWENTY);
    assertThat(atmDomain.getEuroTenCount()).isEqualTo(EXPECTED_TEN);
    assertThat(atmDomain.getEuroFiveCount()).isEqualTo(EXPECTED_FIVE);
    assertThat(atmDomain.getAllMoney()).isEqualTo(EXPECTED_SUM);
  }

  @Test
  public void getAllMoney() {
    AtmDomain atm = AtmDomain.builder()
        .euroFiftyCount(TEN)
        .euroTwentyCount(TEN)
        .euroTenCount(TEN)
        .euroFiveCount(TEN)
        .build();

    long result = atm.getAllMoney();
    assertThat(result).isEqualTo(EXPECTED_SUM_2);
  }

  @Test
  public void toEntity() {
    Atm atm = atmMapper.toEntity(ATM_DOMAIN);
    assertThat(atm.getEuroFiftyCount()).isEqualTo(TEN);
    assertThat(atm.getEuroTwentyCount()).isEqualTo(TEN);
    assertThat(atm.getEuroTenCount()).isEqualTo(TEN);
    assertThat(atm.getEuroFiveCount()).isEqualTo(TEN);
  }
}
