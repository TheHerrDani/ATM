package com.daniel.sipos.zinkworks.repository.repositories.dataaccess;

import com.daniel.sipos.zinkworks.repository.entities.Account;
import java.util.Optional;
import javax.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountDataAccess extends JpaRepository<Account, Long> {
  @Lock(LockModeType.PESSIMISTIC_WRITE)
  Optional<Account> findByAccountNumber(String accountNumber);
}
