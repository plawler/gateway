package org.inbloom.gateway.rest;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import org.inbloom.gateway.core.domain.Verification;
import org.inbloom.gateway.core.event.VerifiedEmailEvent;
import org.inbloom.gateway.core.event.VerifyEmailEvent;
import org.inbloom.gateway.core.service.VerificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.UriComponentsBuilder;

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

    @RequestMapping(value = "/verifications", method = RequestMethod.POST)
    @ApiOperation(value = "Verify User's email and set their password")
    public ResponseEntity<Verification> register(@Valid @RequestBody Verification verification, UriComponentsBuilder componentsBuilder) {

        VerifiedEmailEvent modifiedEvent = verificationService.verifyEmail(new VerifyEmailEvent());


        return new ResponseEntity<Verification>(HttpStatus.OK);
    }
}
