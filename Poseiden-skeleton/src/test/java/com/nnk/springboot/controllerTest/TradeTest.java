package com.nnk.springboot.controllerTest;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.service.TradeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TradeTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TradeService tradeService;

    @BeforeEach
    public void setup(){
        Trade trade = Mockito.mock(Trade.class);
        when(tradeService.findById(anyInt())).thenReturn(Optional.of(trade));
    }

    @Test
    @WithMockUser
    public void getAllTradeFromService() throws Exception {
        this.mockMvc.perform(get("/trade/list")).andDo(print()).andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    public void addTradeFromService() throws Exception {
        this.mockMvc.perform(get("/trade/add")).andDo(print()).andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    public void tradeUpdate() throws Exception {
        mockMvc.perform(get("/trade/update/1")
                        .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(view().name("trade/update"));
    }
    @Test
    @WithMockUser
    public void tradePostUpdateRedirect() throws Exception {

        mockMvc.perform(post("/trade/update/1")
                        .with(csrf()))
                .andExpect(status().isFound()).andExpect(redirectedUrl("/trade/list"));
    }

    @Test
    @WithMockUser
    public void tradePostUpdate() throws Exception {
              mockMvc.perform(post("/trade/update/1")
                        .with(csrf()))
                .andExpect(status().isFound()).andExpect(view().name("redirect:/trade/list"));
    }
    @Test
    @WithMockUser
    public void tradeValidateTest() throws Exception {
               mockMvc.perform(post("/trade/validate")
                        .with(csrf()))
                .andExpect(status().isFound()).andExpect(view().name("redirect:/trade/list"));

    }
    @Test
    @WithMockUser
    public void tradeDelete() throws Exception {
              mockMvc.perform(get("/trade/delete/1")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/trade/list"));
    }

}
