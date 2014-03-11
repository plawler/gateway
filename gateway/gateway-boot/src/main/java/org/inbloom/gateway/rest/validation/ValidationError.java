package org.inbloom.gateway.rest.validation;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: paullawler
 * Date: 2/28/14
 * Time: 9:11 AM
 * To change this template use File | Settings | File Templates.
 */
public class ValidationError {

    private List<FieldError> errors;

    public ValidationError() {
        errors = Lists.newArrayList();
    }

    public void addFieldError(String field, String message) {
        errors.add(new FieldError(field, message));
    }

    public List<FieldError> getErrors() {
        return errors;
    }

}
