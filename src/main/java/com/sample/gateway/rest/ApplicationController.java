package com.sample.gateway.rest;

import com.sample.gateway.core.domain.Application;
import com.sample.gateway.core.event.ApplicationData;
import com.sample.gateway.core.event.RegisterApplicationEvent;
import com.sample.gateway.core.event.RegisteredApplicationEvent;
import com.sample.gateway.core.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.UriComponentsBuilder;

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

}
