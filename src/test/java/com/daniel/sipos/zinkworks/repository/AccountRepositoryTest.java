package com.daniel.sipos.zinkworks.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.daniel.sipos.zinkworks.repository.entities.Account;
import com.daniel.sipos.zinkworks.repository.repositories.AccountRepository;
import java.math.BigDecimal;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
public class AccountRepositoryTest {
  public static final Long ACCOUNT_NUMBER = 123456789L;
  public static final Integer PIN_NUMBER = 1234;
  @Autowired
  AccountRepository accountRepository;

  @Test
  public void findAccountByAccountNumber() {

    Account account = accountRepository.findAccountByAccountNumber(ACCOUNT_NUMBER);

    assertThat(account.getAccountNumber()).isEqualTo(ACCOUNT_NUMBER);
    assertThat(account.getPin()).isEqualTo(PIN_NUMBER);
    assertThat(account.getAccountDetails().getActualBalance()).isEqualTo(new BigDecimal(800));
    assertThat(account.getAccountDetails().getOverdraft()).isEqualTo(new BigDecimal(200));
  }
}
