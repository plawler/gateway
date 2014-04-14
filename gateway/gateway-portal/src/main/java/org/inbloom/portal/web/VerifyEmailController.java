package org.inbloom.portal.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.inbloom.gateway.common.domain.AccountValidation;
import org.inbloom.gateway.common.domain.Verification;
import org.inbloom.gateway.common.status.GatewayStatus;
import org.inbloom.portal.util.RestTemplateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

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
    public VerifyEmailController(Environment environment) {
        this.env = environment;
    }

    public String getApiHost() {
        return env.getProperty("apiHost", "http://localhost:9001");
    }

    @RequestMapping(method=RequestMethod.GET)
    public String get(Model model, @RequestParam("token") String token) {

        //check that supplied token is valid before redirecting to "create password" page

        RestTemplate rest = RestTemplateUtil.noErrorHandlers();
        ObjectMapper mapper = RestTemplateUtil.ignoreMissingFieldsMapper();

        ResponseEntity<String> response =  rest.getForEntity(getApiHost() + "/gateway/api/verifications/{token}", String.class, token);

        try {
            if(response.getStatusCode().is2xxSuccessful()) {
                Verification verification = mapper.readValue(response.getBody(), Verification.class);

                if(verification.isVerified()) {
                    //email has already been validated
                    model.addAttribute("errorMessage", "Your email has already been validated. Please sign in");
                    return "login";
                }

                if(verification.isExpired()) {
                    //token has expired
                    model.addAttribute("message", "Your email validation has expired. TODO: redirect to \"resend validation email\" page");
                    return "error";
                }

                //redirect to create password page
                model.addAttribute("validationToken", token);
                return "setPassword";
            }
            else {
                GatewayStatus status = mapper.readValue(response.getBody(), GatewayStatus.class);

                switch(status.getStatus()) {
                    case NOT_FOUND:
                        model.addAttribute("message", "Couldn't find the token. Make sure you follow the link sent in the verification email, or try signing up for an account");
                        return "error";
                    case ERROR:
                        model.addAttribute("message", status.getMessage());
                        return "error";
                    case EXPIRED:
                        model.addAttribute("message", "Your email validation has expired. TODO: redirect to \"resend validation email\" page");
                        return "error";
                    case REDEEMED:
                        model.addAttribute("errorMessage", "Your email has already been validated. Please sign in");
                        return "login";
                    default:
                        model.addAttribute("message", "Unknown response from api");
                        return "error";
                }
            }
        } catch(IOException e) {
            model.addAttribute("message", e.getMessage());
            return "error";
        }
    }


    @RequestMapping(method=RequestMethod.POST)
    public String post(Model model, @ModelAttribute AccountValidation validation) {

        RestTemplate rest = RestTemplateUtil.noErrorHandlers();
        ObjectMapper mapper = RestTemplateUtil.ignoreMissingFieldsMapper();

        String token = validation.getValidationToken();

        ResponseEntity<String> response =  rest.postForEntity(getApiHost() + "/gateway/api/verifications/{token}", validation, String.class, token);

        try {

            if(response.getStatusCode().is2xxSuccessful()) {
                Verification verification = mapper.readValue(response.getBody(), Verification.class);
                model.addAttribute("successMessage", "Congratulations! You have succesfully validated your email and created a password");
                return "login";
            } else {
                GatewayStatus status = mapper.readValue(response.getBody(), GatewayStatus.class);

                switch(status.getStatus()) {
                    case SUCCESS:
                        model.addAttribute("successMessage", "Congratulations! You have succesfully validated your email and created a password");
                        return "login";
                    default:
                        model.addAttribute("errorMessage", status.getMessage());
                        model.addAttribute("validationToken", token);
                        return "setPassword";

                }
            }
        } catch(IOException e) {
            model.addAttribute("message", e.getMessage());
            return "error";
        }
    }

}
