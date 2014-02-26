package com.sample.gateway.util.mapper;

import com.sample.gateway.core.domain.Application;
import com.sample.gateway.persistence.domain.ApplicationEntity;
import org.modelmapper.ModelMapper;
import org.springframework.core.convert.converter.Converter;

/**
 * Created with IntelliJ IDEA.
 * User: paullawler
 * Date: 2/23/14
 * Time: 7:22 PM
 * To change this template use File | Settings | File Templates.
 */
public class PersistentApplicationModelMapper implements Converter<Application, ApplicationEntity> {

    @Override
    public ApplicationEntity convert(Application application) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(application, ApplicationEntity.class);
    }

}
