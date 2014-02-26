package com.sample.gateway.persistence.service;

import com.sample.gateway.core.event.RegisterOperatorEvent;
import com.sample.gateway.core.event.RegisteredOperatorEvent;
import com.sample.gateway.core.event.RetrieveOperatorEvent;
import com.sample.gateway.core.event.RetrievedOperatorEvent;
import com.sample.gateway.persistence.domain.OperatorEntity;
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
        OperatorEntity operatorEntity = conversionService.convert(registerOperatorEvent.getData(), OperatorEntity.class);
        operatorRepository.save(operatorEntity);
        return new RegisteredOperatorEvent(conversionService.convert(operatorEntity,
                com.sample.gateway.core.domain.Operator.class));
    }

    @Override
    public RetrievedOperatorEvent retrieveOperator(RetrieveOperatorEvent retrieveOperatorEvent) {
        OperatorEntity retrieved = operatorRepository.findOne(retrieveOperatorEvent.getId());
        return new RetrievedOperatorEvent(conversionService.convert(retrieved,
                com.sample.gateway.core.domain.Operator.class));
    }
}
