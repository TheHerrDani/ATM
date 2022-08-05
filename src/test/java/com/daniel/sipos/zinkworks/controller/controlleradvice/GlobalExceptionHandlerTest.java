package com.daniel.sipos.atm.controller.controlleradvice;

import static com.daniel.sipos.atm.controller.controlleradvice.GlobalExceptionHandler.GENERIC_ERROR;
import static com.daniel.sipos.atm.service.services.AccountService.ACCOUNT_BALANCE_SHORTAGE;
import static com.daniel.sipos.atm.service.services.AccountService.CAN_NOT_BE_0;
import static com.daniel.sipos.atm.service.services.AccountService.MUST_BE_A_POSITIVE_NUMBER;
import static com.daniel.sipos.atm.service.services.AccountService.MUST_BE_DIVISIBLE_BY_5;
import static com.daniel.sipos.atm.service.services.AtmService.ATM_MONEY_SHORTAGE;
import static com.daniel.sipos.atm.util.AccountConstants.BAD_ACCOUNT_NUMBER;
import static com.daniel.sipos.atm.util.ControllerConstants.ATM_SHORTAGE_MODEL;
import static com.daniel.sipos.atm.util.ControllerConstants.ATM_SHORTAGE_MODEL_2;
import static com.daniel.sipos.atm.util.ControllerConstants.BAD_DISPENSE_MONEY_MODEL;
import static com.daniel.sipos.atm.util.ControllerConstants.DISPENSE_MONEY_MODEL;
import static com.daniel.sipos.atm.util.ControllerConstants.REQUESTED_AMOUNT_ERROR;
import static com.daniel.sipos.atm.util.ControllerConstants.REQUESTED_AMOUNT_ERROR_2;
import static com.daniel.sipos.atm.util.ControllerConstants.TOO_MUCH_DISPENSE_MONEY_MODEL;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import javax.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
@Transactional
public class GlobalExceptionHandlerTest {

  @Autowired
  private MockMvc mockMvc;
  @Autowired
  private ObjectMapper objectMapper;

  @Test
  public void unauthorized() throws Exception {
    this.mockMvc.perform(post("/api/account/dispense-money")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(DISPENSE_MONEY_MODEL)))
        .andDo(print())
        .andExpect(status().isUnauthorized());
  }

  @Test
  @WithMockUser
  public void handleNoSuchElementException() throws Exception {
    this.mockMvc.perform(get("/api/account/information")
            .param("accountNumber", BAD_ACCOUNT_NUMBER))
        .andDo(print())
        .andExpect(status().isNotFound())
        .andExpect(content().string(containsString(GENERIC_ERROR)));
  }

  @Test
  @WithMockUser
  public void handleAtmDenominationException() throws Exception {
    this.mockMvc.perform(post("/api/account/dispense-money")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(BAD_DISPENSE_MONEY_MODEL)))
        .andDo(print())
        .andExpect(status().isBadRequest())
        .andExpect(content().string(containsString(MUST_BE_DIVISIBLE_BY_5)));
  }

  @Test
  @WithMockUser
  public void handleAtmMoneyShortageException() throws Exception {
    this.mockMvc.perform(post("/api/account/dispense-money")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(ATM_SHORTAGE_MODEL)))
        .andDo(print())
        .andExpect(status().isOk());
    this.mockMvc.perform(post("/api/account/dispense-money")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(ATM_SHORTAGE_MODEL_2)))
        .andDo(print())
        .andExpect(status().isBadRequest())
        .andExpect(content().string(containsString(ATM_MONEY_SHORTAGE)));
  }

  @Test
  @WithMockUser
  public void handleOverLimitException() throws Exception {
    this.mockMvc.perform(post("/api/account/dispense-money")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(TOO_MUCH_DISPENSE_MONEY_MODEL)))
        .andDo(print())
        .andExpect(status().isBadRequest())
        .andExpect(content().string(containsString(ACCOUNT_BALANCE_SHORTAGE)));
  }

  @Test
  @WithMockUser
  public void handleRequestedAmountException() throws Exception {
    this.mockMvc.perform(post("/api/account/dispense-money")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(REQUESTED_AMOUNT_ERROR)))
        .andDo(print())
        .andExpect(status().isBadRequest())
        .andExpect(content().string(containsString(MUST_BE_A_POSITIVE_NUMBER)));
    this.mockMvc.perform(post("/api/account/dispense-money")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(REQUESTED_AMOUNT_ERROR_2)))
        .andDo(print())
        .andExpect(status().isBadRequest())
        .andExpect(content().string(containsString(CAN_NOT_BE_0)));
  }
}
