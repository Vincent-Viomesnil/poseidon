package com.nnk.springboot.controllerTest;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.repositories.RuleNameRepository;
import com.nnk.springboot.service.RuleNameService;
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
public class RuleNameTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RuleNameService ruleNameService;

    @MockBean
    private RuleNameRepository ruleNameRepository;

    @Test
    @WithMockUser
    public void getAllRuleNameFromService() throws Exception {
        this.mockMvc.perform(get("/ruleName/list")).andDo(print()).andExpect(status().isOk());

    }

    @Test
    @WithMockUser
    public void addRuleNameFromService() throws Exception {
        this.mockMvc.perform(get("/ruleName/add")).andDo(print()).andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    public void ruleNameUpdate() throws Exception {
        RuleName ruleName = Mockito.mock(RuleName.class);

        when(ruleNameService.findById(anyInt())).thenReturn(Optional.of(ruleName));

        mockMvc.perform(get("/ruleName/update/1")
                        .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(view().name("ruleName/update"));
    }
}
