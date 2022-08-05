package com.daniel.sipos.atm.controller.mappers;

import com.daniel.sipos.atm.controller.models.AtmDataModel;
import com.daniel.sipos.atm.service.domain.AtmDomain;
import org.springframework.stereotype.Service;

@Service
public class AtmDataModelMapper {

  public AtmDataModel toModel(AtmDomain atmDomain) {
    return AtmDataModel.builder()
        .euroFiftyCount(atmDomain.getEuroFiftyCount())
        .euroTwentyCount(atmDomain.getEuroTwentyCount())
        .euroTenCount(atmDomain.getEuroTenCount())
        .euroFiveCount(atmDomain.getEuroFiveCount())
        .atmAllMoney(atmDomain.getAllMoney())
        .build();
  }
}
