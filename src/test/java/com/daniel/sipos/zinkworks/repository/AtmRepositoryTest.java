package com.daniel.sipos.zinkworks.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.daniel.sipos.zinkworks.repository.entities.Atm;
import com.daniel.sipos.zinkworks.repository.repositories.AtmRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;


@SpringBootTest
@ActiveProfiles("test")
public class AtmRepositoryTest {

  public static final long ATM_ID = 1L;
  public static final long ATM_ID_TWO = 2L;
  public static final long EXPECTED_FIFTY = 10L;
  public static final long EXPECTED_TWENTY = 30L;
  public static final long EXPECTED_TEN = 30L;
  public static final long EXPECTED_FIVE = 20L;
  @Autowired
  AtmRepository atmRepository;

  @Test
  public void updateAtm() {
    Atm atm = createAtm(ATM_ID_TWO, EXPECTED_TWENTY, EXPECTED_FIVE, EXPECTED_FIVE, EXPECTED_FIFTY);

    atm = atmRepository.saveOrUpdateAtm(atm);

    assertThat(atm.getId()).isEqualTo(ATM_ID_TWO);
    assertThat(atm.getEuroFiftyCount()).isEqualTo(EXPECTED_TWENTY);
    assertThat(atm.getEuroTenCount()).isEqualTo(EXPECTED_FIVE);

    atm = createAtm(ATM_ID_TWO, EXPECTED_FIFTY, EXPECTED_TWENTY, EXPECTED_TEN, EXPECTED_FIVE);
    atm = atmRepository.saveOrUpdateAtm(atm);

    assertThat(atm.getId()).isEqualTo(ATM_ID_TWO);
    assertThat(atm.getEuroFiftyCount()).isEqualTo(EXPECTED_FIFTY);
    assertThat(atm.getEuroTwentyCount()).isEqualTo(EXPECTED_TWENTY);
    assertThat(atm.getEuroTenCount()).isEqualTo(EXPECTED_TEN);
    assertThat(atm.getEuroFiveCount()).isEqualTo(EXPECTED_FIVE);
  }

  @Test
  public void findAtmById() {

    Atm atm = atmRepository.findAtmById(ATM_ID);

    assertThat(atm.getId()).isEqualTo(ATM_ID);
    assertThat(atm.getEuroFiftyCount()).isEqualTo(EXPECTED_FIFTY);
    assertThat(atm.getEuroTwentyCount()).isEqualTo(EXPECTED_TWENTY);
    assertThat(atm.getEuroTenCount()).isEqualTo(EXPECTED_TEN);
    assertThat(atm.getEuroFiveCount()).isEqualTo(EXPECTED_FIVE);
  }

  private Atm createAtm(long atmId, long expectedFifty, long expectedTwenty,
                        long expectedTen, long expectedFive) {
    return Atm.builder()
        .id(atmId)
        .euroFiftyCount(expectedFifty)
        .euroTwentyCount(expectedTwenty)
        .euroTenCount(expectedTen)
        .euroFiveCount(expectedFive)
        .build();
  }
}
