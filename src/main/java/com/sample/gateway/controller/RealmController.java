package com.sample.gateway.controller;

import com.sample.gateway.model.Realm;
import com.sample.gateway.persistence.repository.CentralAppRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: paullawler
 * Date: 1/28/14
 * Time: 2:17 PM
 * To change this template use File | Settings | File Templates.
 */
@Controller
public class RealmController {

    @Autowired
    private CentralAppRegistry registry;

    private static final Logger log = LoggerFactory.getLogger(RealmController.class);

    @RequestMapping(value = "/realms", method = RequestMethod.GET)
    public @ResponseBody List<Realm> getRealms() {
        return registry.getRealms();
    }

}
