package com.sample.gateway.util.mapper;

import com.sample.gateway.persistence.domain.Operator;
import org.modelmapper.ModelMapper;
import org.springframework.core.convert.converter.Converter;

/**
 * Created with IntelliJ IDEA.
 * User: paullawler
 * Date: 2/25/14
 * Time: 2:34 PM
 * To change this template use File | Settings | File Templates.
 */
public class DomainOperatorModelMapper implements Converter<Operator, com.sample.gateway.core.domain.Operator> {
    @Override
    public com.sample.gateway.core.domain.Operator convert(Operator operator) {
        ModelMapper mapper = new ModelMapper();
        return mapper.map(operator, com.sample.gateway.core.domain.Operator.class);
    }
}
