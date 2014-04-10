package org.inbloom.gateway.rest;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiError;
import com.wordnik.swagger.annotations.ApiErrors;
import com.wordnik.swagger.annotations.ApiOperation;

import org.inbloom.gateway.common.domain.ApplicationProvider;
import org.inbloom.gateway.core.event.provider.*;
import org.inbloom.gateway.core.service.ApplicationProviderService;
import org.inbloom.gateway.common.status.ApplicationProviderStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
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
 * created by benjaminmorgan on 3/24/14
 */
@Controller
@EnableAutoConfiguration
@Api(value="", description="Application Provider endpoint CRU functionality.")
public class ApplicationProviderController {


    @Autowired
    ApplicationProviderService appProviderService;

    @RequestMapping(value = "/applicationProviders", method = RequestMethod.POST)
    @ApiOperation(value = "Register a new application provider.")
    public ResponseEntity<ApplicationProvider> register(@Valid @RequestBody ApplicationProvider appProvider, UriComponentsBuilder componentsBuilder)
    {
        RegisteredApplicationProviderEvent createdEvent = appProviderService.registerApplicationProvider(new RegisterApplicationProviderEvent(appProvider));

        switch((ApplicationProviderStatus)createdEvent.status()) {
            case SUCCESS:
                ApplicationProvider newAppProvider = createdEvent.getApplicationProvider();

                HttpHeaders headers = new HttpHeaders();
                headers.setLocation(componentsBuilder.path("/applicationProviders/{id}")
                        .buildAndExpand(newAppProvider.getApplicationProviderId()).toUri());

                return new ResponseEntity<ApplicationProvider>(newAppProvider, headers, HttpStatus.CREATED);


            default:
                return new ResponseEntity(new StatusResponse(createdEvent.status()), HttpStatus.BAD_REQUEST);
        }
    }


    @RequestMapping(method = RequestMethod.GET, value="/applicationProviders/{id}")
    @ApiOperation(value = "Retrieve an Application Provider by id.", notes = "Look up an Application Provider based on applicationProviderId value.")
    @ApiErrors(value = { @ApiError(code = 404, reason = "Application Provider not found") })
    public ResponseEntity<ApplicationProvider> retrieve(@PathVariable Long id) {

        RetrievedApplicationProviderEvent retrievedEvent = appProviderService.retrieveApplicationProvider(new RetrieveApplicationProviderEvent(id));

        ApplicationProvider appProvider = retrievedEvent.getData();

        if(appProvider != null) {
            return new ResponseEntity<ApplicationProvider>(appProvider, HttpStatus.OK);
        }
        else {
            return new ResponseEntity(new StatusResponse(retrievedEvent.status()), HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(method = RequestMethod.POST, value="/applicationProviders/{id}")
    @ApiOperation(value = "Modify an Application Provider by id.", notes = "Update an Application Provider based on applicationProviderId value.")
    @ApiErrors(value = { @ApiError(code = 404, reason = "Application Provider not found for given id"),
            @ApiError(code = 400, reason = "Id can not be null"),
            @ApiError(code = 409, reason = "Returned id did not match passed in id")})
    public ResponseEntity modify(@RequestBody ApplicationProvider appProvider, @PathVariable Long id) {

        if(id == null) {
            //fail fast if this came in without a valid Id
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        } else if (appProvider.getApplicationProviderId() == null || !id.equals(appProvider.getApplicationProviderId())) {
            //fail fast if the id from endpoint does not match the one passed in the request body
            return new ResponseEntity<ApplicationProvider>(appProvider, HttpStatus.CONFLICT);
        }

        ModifiedApplicationProviderEvent modifiedEvent = appProviderService.modifyApplicationProvider(new ModifyApplicationProviderEvent(id, appProvider));

        switch ((ApplicationProviderStatus)modifiedEvent.status())
        {
            case SUCCESS:
                return new ResponseEntity(HttpStatus.NO_CONTENT);
            case NOT_FOUND:
                return new ResponseEntity(new StatusResponse(modifiedEvent.status()), HttpStatus.NOT_FOUND);
            default:
                return new ResponseEntity(new StatusResponse(modifiedEvent.status()), HttpStatus.INTERNAL_SERVER_ERROR);//throw 500 error if we don't know why this failed
        }

    }
}
