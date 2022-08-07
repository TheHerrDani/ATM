package com.daniel.sipos.atm.controller.controllers;

import static com.daniel.sipos.atm.util.AccountConstants.ACCOUNT_NUMBER;
import static com.daniel.sipos.atm.util.ControllerConstants.ACCOUNT_INFO;
import static com.daniel.sipos.atm.util.ControllerConstants.DISPENSE_INFO;
import static com.daniel.sipos.atm.util.ControllerConstants.DISPENSE_MONEY_MODEL;
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
public class AccountControllerTest {

  @Autowired
  private MockMvc mockMvc;
  @Autowired
  private ObjectMapper objectMapper;

  @Test
  @WithMockUser
  public void getAccountInformation() throws Exception {
    this.mockMvc.perform(get("/api/account/information")
            .param("accountNumber", ACCOUNT_NUMBER))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().string(containsString(ACCOUNT_INFO)));
  }

  @Test
  @WithMockUser
  public void dispenseMoney() throws Exception {
    this.mockMvc.perform(post("/api/account/dispense-money")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(DISPENSE_MONEY_MODEL)))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().string(containsString(DISPENSE_INFO)));
  }
}
