package com.sample.gateway.util.mapper;

import com.sample.gateway.core.domain.Application;
import org.modelmapper.ModelMapper;
import org.springframework.core.convert.converter.Converter;

/**
 * Created with IntelliJ IDEA.
 * User: paullawler
 * Date: 2/23/14
 * Time: 7:22 PM
 * To change this template use File | Settings | File Templates.
 */
public class PersistentApplicationModelMapper implements Converter<Application, com.sample.gateway.persistence.domain.Application> {

    @Override
    public com.sample.gateway.persistence.domain.Application convert(Application application) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(application, com.sample.gateway.persistence.domain.Application.class);
    }

}
