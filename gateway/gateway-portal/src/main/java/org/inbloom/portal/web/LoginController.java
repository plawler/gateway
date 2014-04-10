package org.inbloom.portal.web;

import org.inbloom.portal.forms.Login;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author benjaminmorgan
 *         Date: 4/8/14
 */
@Controller
@RequestMapping("/login")
public class LoginController {

    private final Environment env;

    @Autowired
    public LoginController(Environment environment) {
        this.env = environment;
    }

    public String getApiHost() {
        return env.getProperty("apiHost", "http://localhost:9001");
    }

    @RequestMapping(method= RequestMethod.GET)
    public String get(Model model) {

        return "login";
    }


    @RequestMapping(method=RequestMethod.POST)
    public String post(Model model, @ModelAttribute Login loginInfo) {

        //TODO: actually log in

        model.addAttribute("errorMessage", "Login functionality has not yet been implemented :P");
        return "login";

    }

}
