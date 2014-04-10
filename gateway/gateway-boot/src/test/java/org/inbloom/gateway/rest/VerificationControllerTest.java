package org.inbloom.gateway.rest;

import org.inbloom.gateway.Gateway;
import org.inbloom.gateway.core.event.verification.RetrieveVerificationEvent;
import org.inbloom.gateway.core.event.verification.RetrievedVerificationEvent;
import org.inbloom.gateway.core.event.verification.ValidateAccountSetupEvent;
import org.inbloom.gateway.core.event.verification.ValidatedAccountSetupEvent;
import org.inbloom.gateway.core.service.VerificationService;
import org.inbloom.gateway.fixture.VerificationFixture;
import org.inbloom.gateway.rest.util.TestUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Date;

import static org.inbloom.gateway.fixture.VerificationFixture.*;

import static org.mockito.Matchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Created By: paullawler
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Gateway.class)
@WebAppConfiguration
public class VerificationControllerTest {

    private MockMvc mockMvc;

    @Mock
    private VerificationService verificationService;

    @InjectMocks
    private VerificationController controller;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .setMessageConverters(new MappingJackson2HttpMessageConverter())
                .setHandlerExceptionResolvers(TestUtil.createExceptionResolver())
                .build();
    }

    @Test
    public void shouldValidateAnAccount() throws Exception {
        Mockito.when(verificationService.validateAccountSetup(any(ValidateAccountSetupEvent.class)))
                .thenReturn(ValidatedAccountSetupEvent.success(VerificationFixture.validVerification(new Date())));

        this.mockMvc.perform(post("/verifications/validate")
                .content(TestUtil.stringify(accountValidation()))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void shouldRetrieveAVerificationForAToken() throws Exception {
        Mockito.when(verificationService.retrieveVerification(any(RetrieveVerificationEvent.class)))
                .thenReturn(RetrievedVerificationEvent.success(VerificationFixture.validVerification(new Date())));

        this.mockMvc.perform(get("/verifications/{token}", "3908092jfojief2309j029")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void shouldFailValidationIfTheVerificationIsNotValid() throws Exception {
        Mockito.when(verificationService.validateAccountSetup(any(ValidateAccountSetupEvent.class)))
                .thenReturn(ValidatedAccountSetupEvent.expired("The verification expired"));

        this.mockMvc.perform(post("/verifications/validate")
                .content(TestUtil.stringify(accountValidation()))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isForbidden());
    }

}
