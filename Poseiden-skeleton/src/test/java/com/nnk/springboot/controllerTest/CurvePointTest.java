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
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

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

    private final ObjectMapper mapper = new ObjectMapper();

    //    @WithMockUser//Annotation pour mocker l'utilisateur connecté avec un rôle
    @Test
    @WithMockUser
    public void getAllCurvePointFromService() throws Exception {
//        CurvePoint curvePoint = Mockito.mock(CurvePoint.class);
//        curvePointRepository.save(curvePoint);
        this.mockMvc.perform(get("/curvePoint/list")).andDo(print()).andExpect(status().isOk());

    }

    @Test
    @WithMockUser
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
    @WithMockUser
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
    @WithMockUser
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
    @WithMockUser
    public void updatedPostCurvePointFromService() throws Exception {
        // error 403
        CurvePoint curvepoint = new CurvePoint();
        curvepoint.setValue(10d);
        curvepoint.setTerm(15d);
        curvepoint.setId(21);
        curvepoint.setCurveId(12);
        curvepoint.setAsOfDate(Timestamp.from(Instant.now()));
        curvepoint.setCreationDate(Timestamp.from(Instant.now().minusMillis(1000)));
        when(curvePointRepository.findById(curvepoint.getId())).thenReturn(Optional.of(curvepoint));

//        CurvePoint curvepoint2 = new CurvePoint();
//        curvepoint.setValue(10d);
//        curvepoint.setTerm(15d);
//        curvepoint.setId(10);
//        curvepoint.setCurveId(12);
//        curvepoint.setAsOfDate(Timestamp.from(Instant.now()));
//        curvepoint.setCreationDate(Timestamp.from(Instant.now().minusMillis(1000)));

        this.mockMvc.perform(post("/curvePoint/update/21").param("curveId", "11")
                .param("term", "1.1")
                .param("value", "5.5")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andDo(print()).andExpect(status().is3xxRedirection());
    }

    @Test
    @WithMockUser
    public void curvePointUpdate() throws Exception {
        CurvePoint curvepoint = new CurvePoint();
        curvepoint.setValue(10d);
        curvepoint.setTerm(15d);
        curvepoint.setId(21);
        curvepoint.setCurveId(12);
        curvepoint.setAsOfDate(Timestamp.from(Instant.now()));
        curvepoint.setCreationDate(Timestamp.from(Instant.now().minusMillis(1000)));

        when(curveService.findById(anyInt())).thenReturn(Optional.of(curvepoint));

        mockMvc.perform(get("/curvePoint/update/1")
                        .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(view().name("curvePoint/update"));
    }

    @Test
    @WithMockUser
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
    @WithMockUser
    public void deleteCurvePointFromService() throws Exception {
        // error 404
        this.mockMvc.perform(get("/curvePoint/delete")).andDo(print()).andExpect(status().isOk());

    }
}
