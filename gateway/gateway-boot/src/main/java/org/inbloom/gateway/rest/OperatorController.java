package org.inbloom.gateway.rest;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiError;
import com.wordnik.swagger.annotations.ApiErrors;
import com.wordnik.swagger.annotations.ApiOperation;
import org.inbloom.gateway.core.domain.Operator;
import org.inbloom.gateway.core.event.*;
import org.inbloom.gateway.core.service.OperatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;

/**
 * Created by lloydengebretsen on 2/20/14.
 */
@Controller
@EnableAutoConfiguration
@Api(value="", description="Operator endpoint CRU functionality.")
public class OperatorController {

    @Autowired
    private OperatorService operatorService;

    @RequestMapping(value = "/operators", method = RequestMethod.POST)
    @ApiOperation(value = "Register a new operator.")
    public ResponseEntity<Operator> register(@Valid @RequestBody Operator operator, UriComponentsBuilder componentsBuilder) {
        RegisteredOperatorEvent registeredEvent = operatorService.registerOperator(new RegisterOperatorEvent(operator));

        Operator newOperator = registeredEvent.getData();

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(componentsBuilder.path("/operators/{id}")
                .buildAndExpand(registeredEvent.getOperatorId().toString()).toUri());

        return new ResponseEntity<Operator>(newOperator, headers, HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.GET, value="/operators/{id}")
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

    @RequestMapping(method = RequestMethod.PUT, value="/operators/{id}")
    @ApiOperation(value = "Modify an operator.")
    @ApiErrors(value = { @ApiError(code = 404, reason = "Operator not found for given id"),
            @ApiError(code = 400, reason = "Id can not be null"),
            @ApiError(code = 409, reason = "Returned id did not match passed in id")})
    public ResponseEntity modify(@RequestBody Operator operator, @PathVariable Long id) {

        if(id == null) {
            //fail fast if this came in without a valid Id
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        } else if (operator.getOperatorId() != null && !id.equals(operator.getOperatorId())) {
            //fail fast if the id from endpoint does not match the one passed in the request body
            // (i.e. can't update the operatorId)
            return new ResponseEntity<Operator>(operator, HttpStatus.CONFLICT);
        }

        ModifiedOperatorEvent modifiedEvent = operatorService.modifyOperator(new ModifyOperatorEvent(id, operator));

        switch (modifiedEvent.status())
        {
            case SUCCESS:
                return new ResponseEntity(HttpStatus.NO_CONTENT);
            case NOT_FOUND:
                return new ResponseEntity(HttpStatus.NOT_FOUND);
            default:
                return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);//throw 500 error if we don't know why this failed
        }
    }
}
