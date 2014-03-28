package org.inbloom.gateway.persistence.service;


import org.inbloom.gateway.core.event.*;

/**
 * Created by lloydengebretsen on 3/21/14.
 */
public interface VerificationPersistenceService {

    public CreatedVerificationEvent createVerification(CreateVerificationEvent createVerificationEvent);
    public VerifiedEmailEvent modifyVerification(VerifyEmailEvent modifyVerificationEvent);
    public RetrievedVerificationEvent retrieveVerification(RetrieveVerificationEvent retrieveVerificationEvent);
}
