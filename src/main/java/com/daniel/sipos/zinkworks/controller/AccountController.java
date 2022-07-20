package com.daniel.sipos.zinkworks.controller;

import com.daniel.sipos.zinkworks.service.domain.AccountDomain;
import com.daniel.sipos.zinkworks.service.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/account")
public class AccountController {
  @Autowired
  AccountService accountService;

  @GetMapping(path = "/dispense-money")
  public @ResponseBody
  AccountDomain dispenseMoney(@RequestParam Long atmId, @RequestParam String accountNumber,
                              @RequestParam long requestedMoney) {
    return accountService.dispenseMoney(atmId, accountNumber, requestedMoney);
  }
}
