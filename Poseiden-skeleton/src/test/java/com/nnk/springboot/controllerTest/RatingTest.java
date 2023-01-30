package com.nnk.springboot.controllerTest;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.repositories.RatingRepository;
import com.nnk.springboot.service.RatingService;
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
public class RatingTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RatingService ratingService;

    @MockBean
    private RatingRepository ratingRepository;

    @Test
    @WithMockUser
    public void getAllRatingFromService() throws Exception {
        this.mockMvc.perform(get("/curvePoint/list")).andDo(print()).andExpect(status().isOk());

    }

    @Test
    @WithMockUser
    public void addRatingFromService() throws Exception {
        this.mockMvc.perform(get("/curvePoint/add")).andDo(print()).andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    public void ratingUpdate() throws Exception {
        Rating rating = Mockito.mock(Rating.class);

        when(ratingService.findById(anyInt())).thenReturn(Optional.of(rating));

        mockMvc.perform(get("/rating/update/1")
                        .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(view().name("rating/update"));
    }

}
