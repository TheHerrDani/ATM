package com.daniel.sipos.zinkworks.service.mappers;

import static com.daniel.sipos.zinkworks.repository.AccountDetailsRepositoryTest.ACCOUNT_DETAILS_ID;
import static com.daniel.sipos.zinkworks.repository.AccountDetailsRepositoryTest.ACTUAL_BALANCE;
import static com.daniel.sipos.zinkworks.repository.AccountDetailsRepositoryTest.OVERDRAFT;
import static org.assertj.core.api.Assertions.assertThat;

import com.daniel.sipos.zinkworks.repository.entities.AccountDetails;
import com.daniel.sipos.zinkworks.service.domain.AccountDetailsDomain;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
public class AccountDetailsMapperTest {

  public static final AccountDetailsDomain ACCOUNT_DETAILS_DOMAIN = AccountDetailsDomain.builder()
      .id(ACCOUNT_DETAILS_ID)
      .actualBalance(ACTUAL_BALANCE)
      .overdraft(OVERDRAFT)
      .build();
  @Autowired
  private AccountDetailsMapper accountDetailsMapper;

  @Test
  public void toDomain() {

    AccountDetails accountDetails = AccountDetails.builder()
        .id(ACCOUNT_DETAILS_ID)
        .actualBalance(ACTUAL_BALANCE)
        .overdraft(OVERDRAFT)
        .build();

    AccountDetailsDomain accountDetailsDomain = accountDetailsMapper.toDomain(accountDetails);

    assertThat(accountDetailsDomain.getId()).isEqualTo(ACCOUNT_DETAILS_ID);
    assertThat(accountDetailsDomain.getActualBalance()).isEqualTo(ACTUAL_BALANCE);
    assertThat(accountDetailsDomain.getOverdraft()).isEqualTo(OVERDRAFT);
  }

  @Test
  public void toEntity() {

    AccountDetailsDomain accountDetailsDomain = ACCOUNT_DETAILS_DOMAIN;

    AccountDetails accountDetails = accountDetailsMapper.toEntity(accountDetailsDomain);

    assertThat(accountDetails.getId()).isEqualTo(ACCOUNT_DETAILS_ID);
    assertThat(accountDetails.getActualBalance()).isEqualTo(ACTUAL_BALANCE);
    assertThat(accountDetails.getOverdraft()).isEqualTo(OVERDRAFT);
  }
}
