package com.sample.gateway.persistence.service;

import com.sample.gateway.core.event.RegisterOperatorEvent;
import com.sample.gateway.core.event.RegisteredOperatorEvent;
import com.sample.gateway.persistence.domain.Operator;
import com.sample.gateway.persistence.repository.OperatorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

/**
 * Created by lloydengebretsen on 2/20/14.
 */
@Service
public class OperatorPersistenceHandler implements OperatorPersistenceService {

    @Autowired
    private OperatorRepository operatorRepository;

    @Autowired
    private ConversionService conversionService;

    @Override
    public RegisteredOperatorEvent registerOperator(RegisterOperatorEvent registerOperatorEvent) {
        Operator operator = conversionService.convert(registerOperatorEvent.getData(), Operator.class);
        operatorRepository.save(operator);
        return new RegisteredOperatorEvent(conversionService.convert(operator,
                com.sample.gateway.core.domain.Operator.class));
    }
}
