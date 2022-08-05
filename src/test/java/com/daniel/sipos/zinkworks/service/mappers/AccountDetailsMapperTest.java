package com.daniel.sipos.atm.service.mappers;

import static com.daniel.sipos.atm.util.AccountDetailsConstants.ACCOUNT_DETAILS_DOMAIN;
import static com.daniel.sipos.atm.util.AccountDetailsConstants.ACCOUNT_DETAILS_ID;
import static com.daniel.sipos.atm.util.AccountDetailsConstants.ACTUAL_BALANCE;
import static com.daniel.sipos.atm.util.AccountDetailsConstants.TWO_HUNDRED;
import static com.daniel.sipos.atm.util.AccountDetailsConstants.createAccountDetails;
import static org.assertj.core.api.Assertions.assertThat;

import com.daniel.sipos.atm.repository.entities.AccountDetails;
import com.daniel.sipos.atm.service.domain.AccountDetailsDomain;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
public class AccountDetailsMapperTest {

  @Autowired
  private AccountDetailsMapper accountDetailsMapper;

  @Test
  public void toDomain() {

    AccountDetailsDomain accountDetailsDomain = accountDetailsMapper.toDomain(
        createAccountDetails(ACCOUNT_DETAILS_ID, ACTUAL_BALANCE, TWO_HUNDRED));

    assertThat(accountDetailsDomain.getId()).isEqualTo(ACCOUNT_DETAILS_ID);
    assertThat(accountDetailsDomain.getActualBalance()).isEqualTo(ACTUAL_BALANCE);
    assertThat(accountDetailsDomain.getOverdraft()).isEqualTo(TWO_HUNDRED);
  }

  @Test
  public void toEntity() {
    AccountDetails accountDetails = accountDetailsMapper.toEntity(ACCOUNT_DETAILS_DOMAIN);

    assertThat(accountDetails.getId()).isEqualTo(ACCOUNT_DETAILS_ID);
    assertThat(accountDetails.getActualBalance()).isEqualTo(ACTUAL_BALANCE);
    assertThat(accountDetails.getOverdraft()).isEqualTo(TWO_HUNDRED);
  }
}
