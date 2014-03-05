package com.sample.gateway.rest;

import com.sample.gateway.core.domain.Operator;
import com.sample.gateway.core.event.*;
import com.sample.gateway.core.service.OperatorService;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiError;
import com.wordnik.swagger.annotations.ApiErrors;
import com.wordnik.swagger.annotations.ApiOperation;
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
@Api(value="", description="Operator endpoint CRU functionality.")
public class OperatorController {

    @Autowired
    private OperatorService operatorService;

    @RequestMapping(method = RequestMethod.POST)
    @ApiOperation(value = "Register a new operator.")
    public ResponseEntity<Operator> register(@Valid @RequestBody Operator operator, UriComponentsBuilder componentsBuilder) {
        RegisteredOperatorEvent registeredEvent = operatorService.registerOperator(new RegisterOperatorEvent(operator));

        Operator newOperator = registeredEvent.getData();

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(componentsBuilder.path("/operators/{id}")
                .buildAndExpand(registeredEvent.getOperatorId().toString()).toUri());

        return new ResponseEntity<Operator>(newOperator, headers, HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.GET, value="/{id}")
    @ApiOperation(value = "Retrieve an operator by id.", notes = "Look up an operator based on operatorId value.")
    @ApiErrors(value = { @ApiError(code = 404, reason = "Operator not found") })
    public ResponseEntity<Operator> retrieve(@PathVariable Long id) {
        RetrievedOperatorEvent retrievedEvent = operatorService.retrieveOperator(new RetrieveOperatorEvent(id));
        Operator operator = retrievedEvent.getData();

        if(operator != null) {
            return new ResponseEntity<Operator>(operator, HttpStatus.OK);
        }
        else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(method = RequestMethod.PUT, value="/{id}")
    @ApiOperation(value = "Modify an operator.")
    @ApiErrors(value = { @ApiError(code = 404, reason = "Operator not found for given id"),
            @ApiError(code = 400, reason = "Id can not be null"),
            @ApiError(code = 409, reason = "Returned id did not match passed in id")})
    public ResponseEntity modify(@RequestBody Operator operator, @PathVariable Long id) {

        if(id == null) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        ModifiedOperatorEvent modifiedEvent = operatorService.modifyOperator(new ModifyOperatorEvent(id, operator));

        if (modifiedEvent.getId() != null && !id.equals(modifiedEvent.getId())) {
            return new ResponseEntity<Operator>(operator, HttpStatus.CONFLICT);
        } else if (modifiedEvent.isUpdateSuccessful()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<Operator>(operator, HttpStatus.NOT_FOUND);
        }
    }
}
