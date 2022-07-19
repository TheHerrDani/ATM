package com.daniel.sipos.zinkworks.repository.repositories;

import com.daniel.sipos.zinkworks.repository.entities.Atm;
import com.daniel.sipos.zinkworks.repository.repositories.dataaccess.AtmDataAccess;
import java.util.NoSuchElementException;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AtmRepository {
  @Autowired
  AtmDataAccess atmDataAccess;

  @Transactional
  public Atm findAtmById(Long atmId) {
    return atmDataAccess.findById(atmId)
        .orElseThrow(() -> new NoSuchElementException("There is no atm with the given id"));
  }

  public Atm saveOrUpdateAtm(Atm atm) {
    return atmDataAccess.saveAndFlush(atm);
  }
}
