package com.nnk.springboot.controllerTest;

import com.nnk.springboot.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserTest {

    @Autowired
    private MockMvc mockMvc;


    @MockBean
    private UserRepository userRepository;

    @Test
    @WithMockUser(username="admin",authorities={"ADMIN"})
    public void getAllUserFromService() throws Exception {

        this.mockMvc.perform(get("/user/list")).andDo(print()).andExpect(status().isOk());

    }

    @Test
    @WithMockUser(username="admin",authorities={"ADMIN"})
    public void addUserFromService() throws Exception {
        this.mockMvc.perform(get("/user/add")).andDo(print()).andExpect(status().isOk());
    }

}
