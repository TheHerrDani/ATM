package com.daniel.sipos.zinkworks;

import static org.assertj.core.api.Assertions.assertThat;

import com.daniel.sipos.zinkworks.repository.entities.Atm;
import com.daniel.sipos.zinkworks.repository.repositories.AtmRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(classes = {SpringTestConfiguration.class})
@ActiveProfiles("test")
public class AtmRepositoryTest {

  public static final long ATM_ID = 1L;
  @Autowired
  AtmRepository atmRepository;

  @Test
  public void findAtmById() {
    Atm atm = atmRepository.findAtmById(ATM_ID);
    assertThat(atm.getId()).isEqualTo(1);
    assertThat(atm.getEuroFiftyCount()).isEqualTo(10);
    assertThat(atm.getEuroTwentyCount()).isEqualTo(30);
    assertThat(atm.getEuroTenCount()).isEqualTo(30);
    assertThat(atm.getEuroFiveCount()).isEqualTo(20);
  }
}
