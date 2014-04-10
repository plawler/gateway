package org.inbloom.gateway.persistence.service;


import org.inbloom.gateway.core.event.verification.*;

/**
 * Created by lloydengebretsen on 3/21/14.
 */
public interface VerificationPersistenceService {

    public CreatedVerificationEvent createVerification(CreateVerificationEvent createVerificationEvent);
    public RetrievedVerificationEvent retrieveForAccountValidation(ValidateAccountSetupEvent validateAccountSetupEvent);
    public RetrievedVerificationEvent retrieveVerification(RetrieveVerificationEvent retrieveVerificationEvent);
    public ModifiedVerificationEvent modifyVerification(ModifyVerificationEvent modifyVerificationEvent);
}
