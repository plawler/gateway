package com.sample.gateway.util.mapper;

import com.sample.gateway.persistence.domain.ApplicationEntity;
import org.modelmapper.ModelMapper;
import org.springframework.core.convert.converter.Converter;

/**
 * Created with IntelliJ IDEA.
 * User: paullawler
 * Date: 2/25/14
 * Time: 11:16 AM
 * To change this template use File | Settings | File Templates.
 */
public class DomainApplicationModelMapper implements Converter<ApplicationEntity, com.sample.gateway.core.domain.Application> {

    @Override
    public com.sample.gateway.core.domain.Application convert(ApplicationEntity applicationEntity) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(applicationEntity, com.sample.gateway.core.domain.Application.class);
    }

}
