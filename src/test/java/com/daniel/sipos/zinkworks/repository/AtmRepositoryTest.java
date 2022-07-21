package com.daniel.sipos.zinkworks.repository;

import static com.daniel.sipos.zinkworks.util.AtmConstants.ATM_ID;
import static com.daniel.sipos.zinkworks.util.AtmConstants.ATM_ID_TWO;
import static com.daniel.sipos.zinkworks.util.AtmConstants.EXPECTED_FIFTY;
import static com.daniel.sipos.zinkworks.util.AtmConstants.EXPECTED_FIVE;
import static com.daniel.sipos.zinkworks.util.AtmConstants.EXPECTED_TEN;
import static com.daniel.sipos.zinkworks.util.AtmConstants.EXPECTED_TWENTY;
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

  @Autowired
  private AtmRepository atmRepository;

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
