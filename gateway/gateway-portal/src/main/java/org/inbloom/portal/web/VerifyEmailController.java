package org.inbloom.portal.web;

import org.inbloom.portal.forms.SignupCompletion;
import org.inbloom.portal.response.Verification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

/**
 * @author benjaminmorgan
 *         Date: 4/1/14
 */
@Controller
@RequestMapping("/email_validation")
@PropertySource("classpath:application.properties")
public class VerifyEmailController {

    private final Environment env;

    @Autowired
    public VerifyEmailController(Environment environment)
    {
        this.env = environment;
    }

    public String getApiHost()
    {
        return env.getProperty("apiHost", "http://localhost:9001");
    }

    @RequestMapping(method=RequestMethod.GET)
    public String get(Model model, @RequestParam("token") String token) {

        //check that token is valid
        RestTemplate getToken = new RestTemplate();
        ResponseEntity response = getToken.getForEntity(getApiHost() + "/gateway/api/verifications/{token}", Object.class, token);

        if(response.getStatusCode().value()/100 == 2) {
            model.addAttribute("validationToken", token);
            return "setPassword";
        } else {
            model.addAttribute("message", "That token is not valid!");
            return "error";
        }
    }


    @RequestMapping(method=RequestMethod.POST)
    public String post(Model model, @ModelAttribute SignupCompletion passwordAndToken) {

        RestTemplate rest = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<SignupCompletion> entity = new HttpEntity<SignupCompletion>(passwordAndToken, headers);

        try {

            Verification response = rest.postForObject(getApiHost() + "/gateway/api/verifications/{token}", entity, Verification.class, passwordAndToken.getValidationToken());

            if(response.getVerified()!= null && response.getVerified()) {
                model.addAttribute("successMessage", "Congratulations! You have succesfully validated your email and created a password");
                return "login";
            } else {
                model.addAttribute("errorMessage", "Danger danger!!!");
                model.addAttribute("validationToken", passwordAndToken.getValidationToken());
                return "setPassword";
            }

        } catch(Exception e) {

            e.printStackTrace();
            return "error";
        }

    }
}
