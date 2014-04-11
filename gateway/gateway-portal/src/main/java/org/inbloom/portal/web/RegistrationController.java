package org.inbloom.portal.web;

import org.inbloom.portal.forms.Registration;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created By: paullawler
 * Shamelessly Modified By: verlinhenderson
 */
@Controller
@RequestMapping("/registration")
public class RegistrationController {

    @RequestMapping(method=RequestMethod.GET)
    public String get(Model model) {
        model.addAttribute("message", "Developer Account Registration");
        return "registration";
    }

    @RequestMapping(method=RequestMethod.POST)
    public String post(Model model, @RequestParam(value="firstname") String firstname, @RequestParam(value="lastname") String lastname, @RequestParam(value="company", defaultValue="") String company, @RequestParam(value="email") String email) {
        model.addAttribute("message", "Validate Your Email");
        Registration registration = new Registration();
        
        registration.setFirstname(firstname);
        registration.setLastname(lastname);
        registration.setCompany(company);
        registration.setEmail(email);
        
        //submit the registration to the API...
        
        return "registration2";
    }

}
