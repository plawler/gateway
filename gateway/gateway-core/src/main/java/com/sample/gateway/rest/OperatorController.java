package com.sample.gateway.rest;

import com.sample.gateway.core.domain.Operator;
import com.sample.gateway.core.event.RegisterOperatorEvent;
import com.sample.gateway.core.event.RegisteredOperatorEvent;
import com.sample.gateway.core.service.OperatorService;
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
 * Created by lloydengebretsen on 2/20/14.
 */
@Controller
@RequestMapping(value = "/operators")
public class OperatorController {

    @Autowired
    private OperatorService operatorService;


    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Operator> register(@RequestBody Operator operator, UriComponentsBuilder componentsBuilder) {
        RegisteredOperatorEvent registeredEvent = operatorService.registerOperator(new RegisterOperatorEvent(operator));

        Operator newOperator = registeredEvent.getData();

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(componentsBuilder.path("/operators/{id}")
                .buildAndExpand(registeredEvent.getOperatorId().toString()).toUri());

        return new ResponseEntity<Operator>(newOperator, headers, HttpStatus.CREATED);
    }

    /*@RequestMapping(method = RequestMethod.GET, value="/{id}")
    public ResponseEntity<ApplicationProvider> getApplicationProvider(@PathVariable Long id) {
        RetrievedApplicationProviderEvent retrievedEvent = applicationProviderService.retrieveApplicationProvider(new RetrieveApplicationProviderEvent(id));
        ApplicationProvider app = ApplicationProvider.fromApplicationProviderData(retrievedEvent.getData());

        return new ResponseEntity<ApplicationProvider>(app, HttpStatus.OK);
    }*/
}
