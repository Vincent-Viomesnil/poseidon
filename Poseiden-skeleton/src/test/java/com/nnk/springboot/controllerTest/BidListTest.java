package com.nnk.springboot.controllerTest;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;
import com.nnk.springboot.service.BidListService;
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
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class BidListTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BidListService bidListService;

    @MockBean
    private BidListRepository bidListRepository;

    @Test
    @WithMockUser
    public void getAllBidListFromService() throws Exception {

        this.mockMvc.perform(get("/bidList/list")).andDo(print()).andExpect(status().isOk());

    }

    @Test
    @WithMockUser
    public void addBidListFromService() throws Exception {
        this.mockMvc.perform(get("/bidList/add")).andDo(print()).andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    public void bidListUpdate() throws Exception {
        BidList bidList = Mockito.mock(BidList.class);

        when(bidListService.findById(anyInt())).thenReturn(Optional.of(bidList));

        mockMvc.perform(get("/bidList/update/1")
                        .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(view().name("bidList/update"));
    }


}
