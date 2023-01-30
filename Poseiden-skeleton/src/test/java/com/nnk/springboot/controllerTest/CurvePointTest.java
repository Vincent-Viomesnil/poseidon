package com.nnk.springboot.controllerTest;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.repositories.CurvePointRepository;
import com.nnk.springboot.service.CurveService;
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
public class CurvePointTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CurveService curveService;

    @MockBean
    private CurvePointRepository curvePointRepository;

    @BeforeEach
    public void setup(){
        CurvePoint curvePoint = Mockito.mock(CurvePoint.class);
        when(curveService.findById(anyInt())).thenReturn(Optional.of(curvePoint));
    }

    @Test
    @WithMockUser
    public void getAllCurvePointFromService() throws Exception {
        this.mockMvc.perform(get("/curvePoint/list")).andDo(print()).andExpect(status().isOk());

    }

    @Test
    @WithMockUser
    public void addCurvePointFromService() throws Exception {
        this.mockMvc.perform(get("/curvePoint/add")).andDo(print()).andExpect(status().isOk());
    }
    @Test
    @WithMockUser
    public void curvePointUpdate() throws Exception {
                mockMvc.perform(get("/curvePoint/update/1")
                        .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(view().name("curvePoint/update"));
    }
    @Test
    @WithMockUser
    public void curvePointPostUpdateRedirect() throws Exception {
        mockMvc.perform(post("/curvePoint/update/1")
                        .with(csrf()))
                .andExpect(status().isFound()).andExpect(redirectedUrl("/curvePoint/list"));

//                .andExpect(view().name());
        //isFound trouve la redirection vers le endpoint utilisé, Plus ciblé code de Redirection
    }

    @Test
    @WithMockUser
    public void curvePointPostUpdate() throws Exception {
        mockMvc.perform(post("/curvePoint/update/1")
                        .with(csrf()))
                .andExpect(status().isFound()).andExpect(view().name("redirect:/curvePoint/list"));
    }
    @Test
    @WithMockUser
    public void curvePointValidateTest() throws Exception {
        mockMvc.perform(post("/curvePoint/validate")
                        .with(csrf()))
                .andExpect(status().isFound()).andExpect(view().name("redirect:/curvePoint/list"));

    }
    @Test
    @WithMockUser
    public void curvePointDelete() throws Exception {
        mockMvc.perform(get("/curvePoint/delete/1")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/curvePoint/list"));
    }


//    @Test
//    @WithMockUser
//    public void updatedPostCurvePointFromService() throws Exception {
//        // error 403
//        CurvePoint curvepoint = Mockito.mock(CurvePoint.class);
//        when(curvePointRepository.findById(curvepoint.getId())).thenReturn(Optional.of(curvepoint));
//
//        this.mockMvc.perform(post("/curvePoint/update/21").param("curveId", "11")
//                .param("term", "1.1")
//                .param("value", "5.5")
//                .contentType(MediaType.APPLICATION_FORM_URLENCODED))
//                .andDo(print()).andExpect(status().is3xxRedirection());
//    }

}
