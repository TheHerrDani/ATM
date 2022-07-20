package com.daniel.sipos.zinkworks.service.mappers;

import static com.daniel.sipos.zinkworks.repository.AtmRepositoryTest.EXPECTED_FIFTY;
import static com.daniel.sipos.zinkworks.repository.AtmRepositoryTest.EXPECTED_FIVE;
import static com.daniel.sipos.zinkworks.repository.AtmRepositoryTest.EXPECTED_TEN;
import static com.daniel.sipos.zinkworks.repository.AtmRepositoryTest.EXPECTED_TWENTY;
import static org.assertj.core.api.Assertions.assertThat;

import com.daniel.sipos.zinkworks.repository.entities.Atm;
import com.daniel.sipos.zinkworks.service.domain.AtmDomain;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
public class AtmMapperTest {

  private static final long EXPECTED_SUM = 1500L;
  private static final long EXPECTED_SUM_2 = 850L;
  public static final long TEN = 10L;
  public static final AtmDomain ATM_DOMAIN = AtmDomain.builder()
      .id(TEN)
      .euroFiftyCount(TEN)
      .euroTwentyCount(TEN)
      .euroTenCount(TEN)
      .euroFiveCount(TEN)
      .build();

  @Autowired
  private AtmMapper atmMapper;

  @Test
  public void toDomain() {
    Atm atm = Atm.builder()
        .id(TEN)
        .euroFiftyCount(EXPECTED_FIFTY)
        .euroTwentyCount(EXPECTED_TWENTY)
        .euroTenCount(EXPECTED_TEN)
        .euroFiveCount(EXPECTED_FIVE)
        .build();

    AtmDomain atmDomain = atmMapper.toDomain(atm);

    assertThat(atmDomain.getId()).isEqualTo(TEN);
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
    assertThat(atm.getId()).isEqualTo(TEN);
    assertThat(atm.getEuroFiftyCount()).isEqualTo(TEN);
    assertThat(atm.getEuroTwentyCount()).isEqualTo(TEN);
    assertThat(atm.getEuroTenCount()).isEqualTo(TEN);
    assertThat(atm.getEuroFiveCount()).isEqualTo(TEN);
  }
}
