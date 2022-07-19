package com.daniel.sipos.zinkworks.repository.repositories.dataaccess;

import com.daniel.sipos.zinkworks.repository.entities.Account;
import java.util.Optional;
import javax.persistence.LockModeType;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface AccountDataAccess extends JpaRepository<Account, Long> {
  @Lock(LockModeType.PESSIMISTIC_WRITE)
  Optional<Account> findByAccountNumber(Long accountNumber);
}