package com.daniel.sipos.zinkworks.service.services;

import static com.daniel.sipos.zinkworks.util.Util.FIFTY;
import static com.daniel.sipos.zinkworks.util.Util.FIVE;
import static com.daniel.sipos.zinkworks.util.Util.TEN;
import static com.daniel.sipos.zinkworks.util.Util.TWENTY;

import com.daniel.sipos.zinkworks.exceptions.AtmDenominationException;
import com.daniel.sipos.zinkworks.exceptions.AtmMoneyShortageException;
import com.daniel.sipos.zinkworks.repository.entities.Atm;
import com.daniel.sipos.zinkworks.repository.repositories.AtmRepository;
import com.daniel.sipos.zinkworks.service.domain.AtmDispenseChange;
import com.daniel.sipos.zinkworks.service.domain.AtmDomain;
import com.daniel.sipos.zinkworks.service.mappers.AtmMapper;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AtmService {
  @Autowired
  AtmRepository atmRepository;

  @Autowired
  AtmMapper atmMapper;

  public AtmDispenseChange createAtmDispenseChange(long atmId, long requested) {
    AtmDomain atmDomain = atmMapper.toDomain(atmRepository.findAtmById(atmId));
    checkAtmAllMoney(requested, atmDomain);
    return doCreateAtmDispenseChange(requested, atmDomain);
  }

  private AtmDispenseChange doCreateAtmDispenseChange(long requested, AtmDomain atmDomain) {
    AtmDispenseChange atmDispenseChange = AtmDispenseChange.buildEmpty();
    long remaining = requested;
    Map<Long, Long> atmDenominationMap = createAtmDenominationMap(atmDomain);
    while (true) {
      if (isFiftyDenominationAppropriate(remaining, FIFTY, atmDenominationMap)) {
        atmDispenseChange.setEuroFiftyCount(atmDispenseChange.getEuroFiftyCount() + 1);
        remaining = getRemaining(remaining, atmDenominationMap, FIFTY);
      } else if (isFiftyDenominationAppropriate(remaining, TWENTY, atmDenominationMap)) {
        atmDispenseChange.setEuroTwentyCount(atmDispenseChange.getEuroTwentyCount() + 1);
        remaining = getRemaining(remaining, atmDenominationMap, TWENTY);
      } else if (isFiftyDenominationAppropriate(remaining, TEN, atmDenominationMap)) {
        atmDispenseChange.setEuroTenCount(atmDispenseChange.getEuroTenCount() + 1);
        remaining = getRemaining(remaining, atmDenominationMap, TEN);
      } else if (isFiftyDenominationAppropriate(remaining, FIVE, atmDenominationMap)) {
        atmDispenseChange.setEuroFiveCount(atmDispenseChange.getEuroFiveCount() + 1);
        remaining = getRemaining(remaining, atmDenominationMap, FIVE);
      } else {
        throw new AtmDenominationException("Atm does not contains the exact amount of money");
      }
      if (remaining == 0) {
        return atmDispenseChange;
      }
    }
  }

  //TODO Test
  private long getRemaining(long remaining, Map<Long, Long> atmDenominationMap, long denomination) {
    atmDenominationMap.replace(denomination, atmDenominationMap.get(denomination) - 1);
    remaining -= denomination;
    return remaining;
  }

  //TODO Test
  boolean isFiftyDenominationAppropriate(long remaining, long denomination,
                                         Map<Long, Long> atmDenominationMap) {
    return remaining >= denomination && atmDenominationMap.get(denomination) > 0;
  }

  //TODO Test
  void checkAtmAllMoney(long requested, AtmDomain atmDomain) {
    if (atmDomain.getAllMoney() < requested) {
      throw new AtmMoneyShortageException("Atm does not contains the requested amount of money");
    }
  }

  Map<Long, Long> createAtmDenominationMap(AtmDomain atmDomain) {
    return new HashMap<>(Map.of(FIFTY, atmDomain.getEuroFiftyCount(), TWENTY,
        atmDomain.getEuroTwentyCount(), TEN, atmDomain.getEuroTenCount(), FIVE,
        atmDomain.getEuroFiveCount()));
  }

  public void updateStorage(AtmDispenseChange atmDispenseChange, long atmId) {
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
