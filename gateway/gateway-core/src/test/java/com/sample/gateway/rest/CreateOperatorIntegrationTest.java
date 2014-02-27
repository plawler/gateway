package com.sample.gateway.rest;

import com.sample.gateway.core.event.ModifyOperatorEvent;
import com.sample.gateway.core.event.RegisterOperatorEvent;
import com.sample.gateway.core.event.RetrieveOperatorEvent;
import com.sample.gateway.core.service.OperatorService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.io.IOException;

import static com.sample.gateway.rest.fixture.DataFixtures.applicationJson;
import static com.sample.gateway.rest.fixture.DataFixtures.operatorJson;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.Mockito.*;

import static com.sample.gateway.rest.fixture.OperatorEventFixtures.*;

/**
 * Created by lloydengebretsen on 2/26/14.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:applicationContext.xml"})
public class CreateOperatorIntegrationTest {

    private MockMvc mockMvc;

    @InjectMocks
    private OperatorController controller;

    @Mock
    private OperatorService operatorService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .setMessageConverters(new MappingJackson2HttpMessageConverter()).build();
    }

    @Test
    public void shouldRegisterAnOperator() throws Exception {
        when(operatorService.registerOperator(any(RegisterOperatorEvent.class))).thenReturn(operatorRegistered(1L));

        this.mockMvc.perform(post("/operators")
                .content(operatorJson())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(redirectedUrl("http://localhost/operators/1"));
    }

    @Test
    public void shouldRetrieveAnOperator() throws Exception {
        Long operatorId = new Long(1L);
        when(operatorService.retrieveOperator(any(RetrieveOperatorEvent.class))).thenReturn(operatorRetrieved(operatorId));

        this.mockMvc.perform(get("/operators/{id}", operatorId.toString())
                .content(operatorJson())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void shouldModifyAnOperator() throws Exception {
        Long operatorId = new Long(1L);
        when(operatorService.modifyOperator(any(ModifyOperatorEvent.class))).thenReturn(operatorModified(1L));

        this.mockMvc.perform(put("/operators/{id}", operatorId.toString())
                .content(operatorJson())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNoContent());
    }
}
