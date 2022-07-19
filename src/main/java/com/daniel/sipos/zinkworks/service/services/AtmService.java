package com.daniel.sipos.zinkworks.service.services;

import com.daniel.sipos.zinkworks.exceptions.AtmDenominationException;
import com.daniel.sipos.zinkworks.exceptions.AtmMoneyShortageException;
import com.daniel.sipos.zinkworks.repository.entities.Atm;
import com.daniel.sipos.zinkworks.repository.repositories.AtmRepository;
import com.daniel.sipos.zinkworks.service.domain.AtmDispenseChange;
import com.daniel.sipos.zinkworks.service.domain.AtmDomain;
import com.daniel.sipos.zinkworks.service.mappers.AtmMapper;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AtmService {
  public static final BigDecimal FIFTY = BigDecimal.valueOf(50);
  public static final BigDecimal TWENTY = BigDecimal.valueOf(20);
  public static final BigDecimal TEN = BigDecimal.valueOf(10);
  public static final BigDecimal FIVE = BigDecimal.valueOf(5);

  @Autowired
  AtmRepository atmRepository;

  @Autowired
  AtmMapper atmMapper;

  public AtmDispenseChange checkAtmStorage(long atmId, BigDecimal requested) {
    AtmDomain atmDomain = atmMapper.toDomain(atmRepository.findAtmById(atmId));
    if (atmDomain.getAllMoney().compareTo(requested) >= 0) {
      AtmDispenseChange atmDispenseChange = AtmDispenseChange.buildEmpty();
      BigDecimal remaining = BigDecimal.valueOf(requested.longValue());
      Map<Integer, Long> atmDenominationMap = createAtmDenominationMap(atmDomain);
      while (true) {
        if (remaining.compareTo(FIFTY) >= 0 && atmDenominationMap.get(50) > 0) {
          atmDispenseChange.setEuroFiftyCount(atmDispenseChange.getEuroFiftyCount() + 1);
          atmDenominationMap.replace(50, atmDenominationMap.get(50) - 1);
          remaining = remaining.subtract(FIFTY);
        } else if (remaining.compareTo(TWENTY) >= 0 && atmDenominationMap.get(20) > 0) {
          atmDispenseChange.setEuroTwentyCount(atmDispenseChange.getEuroTwentyCount() + 1);
          atmDenominationMap.replace(20, atmDenominationMap.get(20) - 1);
          remaining = remaining.subtract(TWENTY);
        } else if (remaining.compareTo(TEN) >= 0 && atmDenominationMap.get(10) > 0) {
          atmDispenseChange.setEuroTenCount(atmDispenseChange.getEuroTenCount() + 1);
          atmDenominationMap.replace(10, atmDenominationMap.get(10) - 1);
          remaining = remaining.subtract(TEN);
        } else if (remaining.compareTo(FIVE) >= 0 && atmDenominationMap.get(5) > 0) {
          atmDispenseChange.setEuroFiveCount(atmDispenseChange.getEuroFiveCount() + 1);
          atmDenominationMap.replace(5, atmDenominationMap.get(5) - 1);
          remaining = remaining.subtract(FIVE);
        } else {
          throw new AtmDenominationException("Atm does not contains the exact amount of money");
        }
        if (remaining.equals(BigDecimal.ZERO)) {
          return atmDispenseChange;
        }
      }
    } else {
      throw new AtmMoneyShortageException("Atm does not contains the requested amount of money");
    }
  }

  HashMap<Integer, Long> createAtmDenominationMap(AtmDomain atmDomain) {
    return new HashMap<>(Map.of(50, atmDomain.getEuroFiftyCount(), 20,
        atmDomain.getEuroTwentyCount(), 10, atmDomain.getEuroTenCount(), 5,
        atmDomain.getEuroFiveCount()));
  }

  public void updateStorage(AtmDispenseChange atmDispenseChange, Long atmId) {
    AtmDomain atmDomain = atmMapper.toDomain(atmRepository.findAtmById(atmId));
    Atm atm = Atm.builder()
        .id(atmId)
        .euroFiftyCount(atmDomain.getEuroFiftyCount() - atmDispenseChange.getEuroFiftyCount())
        .euroTwentyCount(atmDomain.getEuroTwentyCount() - atmDispenseChange.getEuroTwentyCount())
        .euroTenCount(atmDomain.getEuroTenCount() - atmDispenseChange.getEuroTenCount())
        .euroFiveCount(atmDomain.getEuroFiveCount() - atmDispenseChange.getEuroFiveCount())
        .build();
    atmRepository.saveOrUpdateAtm(atm);
  }
}
