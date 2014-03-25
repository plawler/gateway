package org.inbloom.gateway.persistence.service;

import org.inbloom.gateway.core.domain.Verification;
import org.inbloom.gateway.core.event.*;
import org.inbloom.gateway.persistence.domain.TokenEntity;
import org.inbloom.gateway.persistence.domain.UserEntity;
import org.inbloom.gateway.persistence.domain.VerificationEntity;
import org.inbloom.gateway.persistence.repository.UserRepository;
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
    private TokenPersistenceService tokenPersistenceService;
    @Autowired
    private UserRepository userRepository;


    @Autowired
    private ConversionService conversionService;

    @Override
    public CreatedVerificationEvent createVerification(CreateVerificationEvent createVerificationEvent) {

        UserEntity userEntity = userRepository.findOne(createVerificationEvent.getUserId());
        if(userEntity == null){
            //could not find the user so return not found error
            return CreatedVerificationEvent.notFound();
        }

        VerificationEntity verificationEntity = conversionService.convert(createVerificationEvent.getData(), VerificationEntity.class);
        verificationEntity.setUserId(userEntity.getUserId());
        //Generate and persist token
        TokenEntity token = tokenPersistenceService.generateTokenEntity();
        verificationEntity.setTokenId(token.getTokenId());
        verificationRepository.save(verificationEntity);
        
        return CreatedVerificationEvent.success(conversionService.convert(verificationEntity, Verification.class));
    }

    @Override
    public ModifiedVerificationEvent modifyVerification(ModifyVerificationEvent modifyVerificationEvent) {

        VerificationEntity verificationEntity = verificationRepository.findOne(modifyVerificationEvent.getVerificationId());
        if(verificationEntity == null){
            return ModifiedVerificationEvent.notFound();
        }
        verificationEntity.setVerified(modifyVerificationEvent.isVerified());
        verificationEntity.setClientIpAddress(modifyVerificationEvent.getClientIpAddress());
        verificationRepository.save(verificationEntity);
        return ModifiedVerificationEvent.success(conversionService.convert(verificationEntity, Verification.class));
    }

    @Override
    public RetrievedVerificationEvent retrieveVerification(RetrieveVerificationEvent retrieveVerificationEvent) {
        VerificationEntity verificationEntity = verificationRepository.findOne(retrieveVerificationEvent.getVerificationId());
        return new RetrievedVerificationEvent(conversionService.convert(verificationEntity, Verification.class));
    }
}
