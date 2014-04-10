package org.inbloom.gateway.rest;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;

import org.inbloom.gateway.common.domain.AccountValidation;
import org.inbloom.gateway.common.domain.Verification;
import org.inbloom.gateway.common.status.VerificationStatus;
import org.inbloom.gateway.core.event.verification.RetrieveVerificationEvent;
import org.inbloom.gateway.core.event.verification.RetrievedVerificationEvent;
import org.inbloom.gateway.core.event.verification.ValidateAccountSetupEvent;
import org.inbloom.gateway.core.event.verification.ValidatedAccountSetupEvent;
import org.inbloom.gateway.core.service.VerificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

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
        switch((VerificationStatus)validated.status()) {
            case SUCCESS: return new ResponseEntity<Verification>(validated.getData(), HttpStatus.OK);
            case EXPIRED: return new ResponseEntity(new StatusResponse(validated.status()), HttpStatus.FORBIDDEN);
            case NOT_FOUND: return new ResponseEntity(new StatusResponse(validated.status()), HttpStatus.NOT_FOUND);
            case REDEEMED: return new ResponseEntity(new StatusResponse(validated.status()), HttpStatus.FORBIDDEN);
            default: return new ResponseEntity(new StatusResponse(validated.status()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/verifications/{token}", method = RequestMethod.GET)
    public ResponseEntity<Verification> retrieve(@PathVariable String token) {
        RetrievedVerificationEvent retrieved = verificationService.retrieveVerification(new RetrieveVerificationEvent(token));
        switch ((VerificationStatus)retrieved.status()) {
            case SUCCESS: return new ResponseEntity<Verification>(retrieved.getData(), HttpStatus.OK);
            case EXPIRED: return new ResponseEntity(new StatusResponse(retrieved.status()), HttpStatus.FORBIDDEN);
            case NOT_FOUND: return new ResponseEntity(new StatusResponse(retrieved.status()), HttpStatus.NOT_FOUND);
            case REDEEMED: return new ResponseEntity(new StatusResponse(retrieved.status()), HttpStatus.FORBIDDEN);
            default: return new ResponseEntity(new StatusResponse(retrieved.status()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
