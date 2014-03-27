package org.inbloom.gateway.fixture;

import org.inbloom.gateway.core.domain.User;
import org.inbloom.gateway.core.domain.Verification;

import java.util.Date;

/**
 * @author benjaminmorgan
 *         Date: 3/27/14
 */
public class VerificationFixture {

    public static Verification buildUnverifiedVerification(Long userId, Long verificationId)
    {
        Verification verification = new Verification();

        Date now = new Date();
        Date later = new Date(now.getTime() + 4*24*60*60*1000); //4 days later

        User user = UserFixture.buildUser();
        user.setUserId(userId);

        verification.setVerificationId(verificationId);
        verification.setValidFrom(now);
        verification.setValidUntil(later);
        verification.setUser(user);
        verification.setToken("XXSecretRandomTokenXX");
        verification.setVerified(false);

        return verification;
    }

    public static Verification buildVerifiedVerification(Long userId, Long verificationId)
    {
        Verification verification = buildUnverifiedVerification(userId, verificationId);
        verification.setVerified(true);
        verification.setClientIpAddress("192.168.1.1");
        return verification;
    }

}
