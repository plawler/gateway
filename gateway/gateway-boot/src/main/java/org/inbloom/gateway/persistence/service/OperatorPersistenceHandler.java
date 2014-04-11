package org.inbloom.gateway.persistence.service;

import org.inbloom.gateway.common.domain.Operator;
import org.inbloom.gateway.core.event.operator.*;
import org.inbloom.gateway.persistence.domain.OperatorEntity;
import org.inbloom.gateway.persistence.repository.OperatorRepository;
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
        return RegisteredOperatorEvent.success(conversionService.convert(operatorEntity,
                Operator.class));
    }

    @Override
    public RetrievedOperatorEvent retrieveOperator(RetrieveOperatorEvent retrieveOperatorEvent) {
        OperatorEntity retrieved = operatorRepository.findOne(retrieveOperatorEvent.getId());
        if(retrieved == null) {
            return RetrievedOperatorEvent.notFound();
        }
        else {
            return RetrievedOperatorEvent.success(conversionService.convert(retrieved,
                Operator.class));
        }
    }

    @Override
    public ModifiedOperatorEvent modifyOperator(ModifyOperatorEvent modifyOperatorEvent) {
        OperatorEntity entity = operatorRepository.findOne(modifyOperatorEvent.getId());

        entity.setOperatorName(modifyOperatorEvent.getOperatorName());
        entity.setApiUri(modifyOperatorEvent.getApiUri());
        entity.setConnectorUri(modifyOperatorEvent.getConnectorUri());
        entity.setEnabled(modifyOperatorEvent.isEnabled());
        entity.setPrimaryContactEmail(modifyOperatorEvent.getPrimaryContactEmail());
        entity.setPrimaryContactName(modifyOperatorEvent.getPrimaryContactName());
        entity.setPrimaryContactPhone(modifyOperatorEvent.getPrimaryContactPhone());

        operatorRepository.save(entity);

        return ModifiedOperatorEvent.success(modifyOperatorEvent.getId(), conversionService.convert(entity, Operator.class));
    }
}
