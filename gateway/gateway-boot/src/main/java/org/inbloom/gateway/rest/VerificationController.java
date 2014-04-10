package org.inbloom.gateway.rest;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import org.inbloom.gateway.common.status.VerificationStatus;
import org.inbloom.gateway.core.domain.AccountValidation;
import org.inbloom.gateway.core.domain.Verification;
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
        return buildResponse((VerificationStatus)validated.status(), validated.getData());
    }

    @RequestMapping(value = "/verifications/{token}", method = RequestMethod.GET)
    public ResponseEntity<Verification> retrieve(@PathVariable String token) {
        RetrievedVerificationEvent retrieved = verificationService.retrieveVerification(new RetrieveVerificationEvent(token));
        return buildResponse((VerificationStatus)retrieved.status(), retrieved.getData());
    }

    private ResponseEntity buildResponse(VerificationStatus status, Verification verification) {
        switch(status) {
            case SUCCESS: return new ResponseEntity<Verification>(verification, HttpStatus.OK);
            case NOT_FOUND: return new ResponseEntity(status, HttpStatus.NOT_FOUND);
            case EXPIRED: ;//fall through to next FORBIDDEN response
            case REDEEMED: return new ResponseEntity(status, HttpStatus.FORBIDDEN);
            default: return new ResponseEntity(status, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
