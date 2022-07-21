package com.daniel.sipos.zinkworks.repository.repositories.dataaccess;

import com.daniel.sipos.zinkworks.repository.entities.Atm;
import java.util.Optional;
import javax.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Repository;

@Repository
public interface AtmDataAccess extends JpaRepository<Atm, Long> {
  @Lock(LockModeType.PESSIMISTIC_WRITE)
  Optional<Atm> findById(long atmId);
}
