package com.daniel.sipos.zinkworks.service.services;

import static com.daniel.sipos.zinkworks.util.Util.FIFTY;
import static com.daniel.sipos.zinkworks.util.Util.FIVE;
import static com.daniel.sipos.zinkworks.util.Util.TEN;
import static com.daniel.sipos.zinkworks.util.Util.TWENTY;

import com.daniel.sipos.zinkworks.controller.mappers.AtmDataModelMapper;
import com.daniel.sipos.zinkworks.controller.models.AtmDataModel;
import com.daniel.sipos.zinkworks.exceptions.AtmDenominationException;
import com.daniel.sipos.zinkworks.exceptions.AtmMoneyShortageException;
import com.daniel.sipos.zinkworks.repository.entities.Atm;
import com.daniel.sipos.zinkworks.repository.repositories.AtmRepository;
import com.daniel.sipos.zinkworks.service.domain.AtmDispenseChange;
import com.daniel.sipos.zinkworks.service.domain.AtmDomain;
import com.daniel.sipos.zinkworks.service.mappers.AtmMapper;
import java.util.HashMap;
import java.util.Map;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AtmService {

  public static final String ATM_MONEY_SHORTAGE =
      "Atm does not contains the requested amount of money";
  private final AtmRepository atmRepository;
  private final AtmMapper atmMapper;
  private final AtmDataModelMapper atmDataModelMapper;

  public AtmDataModel getAccountInformation(long atmId) {
    return atmDataModelMapper.toModel(atmMapper.toDomain(atmRepository.findAtmById(atmId)));
  }

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
      if (isActualDenominationAppropriate(remaining, FIFTY, atmDenominationMap)) {
        atmDispenseChange.increaseFiftyByOne();
        remaining = getRemaining(remaining, atmDenominationMap, FIFTY);
      } else if (isActualDenominationAppropriate(remaining, TWENTY, atmDenominationMap)) {
        atmDispenseChange.increaseTwentyByOne();
        remaining = getRemaining(remaining, atmDenominationMap, TWENTY);
      } else if (isActualDenominationAppropriate(remaining, TEN, atmDenominationMap)) {
        atmDispenseChange.increaseTenByOne();
        remaining = getRemaining(remaining, atmDenominationMap, TEN);
      } else if (isActualDenominationAppropriate(remaining, FIVE, atmDenominationMap)) {
        atmDispenseChange.increaseFiveByOne();
        remaining = getRemaining(remaining, atmDenominationMap, FIVE);
      } else {
        throw new AtmDenominationException("Atm does not contains the exact amount of money");
      }
      if (remaining == 0) {
        return atmDispenseChange;
      }
    }
  }

  private long getRemaining(long remaining, Map<Long, Long> atmDenominationMap, long denomination) {
    atmDenominationMap.replace(denomination, atmDenominationMap.get(denomination) - 1);
    remaining -= denomination;
    return remaining;
  }

  private boolean isActualDenominationAppropriate(long remaining, long denomination,
                                                  Map<Long, Long> atmDenominationMap) {
    return remaining >= denomination && atmDenominationMap.get(denomination) > 0;
  }

  private void checkAtmAllMoney(long requested, AtmDomain atmDomain) {
    if (atmDomain.getAllMoney() < requested) {
      throw new AtmMoneyShortageException(ATM_MONEY_SHORTAGE);
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
