package com.nnk.springboot.controllerTest;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.repositories.CurvePointRepository;
import com.nnk.springboot.service.CurveService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

    @Test
    public void getAllBidListFromService() throws Exception {
        CurvePoint curvePoint = Mockito.mock(CurvePoint.class);
        List<CurvePoint> curvePointList = curvePointRepository.findAll();
        when(curveService.findAll()).thenReturn(curvePointList);
        this.mockMvc.perform(get("/curvePoint/list")).andDo(print()).andExpect(status().isOk());
//                .andExpect(content().string(containsString("Hello, Mock")));


    }
}
