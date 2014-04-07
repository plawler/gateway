package org.inbloom.gateway.persistence.service;

import org.inbloom.gateway.core.domain.User;
import org.inbloom.gateway.core.domain.Verification;
import org.inbloom.gateway.core.event.*;
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
    private UserRepository userRepository;

    @Autowired
    private ConversionService conversionService;

    @Override
    public CreatedVerificationEvent createVerification(CreateVerificationEvent createVerificationEvent) {

        User user = createVerificationEvent.getData().getUser();
        if(user == null){
            //could not find the user so return not found error
            return CreatedVerificationEvent.notFound();
        }

        VerificationEntity verificationEntity = conversionService.convert(createVerificationEvent.getData(), VerificationEntity.class);
        verificationEntity.setUserId(user.getUserId());
        verificationRepository.save(verificationEntity);
        return CreatedVerificationEvent.success(conversionService.convert(verificationEntity, Verification.class));
    }

    @Override
    public RetrievedVerificationEvent retrieveForAccountValidation(ValidateAccountSetupEvent validateAccountSetupEvent) {
        VerificationEntity verificationEntity = verificationRepository.findByToken(validateAccountSetupEvent.getValidationToken());
        if (verificationEntity == null) {
            return RetrievedVerificationEvent.newNotFound();
        }

        Verification verification = conversionService.convert(verificationEntity, Verification.class);
        User user = conversionService.convert(userRepository.findOne(verification.getUserId()), User.class);
        verification.setUser(user); // todo: CODE REVIEW!!

        return RetrievedVerificationEvent.success(verification);
    }

    @Override
    public ModifiedVerificationEvent modifyVerification(ModifyVerificationEvent modifyVerificationEvent) {
        VerificationEntity verificationEntity = verificationRepository.findByToken(modifyVerificationEvent.getToken());
        if(verificationEntity == null){
            return ModifiedVerificationEvent.notFound();
        }
        verificationEntity.setClientIpAddress(modifyVerificationEvent.getClientIpAddress());
        verificationEntity.setVerified(modifyVerificationEvent.getVerified());
        verificationRepository.save(verificationEntity);
        return ModifiedVerificationEvent.success(conversionService.convert(verificationEntity, Verification.class));
    }

    @Override
    public RetrievedVerificationEvent retrieveVerification(RetrieveVerificationEvent retrieveVerificationEvent) {
        VerificationEntity verificationEntity = verificationRepository.findByToken(retrieveVerificationEvent.getVerificationToken());
        if (verificationEntity == null)
            return RetrievedVerificationEvent.newNotFound();

        return RetrievedVerificationEvent.success(conversionService.convert(verificationEntity, Verification.class));
    }
}
