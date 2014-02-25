package com.sample.gateway.rest;

import com.sample.gateway.core.event.RegisterApplicationEvent;
import com.sample.gateway.core.event.RetrieveApplicationEvent;
import com.sample.gateway.core.service.ApplicationService;
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

import static com.sample.gateway.rest.fixture.ApplicationEventFixtures.*;
import static com.sample.gateway.rest.fixture.DataFixtures.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.Mockito.*;

/**
 * Created with IntelliJ IDEA.
 * User: paullawler
 * Date: 2/16/14
 * Time: 5:31 PM
 * To change this template use File | Settings | File Templates.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:applicationContext.xml"})
public class CreateApplicationIntegrationTest {

    private MockMvc mockMvc;

    @InjectMocks
    private ApplicationController controller;

    @Mock
    ApplicationService applicationService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .setMessageConverters(new MappingJackson2HttpMessageConverter()).build();
    }

    @Test
    public void shouldRegisterAnApplication() throws Exception {
        when(applicationService.registerNewApplication(any(RegisterApplicationEvent.class)))
                .thenReturn(applicationRegistered(1L));

        this.mockMvc.perform(
                post("/applications")
                    .content(applicationJson())
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(redirectedUrl("http://localhost/applications/1"));
    }

    @Test
    public void shouldRetrieveAnApplication() throws Exception {
        Long applicationId = 1L;

        when(applicationService.retrieveApplication(any(RetrieveApplicationEvent.class)))
                .thenReturn(applicationRetrieved(applicationId));

        this.mockMvc.perform(
                get("/applications/{id}", applicationId.toString())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldNotModifyAnApplicationThatIsNotFound() {

    }

}
