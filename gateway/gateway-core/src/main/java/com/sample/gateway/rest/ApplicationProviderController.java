package com.sample.gateway.rest;

import com.sample.gateway.core.domain.ApplicationProvider;
import com.sample.gateway.core.event.RegisterApplicationProviderEvent;
import com.sample.gateway.core.event.RegisteredApplicationProviderEvent;
import com.sample.gateway.core.event.RetrieveApplicationProviderEvent;
import com.sample.gateway.core.event.RetrievedApplicationProviderEvent;
import com.sample.gateway.core.service.ApplicationProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * Created by lloydengebretsen on 2/18/14.
 */
@Controller
@RequestMapping(value = "/application-providers")
public class ApplicationProviderController {

    @Autowired
    private ApplicationProviderService applicationProviderService;


    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<ApplicationProvider> register(@RequestBody ApplicationProvider applicationProvider, UriComponentsBuilder componentsBuilder) {
        RegisteredApplicationProviderEvent registeredEvent = applicationProviderService.registerApplicationProvider(new RegisterApplicationProviderEvent(applicationProvider.details()));

        ApplicationProvider newAppProvider = ApplicationProvider.fromApplicationProviderData(registeredEvent.getData());

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(componentsBuilder.path("/application-providers/{id}")
                .buildAndExpand(registeredEvent.getData().getApplicationProviderId().toString()).toUri());

        return new ResponseEntity<ApplicationProvider>(newAppProvider, headers, HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.GET, value="/{id}")
    public ResponseEntity<ApplicationProvider> getApplicationProvider(@PathVariable Long id) {
        RetrievedApplicationProviderEvent retrievedEvent = applicationProviderService.retrieveApplicationProvider(new RetrieveApplicationProviderEvent(id));
        ApplicationProvider app = ApplicationProvider.fromApplicationProviderData(retrievedEvent.getData());

        return new ResponseEntity<ApplicationProvider>(app, HttpStatus.OK);
    }
}
