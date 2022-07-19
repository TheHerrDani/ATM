package com.daniel.sipos.zinkworks;

import static org.assertj.core.api.Assertions.assertThat;

import com.daniel.sipos.zinkworks.repository.entities.Atm;
import com.daniel.sipos.zinkworks.repository.repositories.AtmRepository;
import javax.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(classes = {SpringTestConfiguration.class})
@ActiveProfiles("test")
public class AtmRepositoryTest {

  public static final long ATM_ID = 1L;
  public static final long EXPECTED_FIFTY = 10L;
  public static final long EXPECTED_TWENTY = 30L;
  public static final long EXPECTED_TEN = 30L;
  public static final long EXPECTED_FIVE = 20L;
  @Autowired
  AtmRepository atmRepository;

  @Test
  public void updateAtm() {
    Atm atm = Atm.builder()
        .id(1L)
        .euroFiftyCount(EXPECTED_TWENTY)
        .euroTwentyCount(EXPECTED_FIVE)
        .euroTenCount(EXPECTED_FIVE)
        .euroFiveCount(EXPECTED_FIFTY)
        .build();
    atm = atmRepository.updateAtm(atm);
    assertThat(atm.getId()).isEqualTo(1);
    assertThat(atm.getEuroFiftyCount()).isEqualTo(EXPECTED_TWENTY);
    assertThat(atm.getEuroTenCount()).isEqualTo(EXPECTED_FIVE);
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
}
