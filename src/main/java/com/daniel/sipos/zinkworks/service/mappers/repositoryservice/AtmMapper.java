package com.daniel.sipos.zinkworks.service.mappers.repositoryservice;

import com.daniel.sipos.zinkworks.repository.entities.Atm;
import com.daniel.sipos.zinkworks.service.domain.AtmDomain;
import org.springframework.stereotype.Service;

@Service
public class AtmMapper {

  public AtmDomain toDomain(Atm atm) {
    return AtmDomain.builder()
        .id(atm.getId())
        .euroFiftyCount(atm.getEuroFiftyCount())
        .euroTwentyCount(atm.getEuroTwentyCount())
        .euroTenCount(atm.getEuroTenCount())
        .euroFiveCount(atm.getEuroFiveCount())
        .build();
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
