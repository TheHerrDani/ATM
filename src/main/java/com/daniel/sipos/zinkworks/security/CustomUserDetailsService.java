package com.daniel.sipos.zinkworks.security;

import com.daniel.sipos.zinkworks.repository.entities.Account;
import com.daniel.sipos.zinkworks.repository.repositories.AccountRepository;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

  private final PasswordEncoder passwordEncoder;
  private final AccountRepository accountRepository;

  @Override
  public UserDetails loadUserByUsername(String accountNumber) throws UsernameNotFoundException {
    Account account = accountRepository.findAccountByAccountNumber(accountNumber);
    String username = account.getAccountNumber();
    String password = account.getPin();


    List<SimpleGrantedAuthority> authList = getAuthorities();

    return User.builder()
        .username(username)
        .password(password)
        .authorities(authList)
        .passwordEncoder(passwordEncoder::encode)
        .build();
  }

  private List<SimpleGrantedAuthority> getAuthorities() {
    List<SimpleGrantedAuthority> authList = new ArrayList<>();
    authList.add(new SimpleGrantedAuthority("ROLE_USER"));

    return authList;
  }
}
