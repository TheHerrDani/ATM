package com.daniel.sipos.atm.controller.controllers;

import com.daniel.sipos.atm.controller.mappers.DispenseDataModelMapper;
import com.daniel.sipos.atm.controller.models.AccountDataModel;
import com.daniel.sipos.atm.controller.models.DispenseDataModel;
import com.daniel.sipos.atm.controller.models.DispenseMoneyModel;
import com.daniel.sipos.atm.service.services.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/account")
@AllArgsConstructor
public class AccountController {

  private final AccountService accountService;
  private final DispenseDataModelMapper dispenseDataModelMapper;

  @GetMapping(path = "/information")
  public @ResponseBody
  AccountDataModel getAccountInformation(@RequestParam String accountNumber) {
    return accountService.getAccountInformation(accountNumber);
  }

  @PostMapping(path = "/dispense-money")
  public @ResponseBody
  DispenseDataModel dispenseMoney(@RequestBody DispenseMoneyModel dispenseMoneyModel) {
    return dispenseDataModelMapper.toModel(
        accountService.dispenseMoney(dispenseMoneyModel.getAtmId(),
            dispenseMoneyModel.getAccountNumber(), dispenseMoneyModel.getRequestedMoney()));
  }
}
