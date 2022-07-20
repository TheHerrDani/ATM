package com.daniel.sipos.zinkworks.controller.controllers;

import com.daniel.sipos.zinkworks.controller.models.AtmDataModel;
import com.daniel.sipos.zinkworks.service.services.AtmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(path = "/api/atm")
public class AtmController {
  @Autowired
  AtmService atmService;

  @GetMapping(path = "/get-atm-information")
  public @ResponseBody
  AtmDataModel getAtmInformation(@RequestParam long atmId) {
    return atmService.getAccountInformation(atmId);
  }
}