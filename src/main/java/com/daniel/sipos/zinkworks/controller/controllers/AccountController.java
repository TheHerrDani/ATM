package com.daniel.sipos.zinkworks.controller.controllers;

import com.daniel.sipos.zinkworks.controller.models.AccountDataModel;
import com.daniel.sipos.zinkworks.controller.models.DispenseDataModel;
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

  @GetMapping(path = "/get-account-information")
  public @ResponseBody
  AccountDataModel getAccountInformation(@RequestParam String accountNumber) {
    return accountService.getAccountInformation(accountNumber);
  }

  @GetMapping(path = "/dispense-money")
  public @ResponseBody
  DispenseDataModel dispenseMoney(@RequestParam Long atmId, @RequestParam String accountNumber,
                                  @RequestParam long requestedMoney) {
    return accountService.dispenseMoney(atmId, accountNumber, requestedMoney);
  }
}
