package com.daniel.sipos.atm.service.mappers;

import static com.daniel.sipos.atm.util.AccountConstants.ACCOUNT_DOMAIN;
import static com.daniel.sipos.atm.util.AccountConstants.ACCOUNT_NUMBER;
import static com.daniel.sipos.atm.util.AccountConstants.DISPENSABLE_MONEY;
import static com.daniel.sipos.atm.util.AccountConstants.PIN_NUMBER;
import static com.daniel.sipos.atm.util.AccountDetailsConstants.ACCOUNT_DETAILS_ID;
import static com.daniel.sipos.atm.util.AccountDetailsConstants.ACTUAL_BALANCE;
import static com.daniel.sipos.atm.util.AccountDetailsConstants.TWO_HUNDRED;
import static com.daniel.sipos.atm.util.AccountDetailsConstants.createAccountDetails;
import static org.assertj.core.api.Assertions.assertThat;

import com.daniel.sipos.atm.repository.entities.Account;
import com.daniel.sipos.atm.repository.entities.AccountDetails;
import com.daniel.sipos.atm.service.domain.AccountDomain;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
public class AccountMapperTest {

  @Autowired
  private AccountMapper accountMapper;

  @Test
  public void toDomain() {
    AccountDetails accountDetails =
        createAccountDetails(ACCOUNT_DETAILS_ID, ACTUAL_BALANCE, TWO_HUNDRED);


    Account account = Account.builder()
        .accountNumber(ACCOUNT_NUMBER)
        .pin(PIN_NUMBER)
        .accountDetails(accountDetails)
        .build();

    AccountDomain accountDomain = accountMapper.toDomain(account);

    assertThat(accountDomain.getAccountNumber()).isEqualTo(ACCOUNT_NUMBER);
    assertThat(accountDomain.getPin()).isEqualTo(PIN_NUMBER);
    assertThat(accountDomain.getAccountDetails().getActualBalance()).isEqualTo(ACTUAL_BALANCE);
    assertThat(accountDomain.getDispensableMoney()).isEqualTo(DISPENSABLE_MONEY);
    assertThat(accountDomain.getAccountDetails().getOverdraft()).isEqualTo(TWO_HUNDRED);
  }

  @Test
  public void toEntity() {
    Account account = accountMapper.toEntity(ACCOUNT_DOMAIN);

    assertThat(account.getAccountNumber()).isEqualTo(ACCOUNT_NUMBER);
    assertThat(account.getPin()).isEqualTo(PIN_NUMBER);
    assertThat(account.getAccountDetails().getActualBalance()).isEqualTo(ACTUAL_BALANCE);
    assertThat(account.getAccountDetails().getOverdraft()).isEqualTo(TWO_HUNDRED);
  }
}
