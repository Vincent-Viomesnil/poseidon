package com.nnk.springboot.controllerTest;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;
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
public class UserTest {

    @Autowired
    private MockMvc mockMvc;


    @MockBean
    private UserRepository userRepository;

    @BeforeEach
    public void setup(){
        User user = Mockito.mock(User.class);
        when(userRepository.findById(anyInt())).thenReturn(Optional.of(user));
    }

    @Test
    @WithMockUser(username="admin",authorities={"ADMIN"})
    public void getAllUserFromService() throws Exception {
        this.mockMvc.perform(get("/user/list")).andDo(print()).andExpect(status().isOk());

    }
    @Test
    @WithMockUser
    public void accessDeniedUser() throws Exception {
        this.mockMvc.perform(get("/user/list")).andDo(print()).andExpect(status().isForbidden());
    }


    @Test
    @WithMockUser(username="admin",authorities={"ADMIN"})
    public void addUserFromService() throws Exception {
        this.mockMvc.perform(get("/user/add")).andDo(print()).andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    public void accessDeniedAddingUser() throws Exception {
        this.mockMvc.perform(get("/user/add")).andDo(print()).andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(username="admin",authorities={"ADMIN"})
    public void userUpdate() throws Exception {
        mockMvc.perform(get("/user/update/1")
                        .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(view().name("user/update"));
    }

    @Test
    @WithMockUser(username="admin",authorities={"ADMIN"})
    public void userPostUpdate() throws Exception {
        mockMvc.perform(post("/user/update/1")
                        .with(csrf()))
                .andExpect(status().isOk()).andExpect(view().name("user/update"));
    }
    @Test
    @WithMockUser(username="admin",authorities={"ADMIN"})
    public void userValidateTest() throws Exception {
        mockMvc.perform(post("/user/validate")
                        .with(csrf()))
                .andExpect(status().isOk()).andExpect(view().name("user/add"));
    }
    @Test
    @WithMockUser(username="admin",authorities={"ADMIN"})
    public void userDelete() throws Exception {
        mockMvc.perform(get("/user/delete/1")
                        .with(csrf()))
                .andExpect(status().isFound()).andExpect(redirectedUrl("/user/list"));
    }
}
