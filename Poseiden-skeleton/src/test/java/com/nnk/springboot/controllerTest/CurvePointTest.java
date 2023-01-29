package com.nnk.springboot.controllerTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nnk.springboot.domain.CurvePoint;
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
import org.springframework.test.web.servlet.ResultMatcher;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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

    private final ObjectMapper mapper = new ObjectMapper();

    //    @WithMockUser//Annotation pour mocker l'utilisateur connecté avec un rôle
    @Test
    public void getAllCurvePointFromService() throws Exception {
//        CurvePoint curvePoint = Mockito.mock(CurvePoint.class);
//        curvePointRepository.save(curvePoint);
        this.mockMvc.perform(get("/curvePoint/list")).andDo(print()).andExpect(status().isOk());

    }

    @Test
    public void addCurvePointFromService() throws Exception {
        this.mockMvc.perform(get("/curvePoint/add")).andDo(print()).andExpect(status().isOk());
    }
//    @Test
//    public void validateAddingCurvePointFromService() throws Exception {
//        Principal principal = new Principal() {
//            @Override
//            public String getName() {
//                return null;
//            }
//
//    };
//
//        mockMvc.perform(post("/curvePoint/validate").principal(principal)).andExpect(status().isOk());
//    }

    @Test
    public void testCreateUserSuccess() throws Exception {
        // error 403
        CurvePoint curvepoint = new CurvePoint();
        curvepoint.setValue(10d);
        curvepoint.setTerm(15d);
        curvepoint.setId(1);
        curvepoint.setCurveId(12);
        curvepoint.setAsOfDate(Timestamp.from(Instant.now()));
        curvepoint.setCreationDate(Timestamp.from(Instant.now().minusMillis(1000)));

//        given(curveService.save(any(CurvePoint.class))).willReturn(curvePointRepository.save(curvepoint));

        String res = mockMvc.perform(post("/curvePoint/validate")
                        .content(mapper.writeValueAsString(curvepoint))
                        .contentType(APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        CurvePoint responseCurvePoint = mapper.readValue(res, CurvePoint.class);
        assertEquals(curvepoint, responseCurvePoint);
    }


    @Test
    public void updatedCurvePointFromService() throws Exception {
        // error 404 //400
//        this.mockMvc.perform(get("/curvePoint/update")).andDo(print()).andExpect(status().isOk());

        CurvePoint curvepoint = new CurvePoint();
        curvepoint.setValue(10d);
        curvepoint.setTerm(15d);
        curvepoint.setId(1);
        curvepoint.setCurveId(12);
        curvepoint.setAsOfDate(Timestamp.from(Instant.now()));
        curvepoint.setCreationDate(Timestamp.from(Instant.now().minusMillis(1000)));


        when(curvePointRepository.findById(curvepoint.getId())).thenReturn(Optional.of(curvepoint));
        mockMvc.perform(post("/curvePoint/update/1")).andExpect(status().isOk())
                .andExpect((ResultMatcher) jsonPath("$.id").value(1))
                .andExpect((ResultMatcher) jsonPath("$.value").value(curvepoint.getValue()))
                .andExpect((ResultMatcher) jsonPath("$.term").value(curvepoint.getTerm()))
                .andDo(print());
    }



    @Test
    public void updatedPostCurvePointFromService() throws Exception {
        // error 403
        CurvePoint curvepoint = new CurvePoint();
        curvepoint.setValue(10d);
        curvepoint.setTerm(15d);
        curvepoint.setId(1);
        curvepoint.setCurveId(12);
        curvepoint.setAsOfDate(Timestamp.from(Instant.now()));
        curvepoint.setCreationDate(Timestamp.from(Instant.now().minusMillis(1000)));
        this.mockMvc.perform(post("/curvePoint/update/1")).andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void updatedGetCurvePointFromService() throws Exception {
        // error 404
        CurvePoint curvepoint = new CurvePoint();
        curvepoint.setValue(10d);
        curvepoint.setTerm(15d);
        curvepoint.setId(1);
        curvepoint.setCurveId(12);
        curvepoint.setAsOfDate(Timestamp.from(Instant.now()));
        curvepoint.setCreationDate(Timestamp.from(Instant.now().minusMillis(1000)));
        this.mockMvc.perform(get("/curvePoint/update/1")).andDo(print()).andExpect(status().isOk());
    }
    @Test
    public void deleteCurvePointFromService() throws Exception {
        // error 404
        this.mockMvc.perform(get("/curvePoint/delete")).andDo(print()).andExpect(status().isOk());

    }
}
