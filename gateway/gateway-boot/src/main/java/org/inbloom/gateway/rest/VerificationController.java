package org.inbloom.gateway.rest;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import org.inbloom.gateway.core.domain.AccountValidation;
import org.inbloom.gateway.core.domain.Verification;
import org.inbloom.gateway.core.event.*;
import org.inbloom.gateway.core.service.VerificationService;
import org.inbloom.gateway.rest.validation.ValidationException;
import org.inbloom.gateway.rest.validation.VerificationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: benjaminmorgan
 * Date: 3/25/14
 * Time: 11:44 AM
 * To change this template use File | Settings | File Templates.
 */
@Controller
@EnableAutoConfiguration
@Api(value="", description="Verification Endpoint ")
public class VerificationController {

    @Autowired
    VerificationService verificationService;

    @RequestMapping(value = "/verifications/{token}", method = RequestMethod.POST)
    @ApiOperation(value = "Validates User's email and sets their password")
    public ResponseEntity<Verification> validate(@Valid @RequestBody AccountValidation validation, @PathVariable String token) {
        validation.setValidationToken(token);
        ValidatedAccountSetupEvent validated = verificationService.validateAccountSetup(new ValidateAccountSetupEvent(validation));
        switch(validated.status()) {
            case SUCCESS: return new ResponseEntity<Verification>(validated.getData(), HttpStatus.OK);
            case FAILED: throw new VerificationException(validated.message());
            default: return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/verifications/{token}", method = RequestMethod.GET)
    public ResponseEntity<Verification> retrieve(@PathVariable String token) {
        RetrievedVerificationEvent retrieved = verificationService.retrieveVerification(new RetrieveVerificationEvent(token));
        switch (retrieved.status()) {
            case SUCCESS: return new ResponseEntity<Verification>(retrieved.getData(), HttpStatus.OK);
            case NOT_FOUND: return new ResponseEntity(HttpStatus.NOT_FOUND);
            default: return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
