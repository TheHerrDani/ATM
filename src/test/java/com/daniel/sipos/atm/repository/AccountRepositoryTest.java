package com.daniel.sipos.atm.repository;

import static com.daniel.sipos.atm.util.AccountConstants.ACCOUNT_NUMBER;
import static com.daniel.sipos.atm.util.AccountConstants.TWO_HUNDRED;
import static com.daniel.sipos.atm.util.AccountDetailsConstants.EIGHT_HUNDRED;
import static org.assertj.core.api.Assertions.assertThat;

import com.daniel.sipos.atm.repository.entities.Account;
import com.daniel.sipos.atm.repository.repositories.AccountRepository;
import javax.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
public class AccountRepositoryTest {

  @Autowired
  private AccountRepository accountRepository;


  @Autowired
  private BCryptPasswordEncoder bCryptPasswordEncoder;

  @Test
  @Transactional
  public void findAccountByAccountNumber() {
    Account account = accountRepository.findAccountByAccountNumber(ACCOUNT_NUMBER);

    assertThat(account.getAccountNumber()).isEqualTo(ACCOUNT_NUMBER);
    assertThat(bCryptPasswordEncoder.matches("1234", account.getPin())).isTrue();
    assertThat(account.getAccountDetails().getActualBalance()).isEqualTo(EIGHT_HUNDRED);
    assertThat(account.getAccountDetails().getOverdraft()).isEqualTo(TWO_HUNDRED);


  }
}
