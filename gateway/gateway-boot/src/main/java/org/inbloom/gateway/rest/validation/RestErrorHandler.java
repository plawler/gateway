package org.inbloom.gateway.rest.validation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

/**
 * Created By: paullawler
 */
@ControllerAdvice // this works because we are using <mvc:annotation-driven/>
public class RestErrorHandler {

    private static final Logger logger = LoggerFactory.getLogger(RestErrorHandler.class);

//    @Autowired
//    private MessageSource messageSource; // for localization of messages

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ValidationError handleValidationException(MethodArgumentNotValidException exception) {
        logger.debug("handling validation errors");
        BindingResult result = exception.getBindingResult();
        return processFieldErrors(result.getFieldErrors());
    }

    private ValidationError processFieldErrors(List<FieldError> fieldErrors) {
        ValidationError validationError = new ValidationError();
        for (FieldError error : fieldErrors) {
            validationError.addFieldError(error.getField(), error.getDefaultMessage());
        }
        return validationError;
    }


    @ExceptionHandler(ValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public GatewayError handleValidationException(ValidationException error)
    {
        //TODO: log?
        return error.getGatewayError();
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public String handleValidationException(Exception error)
    {
        //TODO: log?
        return error.getMessage();
    }

}
