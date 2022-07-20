package com.daniel.sipos.zinkworks.repository.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
public class Atm {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private long id;

  @Column(name = "euro_fifty_count", nullable = false)
  private long euroFiftyCount;

  @Column(name = "euro_twenty_count", nullable = false)
  private long euroTwentyCount;

  @Column(name = "euro_ten_count", nullable = false)
  private long euroTenCount;

  @Column(name = "euro_five_count", nullable = false)
  private long euroFiveCount;
}
