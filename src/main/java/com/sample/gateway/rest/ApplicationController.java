package com.sample.gateway.rest;

import com.sample.gateway.core.domain.Application;
import com.sample.gateway.core.event.*;
import com.sample.gateway.core.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: paullawler
 * Date: 2/15/14
 * Time: 5:18 PM
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping(value = "/applications")
public class ApplicationController {

    @Autowired
    private ApplicationService applicationService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Application> register(@RequestBody Application application, UriComponentsBuilder componentsBuilder) {
        RegisteredApplicationEvent registeredEvent = applicationService.registerNewApplication(new RegisterApplicationEvent(application.details()));

        Application newApp = Application.fromApplicationData(registeredEvent.getData());

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(componentsBuilder.path("/applications/{id}")
                    .buildAndExpand(registeredEvent.getApplicationId().toString()).toUri());

        return new ResponseEntity<Application>(newApp, headers, HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.GET, value="/{id}")
    public ResponseEntity<Application> getApplication(@PathVariable Long id) {
        RetrievedApplicationEvent retrievedEvent = applicationService.retrieveApplication(new RetrieveApplicationEvent(id));
        Application app = Application.fromApplicationData(retrievedEvent.getData());

        return new ResponseEntity<Application>(app, HttpStatus.OK);
    }

}
