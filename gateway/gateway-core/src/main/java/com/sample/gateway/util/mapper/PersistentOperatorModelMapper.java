package com.sample.gateway.util.mapper;

import com.sample.gateway.core.domain.Operator;
import com.sample.gateway.persistence.domain.OperatorEntity;
import org.modelmapper.ModelMapper;
import org.springframework.core.convert.converter.Converter;

/**
 * Created with IntelliJ IDEA.
 * User: paullawler
 * Date: 2/25/14
 * Time: 2:39 PM
 * To change this template use File | Settings | File Templates.
 */
public class PersistentOperatorModelMapper implements Converter<Operator, OperatorEntity> {

    @Override
    public OperatorEntity convert(Operator operator) {
        ModelMapper mapper = new ModelMapper();
        return mapper.map(operator, OperatorEntity.class);
    }

}
