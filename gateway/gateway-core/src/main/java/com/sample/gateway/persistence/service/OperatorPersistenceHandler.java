package com.sample.gateway.persistence.service;

import com.sample.gateway.core.domain.Operator;
import com.sample.gateway.core.event.*;
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
                Operator.class));
    }

    @Override
    public RetrievedOperatorEvent retrieveOperator(RetrieveOperatorEvent retrieveOperatorEvent) {
        OperatorEntity retrieved = operatorRepository.findOne(retrieveOperatorEvent.getId());
        return new RetrievedOperatorEvent(conversionService.convert(retrieved,
                Operator.class));
    }

    @Override
    public ModifiedOperatorEvent modifyOperator(ModifyOperatorEvent modifyOperatorEvent) {
        OperatorEntity entity = operatorRepository.findOne(modifyOperatorEvent.getId());

        entity.setOperatorName(modifyOperatorEvent.getOperatorName());
        entity.setApiUri(modifyOperatorEvent.getApiUri());
        entity.setConnectorUri(modifyOperatorEvent.getConnectorUri());
        entity.setEnabled(modifyOperatorEvent.isEnabled());
        entity.setContractStartOn(modifyOperatorEvent.getContractStartOn());
        entity.setContractEndOn(modifyOperatorEvent.getContractEndOn());
        entity.setPrimaryContactEmail(modifyOperatorEvent.getPrimaryContactEmail());
        entity.setPrimaryContactName(modifyOperatorEvent.getPrimaryContactName());
        entity.setPrimaryContactPhone(modifyOperatorEvent.getPrimaryContactPhone());

        operatorRepository.save(entity);

        return new ModifiedOperatorEvent(modifyOperatorEvent.getId(), conversionService.convert(entity, Operator.class), true, "Update Successful");
    }
}
