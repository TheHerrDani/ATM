package com.daniel.sipos.zinkworks.controller.controllers;

import static com.daniel.sipos.zinkworks.util.AtmConstants.ATM_ID;
import static com.daniel.sipos.zinkworks.util.ControllerConstants.ATM_INFO;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
public class AtmControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Test
  @WithMockUser
  public void getAtmInformation() throws Exception {
    this.mockMvc.perform(get("/api/atm/information")
            .param("atmId", String.valueOf(ATM_ID)))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().string(containsString(ATM_INFO)));
  }
}
