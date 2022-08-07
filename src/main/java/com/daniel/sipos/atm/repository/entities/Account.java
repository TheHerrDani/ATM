package com.daniel.sipos.atm.repository.entities;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Account {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private long id;

  @OneToOne()
  @JoinColumn(name = "account_details", referencedColumnName = "id", nullable = false)
  private AccountDetails accountDetails;

  @Column(name = "account_number", nullable = false)
  private String accountNumber;

  @Column(name = "pin", nullable = false)
  private String pin;

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Account account)) {
      return false;
    }
    return Objects.equals(getId(), account.getId());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getId());
  }
}

