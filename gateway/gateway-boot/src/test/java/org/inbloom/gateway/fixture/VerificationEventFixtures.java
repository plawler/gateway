package org.inbloom.gateway.fixture;

import org.inbloom.gateway.core.domain.Verification;
import org.inbloom.gateway.core.event.*;

/**
 * @author benjaminmorgan
 *         Date: 3/27/14
 */
public class VerificationEventFixtures {

    /**create**/
    public static CreateVerificationEvent buildCreateVerificationEvent() {
        return new CreateVerificationEvent(UserFixture.buildUser());
    }

    /**retrieve**/
    public static RetrieveVerificationEvent buildRetrieveVerificationEvent(String token) {
        return new RetrieveVerificationEvent(token); // todo: fix this after merge
    }

    /**created**/
    public static CreatedVerificationEvent buildSuccessCreatedVerificationEvent(Long userId, Long verificationId) {
        return CreatedVerificationEvent.success(VerificationFixture.buildUnverifiedVerification(1l, 1l));
    }

    public static CreatedVerificationEvent buildNotFoundCreatedVerificationEvent() {
        return CreatedVerificationEvent.notFound();
    }

    /**modify**/
    public static ModifyVerificationEvent buildModifyVerificationEvent() {
        return null; //TODO:
    }

    /**modified**/
    //TODO:

    /**retrieved**/
    public static RetrievedVerificationEvent buildSuccessRetrievedVerificationEvent(Verification verification) {
        return RetrievedVerificationEvent.success(verification);
    }

    public static RetrievedVerificationEvent buildNotFoundRetrievedVerificationEvent() {
        return RetrievedVerificationEvent.newNotFound();
    }

}
