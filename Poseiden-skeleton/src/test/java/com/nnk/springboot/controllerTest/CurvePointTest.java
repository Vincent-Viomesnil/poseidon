package com.nnk.springboot.controllerTest;

import com.nnk.springboot.repositories.CurvePointRepository;
import com.nnk.springboot.service.CurveService;
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
@WithMockUser
public class CurvePointTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CurveService curveService;

    @MockBean
    private CurvePointRepository curvePointRepository;

    //    @WithMockUser//Annotation pour mocker l'utilisateur connecté avec un rôle
    @Test
    public void getAllBidListFromService() throws Exception {
//        CurvePoint curvePoint = Mockito.mock(CurvePoint.class);
//        curvePointRepository.save(curvePoint);
        this.mockMvc.perform(get("/curvePoint/list")).andDo(print()).andExpect(status().isOk());


    }
}
