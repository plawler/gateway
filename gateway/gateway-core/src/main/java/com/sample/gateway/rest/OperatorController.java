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
        if (operator.getOperatorId() != null && !id.equals(operator.getOperatorId())) {
            //fail fast if the id from endpoint does not match the one passed in the request body
            // (i.e. can't update the operatorId)
            return new ResponseEntity<Operator>(operator, HttpStatus.CONFLICT);
        }

        Event modifiedEvent = operatorService.modifyOperator(new ModifyOperatorEvent(id, operator));

        //request failed, check reason and return correct error code
        if (!modifiedEvent.status().equals(Event.Status.SUCCESS)) {
            if(modifiedEvent.status().equals(Event.Status.NOT_FOUND)) {
                return new ResponseEntity(HttpStatus.NOT_FOUND);
            }
            else {
                return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);//throw 500 error if we don't know why this failed
            }
        }

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
