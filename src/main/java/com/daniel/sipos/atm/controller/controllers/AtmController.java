package com.daniel.sipos.atm.controller.controllers;

import com.daniel.sipos.atm.controller.models.AtmDataModel;
import com.daniel.sipos.atm.service.services.AtmService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(path = "/api/atm")
@AllArgsConstructor
public class AtmController {

  private final AtmService atmService;

  @GetMapping(path = "/information")
  public @ResponseBody
  AtmDataModel getAtmInformation(@RequestParam long atmId) {
    return atmService.getAccountInformation(atmId);
  }
}