package org.inbloom.portal.web;

import org.inbloom.portal.forms.SignupCompletion;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author benjaminmorgan
 *         Date: 4/1/14
 */
@Controller
@RequestMapping("/email_validation")
public class VerifyEmailController {

    @RequestMapping(method=RequestMethod.GET)
    public String get(Model model, @RequestParam("token") String token) {

        model.addAttribute("token", token);

        //TODO: check that token is valid
        if(false) {
            model.addAttribute("message", "That token is not valid!");
            return "error";
        }

        return "setPassword";
    }


    @RequestMapping(method=RequestMethod.POST)
    public String post(Model model, @ModelAttribute SignupCompletion passwordAndToken) {
        System.out.println(passwordAndToken.getPassword());
        System.out.println(passwordAndToken.getToken());

        //TODO: post password and token to api
        //on fail, reload page with error msg
        if(false) {
            model.addAttribute("errorMessage", "Danger danger!!!");
            model.addAttribute("token", passwordAndToken.getToken());
            return "setPassword";
        }

        return "hello";
    }
}
