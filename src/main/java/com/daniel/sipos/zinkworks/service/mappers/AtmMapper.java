package com.daniel.sipos.zinkworks.service.mappers;

import com.daniel.sipos.zinkworks.repository.entities.Atm;
import com.daniel.sipos.zinkworks.service.domain.AtmDomain;
import java.math.BigDecimal;
import org.springframework.stereotype.Service;

@Service
public class AtmMapper {

  public static final BigDecimal FIFTY = BigDecimal.valueOf(50L);
  public static final BigDecimal TWENTY = BigDecimal.valueOf(20L);
  public static final BigDecimal TEN = BigDecimal.valueOf(10L);
  public static final BigDecimal FIVE = BigDecimal.valueOf(5L);

  public AtmDomain toDomain(Atm atm) {
    return AtmDomain.builder()
        .id(atm.getId())
        .euroFiftyCount(atm.getEuroFiftyCount())
        .euroTwentyCount(atm.getEuroTwentyCount())
        .euroTenCount(atm.getEuroTenCount())
        .euroFiveCount(atm.getEuroFiveCount())
        .allMoney(getAllMoney(atm))
        .build();
  }

  BigDecimal getAllMoney(Atm atm) {
    return BigDecimal.ZERO
        .add(BigDecimal.valueOf(atm.getEuroFiftyCount()).multiply(FIFTY))
        .add(BigDecimal.valueOf(atm.getEuroTwentyCount()).multiply(TWENTY))
        .add(BigDecimal.valueOf(atm.getEuroTenCount()).multiply(TEN))
        .add(BigDecimal.valueOf(atm.getEuroFiveCount()).multiply(FIVE));
  }

  public Atm toEntity(AtmDomain atmDomain) {
    return Atm.builder()
        .id(atmDomain.getId())
        .euroFiftyCount(atmDomain.getEuroFiftyCount())
        .euroTwentyCount(atmDomain.getEuroTwentyCount())
        .euroTenCount(atmDomain.getEuroTenCount())
        .euroFiveCount(atmDomain.getEuroFiveCount())
        .build();
  }
}
