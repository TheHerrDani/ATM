package com.daniel.sipos.zinkworks.repository.repositories;

import com.daniel.sipos.zinkworks.repository.entities.Atm;
import com.daniel.sipos.zinkworks.repository.repositories.dataaccess.AtmDataAccess;
import java.util.NoSuchElementException;
import javax.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class AtmRepository {

  private final AtmDataAccess atmDataAccess;

  @Transactional
  public Atm findAtmById(long atmId) {
    return atmDataAccess.findById(atmId)
        .orElseThrow(() -> new NoSuchElementException("There is no atm with the given id"));
  }

  public Atm saveOrUpdateAtm(Atm atm) {
    return atmDataAccess.saveAndFlush(atm);
  }
}
