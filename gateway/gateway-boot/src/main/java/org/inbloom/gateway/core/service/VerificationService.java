package org.inbloom.gateway.core.service;

import org.inbloom.gateway.core.event.verification.*;
import org.springframework.stereotype.Service;


/**
 * Created with IntelliJ IDEA.
 * User: benjaminmorgan
 * Date: 3/25/14
 * Time: 8:46 AM
 * To change this template use File | Settings | File Templates.
 */
@Service
public interface VerificationService {

    static final int VERIFICATION_TIMEOUT = 3*24*60*60*1000; //3 days

    CreatedVerificationEvent createVerification(CreateVerificationEvent createEvent);
    ValidatedAccountSetupEvent validateAccountSetup(ValidateAccountSetupEvent validateEvent);
    ModifiedVerificationEvent modifyVerification(ModifyVerificationEvent modifyEvent);
    RetrievedVerificationEvent retrieveVerification(RetrieveVerificationEvent retrieveEvent);

}
