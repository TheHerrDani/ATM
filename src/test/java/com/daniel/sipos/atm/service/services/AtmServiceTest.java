package com.daniel.sipos.atm.service.services;

import static com.daniel.sipos.atm.util.AtmConstants.ATM_DOMAIN;
import static com.daniel.sipos.atm.util.AtmConstants.ATM_ID;
import static com.daniel.sipos.atm.util.AtmConstants.FIVE;
import static com.daniel.sipos.atm.util.Util.FIFTY;
import static com.daniel.sipos.atm.util.Util.TEN;
import static com.daniel.sipos.atm.util.Util.TWENTY;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hibernate.type.IntegerType.ZERO;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.daniel.sipos.atm.exceptions.AtmDenominationException;
import com.daniel.sipos.atm.exceptions.AtmMoneyShortageException;
import com.daniel.sipos.atm.repository.entities.Atm;
import com.daniel.sipos.atm.repository.repositories.AtmRepository;
import com.daniel.sipos.atm.service.domain.AtmDispenseChange;
import java.util.Map;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
public class AtmServiceTest {

  @Autowired
  private AtmService atmService;
  @Autowired
  private AtmRepository atmRepository;

  @ParameterizedTest
  @MethodSource("createAtmDispenseChangeProvider")
  public void createAtmDispenseChange(Long requested, AtmDispenseChange result) {
    AtmDispenseChange atmDispenseChange = atmService.createAtmDispenseChange(ATM_ID, requested);
    assertThat(atmDispenseChange).isEqualTo(result);
  }

  @Test
  public void checkAtmStorageThrowAtmMoneyShortageException() {
    assertThrows(AtmMoneyShortageException.class,
        () -> atmService.createAtmDispenseChange(ATM_ID, 2000L));
  }

  @Test
  public void checkAtmStorageThrowAtmDenominationException() {
    Atm start = Atm.builder()
        .euroFiftyCount(FIVE)
        .euroTwentyCount(ZERO)
        .euroTenCount(FIVE)
        .euroFiveCount(ZERO)
        .build();
    Atm saved = atmRepository.saveOrUpdateAtm(start);
    assertThrows(AtmDenominationException.class,
        () -> atmService.createAtmDispenseChange(saved.getId(), 55));
  }

  @Test
  public void createAtmDenominationMap() {
    Map<Long, Long> testMap = atmService.createAtmDenominationMap(ATM_DOMAIN);
    assertThat(testMap.get(FIFTY)).isEqualTo(TEN);
    assertThat(testMap.get(TWENTY)).isEqualTo(TEN);
    assertThat(testMap.get(TEN)).isEqualTo(TEN);
    assertThat(testMap.get(FIVE)).isEqualTo(TEN);
  }

  @Test
  public void updateStorage() {
    Atm start = Atm.builder()
        .euroFiftyCount(TEN)
        .euroTwentyCount(TEN)
        .euroTenCount(TEN)
        .euroFiveCount(TEN)
        .build();
    start = atmRepository.saveOrUpdateAtm(start);
    AtmDispenseChange atmDispenseChange = createAtmDispenseChange(5, 5, 5, 5);
    atmService.updateStorage(atmDispenseChange, start.getId());
    Atm end = atmRepository.findAtmById(start.getId());
    assertThat(end.getId()).isEqualTo(start.getId());
    assertThat(end.getEuroFiftyCount()).isEqualTo(FIVE);
    assertThat(end.getEuroTwentyCount()).isEqualTo(FIVE);
    assertThat(end.getEuroTenCount()).isEqualTo(FIVE);
    assertThat(end.getEuroFiveCount()).isEqualTo(FIVE);
  }

  private static Stream<Arguments> createAtmDispenseChangeProvider() {
    AtmDispenseChange first =
        createAtmDispenseChange(0, 0, 0, 1);
    AtmDispenseChange second =
        createAtmDispenseChange(2, 0, 1, 1);
    AtmDispenseChange third =
        createAtmDispenseChange(10, 13, 1, 1);
    AtmDispenseChange fourth =
        createAtmDispenseChange(10, 30, 11, 1);
    AtmDispenseChange fifth =
        createAtmDispenseChange(10, 30, 30, 20);
    return Stream.of(
        Arguments.of(5L, first),
        Arguments.of(115L, second),
        Arguments.of(775L, third),
        Arguments.of(1215L, fourth),
        Arguments.of(1500L, fifth)
    );
  }

  public static AtmDispenseChange createAtmDispenseChange(long euroFiftyCount, long euroTwentyCount,
                                                          long euroTenCount, long euroFiveCount) {
    return AtmDispenseChange.builder()
        .euroFiftyCount(euroFiftyCount)
        .euroTwentyCount(euroTwentyCount)
        .euroTenCount(euroTenCount)
        .euroFiveCount(euroFiveCount)
        .build();
  }
}
