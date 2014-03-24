package org.inbloom.gateway.persistence.service;

import org.inbloom.gateway.core.domain.Verification;
import org.inbloom.gateway.core.event.*;
import org.inbloom.gateway.persistence.domain.VerificationEntity;
import org.inbloom.gateway.persistence.repository.VerificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

/**
 * Created by lloydengebretsen on 3/21/14.
 */
@Service
public class VerificationPersistenceHandler implements VerificationPersistenceService {

    @Autowired
    private VerificationRepository verificationRepository;

    @Autowired
    private ConversionService conversionService;

    @Override
    public CreatedVerificationEvent createVerification(CreateVerificationEvent createVerificationEvent) {

        VerificationEntity verificationEntity = conversionService.convert(createVerificationEvent.getData(), VerificationEntity.class);
        verificationRepository.save(verificationEntity);
        return new CreatedVerificationEvent(conversionService.convert(verificationEntity, Verification.class));
    }

    @Override
    public ModifiedVerificationEvent modifyVerification(ModifyVerificationEvent modifyVerificationEvent) {

        VerificationEntity verificationEntity = verificationRepository.findOne(modifyVerificationEvent.getVerificationId());
        verificationEntity.setVerified(modifyVerificationEvent.isVerified());
        verificationEntity.setClientIpAddress(modifyVerificationEvent.getClientIpAddress());
        verificationRepository.save(verificationEntity);
        return new ModifiedVerificationEvent(conversionService.convert(verificationEntity, Verification.class));
    }

    @Override
    public RetrievedVerificationEvent retrieveVerification(RetrieveVerificationEvent retrieveVerificationEvent) {
        VerificationEntity verificationEntity = verificationRepository.findOne(retrieveVerificationEvent.getVerificationId());
        return new RetrievedVerificationEvent(conversionService.convert(verificationEntity, Verification.class));
    }
}
