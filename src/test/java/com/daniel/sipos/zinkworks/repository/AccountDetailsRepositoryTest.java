package com.daniel.sipos.zinkworks.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.daniel.sipos.zinkworks.repository.entities.AccountDetails;
import com.daniel.sipos.zinkworks.repository.repositories.AccountDetailsRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
public class AccountDetailsRepositoryTest {
  public static final long ACCOUNT_DETAILS_ID = 3L;
  public static final long ACTUAL_BALANCE = 400L;
  public static final long NEW_BALANCE = 800L;
  public static final long OVERDRAFT = 200L;

  @Autowired
  AccountDetailsRepository accountDetailsRepository;

  @Test
  public void updateAccountDetails() {
    AccountDetails accountDetails =
        createAccountDetails(ACCOUNT_DETAILS_ID, ACTUAL_BALANCE, OVERDRAFT);
    accountDetails = accountDetailsRepository.saveOrUpdate(accountDetails);

    assertThat(accountDetails.getId()).isEqualTo(ACCOUNT_DETAILS_ID);
    assertThat(accountDetails.getActualBalance()).isEqualTo(ACTUAL_BALANCE);
    assertThat(accountDetails.getOverdraft()).isEqualTo(OVERDRAFT);

    accountDetails = createAccountDetails(ACCOUNT_DETAILS_ID, NEW_BALANCE, OVERDRAFT);
    accountDetails = accountDetailsRepository.saveOrUpdate(accountDetails);

    assertThat(accountDetails.getId()).isEqualTo(ACCOUNT_DETAILS_ID);
    assertThat(accountDetails.getActualBalance()).isEqualTo(NEW_BALANCE);
    assertThat(accountDetails.getOverdraft()).isEqualTo(OVERDRAFT);
  }

  public static AccountDetails createAccountDetails(Long accountDetailsId, long actualBalance,
                                                    long overdraft) {
    return AccountDetails.builder()
        .id(accountDetailsId)
        .actualBalance(actualBalance)
        .overdraft(overdraft)
        .build();
  }
}
