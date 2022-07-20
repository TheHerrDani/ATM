package com.daniel.sipos.zinkworks.service.services;

import static com.daniel.sipos.zinkworks.repository.AccountRepositoryTest.ACCOUNT_NUMBER;
import static com.daniel.sipos.zinkworks.repository.AtmRepositoryTest.ATM_ID;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.daniel.sipos.zinkworks.exceptions.AtmDenominationException;
import com.daniel.sipos.zinkworks.exceptions.OverLimitException;
import com.daniel.sipos.zinkworks.exceptions.RequestedAmountException;
import com.daniel.sipos.zinkworks.service.domain.AccountDomain;
import javax.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
public class AccountServiceTest {
  @Autowired
  AccountService accountService;

  @Test
  @Transactional
  public void dispenseMoney() {
    AccountDomain result = accountService.dispenseMoney(ATM_ID, ACCOUNT_NUMBER,
        400L);
    assertThat(result.getDispensableMoney()).isEqualTo(600);
  }

  @Test
  public void dispenseMoneyThrowOverLimitExceptionException() {
    assertThrows(OverLimitException.class,
        () -> accountService.dispenseMoney(ATM_ID, ACCOUNT_NUMBER, 1600));
  }

  @ParameterizedTest
  @ValueSource(longs = {-1000, -100, -10, -1, 0})
  public void checkRequestedAmountThrowRequestedAmountException(long value) {
    assertThrows(RequestedAmountException.class,
        () -> accountService.checkRequestedAmount(value));
  }

  @ParameterizedTest
  @ValueSource(longs = {13, 28, 132})
  public void checkRequestedAmountThrowAtmDenominationException(long value) {
    assertThrows(AtmDenominationException.class, () -> accountService.checkRequestedAmount(value));
  }
}
