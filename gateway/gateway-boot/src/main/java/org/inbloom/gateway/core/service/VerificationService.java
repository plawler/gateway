package org.inbloom.gateway.core.service;

import org.inbloom.gateway.core.event.*;
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

    public CreatedVerificationEvent createVerification(CreateVerificationEvent createEvent);
    public ModifiedVerificationEvent modifyVerification(ModifyVerificationEvent modifyEvent);
    public RetrievedVerificationEvent retrieveVerification(RetrieveVerificationEvent retrieveEvent);
}
