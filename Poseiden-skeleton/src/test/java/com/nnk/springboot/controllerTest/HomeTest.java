package com.nnk.springboot.controllerTest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class HomeTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser
    public void getAllCurvePointFromService() throws Exception {
        this.mockMvc.perform(get("/")).andDo(print()).andExpect(status().isOk());

    }

    @Test
    @WithMockUser(username="admin",roles={"ADMIN"})
    public void addCurvePointFromService() throws Exception {
        this.mockMvc.perform(get("/admin/home")).andDo(print()).andExpect(redirectedUrl("/bidList/list"));
    }

}
