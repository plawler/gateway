package com.sample.gateway.rest;

import com.sample.gateway.core.domain.Operator;
import com.sample.gateway.core.event.*;
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

import javax.validation.Valid;

/**
 * Created by lloydengebretsen on 2/20/14.
 */
@Controller
@RequestMapping(value = "/operators")
public class OperatorController {

    @Autowired
    private OperatorService operatorService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Operator> register(@Valid @RequestBody Operator operator, UriComponentsBuilder componentsBuilder) {
        RegisteredOperatorEvent registeredEvent = operatorService.registerOperator(new RegisterOperatorEvent(operator));

        Operator newOperator = registeredEvent.getData();

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(componentsBuilder.path("/operators/{id}")
                .buildAndExpand(registeredEvent.getOperatorId().toString()).toUri());

        return new ResponseEntity<Operator>(newOperator, headers, HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.GET, value="/{id}")
    public ResponseEntity<Operator> retrieve(@PathVariable Long id) {
        RetrievedOperatorEvent retrievedEvent = operatorService.retrieveOperator(new RetrieveOperatorEvent(id));
        Operator operator = retrievedEvent.getData();
        return new ResponseEntity<Operator>(operator, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT, value="/{id}")
    public ResponseEntity modify(@RequestBody Operator operator, @PathVariable Long id) {

        ModifiedOperatorEvent modifiedEvent = operatorService.modifyOperator(new ModifyOperatorEvent(id, operator));

        // should we reject the request??
        if (!modifiedEvent.getId().equals(id)) {
            return new ResponseEntity<Operator>(operator, HttpStatus.CONFLICT);
        } else if (modifiedEvent.isUpdateSuccessful()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<Operator>(operator, HttpStatus.NOT_FOUND);
        }
    }
}
