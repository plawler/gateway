package com.sample.gateway.rest;

import com.sample.gateway.rest.validation.RestErrorHandler;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.method.annotation.ExceptionHandlerMethodResolver;
import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver;
import org.springframework.web.servlet.mvc.method.annotation.ServletInvocableHandlerMethod;

import java.lang.reflect.Method;

/**
 * Created By: paullawler
 */
public class TestUtil {

    // http://stackoverflow.com/questions/15302243/spring-mvc-controllers-unit-test-not-calling-controlleradvice
    public static ExceptionHandlerExceptionResolver createExceptionResolver() {

        ExceptionHandlerExceptionResolver exceptionResolver = new ExceptionHandlerExceptionResolver() {
            protected ServletInvocableHandlerMethod getExceptionHandlerMethod(HandlerMethod handlerMethod, Exception exception) {
                Method method = new ExceptionHandlerMethodResolver(RestErrorHandler.class).resolveMethod(exception);
                return new ServletInvocableHandlerMethod(new RestErrorHandler(), method);
            }
        };

        exceptionResolver.getMessageConverters().add(new MappingJackson2HttpMessageConverter()); // without this will throw org.springframework.web.HttpMediaTypeNotAcceptableException
        exceptionResolver.afterPropertiesSet();

        return exceptionResolver;
    }

}
