package org.inbloom.gateway.fixture;

import org.inbloom.gateway.core.domain.Verification;
import org.joda.time.DateTime;

import java.util.Date;

/**
 * Created By: paullawler
 */
public class VerificationFixture {

    public static Verification expiredVerification(Date validationDate) {
        DateTime validFromDate = new DateTime(validationDate).minusDays(5);
        DateTime validUntilDate = validFromDate.plus(3);

        Verification expired = new Verification();
        expired.setVerificationId(123456L);
        expired.setValidFrom(validFromDate.toDate());
        expired.setValidUntil(validUntilDate.toDate());
        expired.setUser(UserFixture.buildUser());

        return expired;
    }

    public static Verification validVerification(Date validationDate) {
        DateTime validFromDate = new DateTime(validationDate);
        DateTime validUntilDate = validFromDate.plusDays(3);

        Verification valid = new Verification();
        valid.setVerificationId(123456L);
        valid.setValidFrom(validationDate);
        valid.setValidUntil(validUntilDate.toDate());
        valid.setUser(UserFixture.buildUser());

        return valid;
    }

}
