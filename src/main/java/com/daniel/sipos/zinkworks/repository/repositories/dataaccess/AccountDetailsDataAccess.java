package com.daniel.sipos.zinkworks.repository.repositories.dataaccess;

import com.daniel.sipos.zinkworks.repository.entities.AccountDetails;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface AccountDetailsDataAccess extends JpaRepository<AccountDetails, Long> {
}
