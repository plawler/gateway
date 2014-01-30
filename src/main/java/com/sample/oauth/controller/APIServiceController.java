package com.sample.oauth.controller;

import com.sample.oauth.model.Service;
import com.sample.oauth.repository.CentralAppRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created with IntelliJ IDEA.
 * User: paullawler
 * Date: 1/28/14
 * Time: 1:46 PM
 * To change this template use File | Settings | File Templates.
 */
@Controller
public class APIServiceController {

    @Autowired
    private CentralAppRegistry registry;

    @RequestMapping(value = "/dataservices/{realmId}", method = RequestMethod.GET)
    public @ResponseBody Service getApiEndpoints(@PathVariable String realmId) {
        Service service = registry.findDataServiceByRealm(realmId);
        return service;
    }
}
