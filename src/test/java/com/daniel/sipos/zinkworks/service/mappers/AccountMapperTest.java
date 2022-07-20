package com.daniel.sipos.zinkworks.service.mappers;

import static com.daniel.sipos.zinkworks.repository.AccountDetailsRepositoryTest.ACCOUNT_DETAILS_ID;
import static com.daniel.sipos.zinkworks.repository.AccountDetailsRepositoryTest.ACTUAL_BALANCE;
import static com.daniel.sipos.zinkworks.repository.AccountDetailsRepositoryTest.OVERDRAFT;
import static com.daniel.sipos.zinkworks.repository.AccountDetailsRepositoryTest.createAccountDetails;
import static com.daniel.sipos.zinkworks.repository.AccountRepositoryTest.ACCOUNT_NUMBER;
import static com.daniel.sipos.zinkworks.repository.AccountRepositoryTest.PIN_NUMBER;
import static com.daniel.sipos.zinkworks.service.mappers.AccountDetailsMapperTest.ACCOUNT_DETAILS_DOMAIN;
import static org.assertj.core.api.Assertions.assertThat;

import com.daniel.sipos.zinkworks.repository.entities.Account;
import com.daniel.sipos.zinkworks.repository.entities.AccountDetails;
import com.daniel.sipos.zinkworks.service.domain.AccountDomain;
import com.daniel.sipos.zinkworks.service.mappers.repositoryservice.AccountMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
public class AccountMapperTest {
  private static final Long ACCOUNT_ID = 1L;
  private static final long DISPENSABLE_MONEY = 600L;
  @Autowired
  private AccountMapper accountMapper;

  @Test
  public void toDomain() {
    AccountDetails accountDetails =
        createAccountDetails(ACCOUNT_DETAILS_ID, ACTUAL_BALANCE, OVERDRAFT);


    Account account = Account.builder()
        .id(ACCOUNT_ID)
        .accountNumber(ACCOUNT_NUMBER)
        .pin(PIN_NUMBER)
        .accountDetails(accountDetails)
        .build();

    AccountDomain accountDomain = accountMapper.toDomain(account);

    assertThat(accountDomain.getAccountNumber()).isEqualTo(ACCOUNT_NUMBER);
    assertThat(accountDomain.getPin()).isEqualTo(PIN_NUMBER);
    assertThat(accountDomain.getAccountDetails().getActualBalance()).isEqualTo(ACTUAL_BALANCE);
    assertThat(accountDomain.getDispensableMoney()).isEqualTo(DISPENSABLE_MONEY);
    assertThat(accountDomain.getAccountDetails().getOverdraft()).isEqualTo(OVERDRAFT);
  }

  @Test
  public void toEntity() {
    AccountDomain accountDomain = AccountDomain.builder()
        .id(ACCOUNT_ID)
        .accountNumber(ACCOUNT_NUMBER)
        .pin(PIN_NUMBER)
        .accountDetails(ACCOUNT_DETAILS_DOMAIN)
        .build();

    Account account = accountMapper.toEntity(accountDomain);

    assertThat(account.getId()).isEqualTo(ACCOUNT_ID);
    assertThat(account.getAccountNumber()).isEqualTo(ACCOUNT_NUMBER);
    assertThat(account.getPin()).isEqualTo(PIN_NUMBER);
    assertThat(account.getAccountDetails().getActualBalance()).isEqualTo(ACTUAL_BALANCE);
    assertThat(account.getAccountDetails().getOverdraft()).isEqualTo(OVERDRAFT);
  }
}
