package org.inbloom.gateway.persistence.service;

import org.inbloom.gateway.common.domain.Operator;
import org.inbloom.gateway.common.status.GatewayStatus;
import org.inbloom.gateway.common.status.Status;
import org.inbloom.gateway.core.event.GatewayAction;
import org.inbloom.gateway.core.event.GatewayRequest;
import org.inbloom.gateway.core.event.GatewayResponse;
import org.inbloom.gateway.core.event.operator.ModifiedOperatorEvent;
import org.inbloom.gateway.core.event.operator.ModifyOperatorEvent;
import org.inbloom.gateway.core.event.operator.RetrieveOperatorEvent;
import org.inbloom.gateway.core.event.operator.RetrievedOperatorEvent;
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
    public GatewayResponse<Operator> registerOperator(GatewayRequest<Operator> registerOperatorEvent) {
        OperatorEntity operatorEntity = conversionService.convert(registerOperatorEvent.getPayload(), OperatorEntity.class);
        operatorRepository.save(operatorEntity);

        return new GatewayResponse<Operator>(GatewayAction.CREATE, conversionService.convert(operatorEntity, Operator.class), new GatewayStatus(Status.SUCCESS));
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
