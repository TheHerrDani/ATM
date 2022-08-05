package com.daniel.sipos.atm.repository;

import static com.daniel.sipos.atm.util.AccountDetailsConstants.ACCOUNT_DETAILS_ID_ZERO;
import static com.daniel.sipos.atm.util.AccountDetailsConstants.ACTUAL_BALANCE;
import static com.daniel.sipos.atm.util.AccountDetailsConstants.EIGHT_HUNDRED;
import static com.daniel.sipos.atm.util.AccountDetailsConstants.TWO_HUNDRED;
import static com.daniel.sipos.atm.util.AccountDetailsConstants.createAccountDetails;
import static org.assertj.core.api.Assertions.assertThat;

import com.daniel.sipos.atm.repository.entities.AccountDetails;
import com.daniel.sipos.atm.repository.repositories.AccountDetailsRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
public class AccountDetailsRepositoryTest {

  @Autowired
  private AccountDetailsRepository accountDetailsRepository;

  @Test
  public void updateAccountDetails() {
    AccountDetails accountDetails =
        createAccountDetails(ACCOUNT_DETAILS_ID_ZERO, ACTUAL_BALANCE, TWO_HUNDRED);
    accountDetails = accountDetailsRepository.saveOrUpdate(accountDetails);

    assertThat(accountDetails.getActualBalance()).isEqualTo(ACTUAL_BALANCE);
    assertThat(accountDetails.getOverdraft()).isEqualTo(TWO_HUNDRED);

    long newId = accountDetails.getId();
    accountDetails = createAccountDetails(newId, EIGHT_HUNDRED, TWO_HUNDRED);
    accountDetails = accountDetailsRepository.saveOrUpdate(accountDetails);

    assertThat(accountDetails.getId()).isEqualTo(newId);
    assertThat(accountDetails.getActualBalance()).isEqualTo(EIGHT_HUNDRED);
    assertThat(accountDetails.getOverdraft()).isEqualTo(TWO_HUNDRED);
  }
}
