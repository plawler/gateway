package org.inbloom.notification.client.email.services;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Locale;

/**
 * Created by tfritz on 3/25/14.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:notificationClientApplicationContext.xml" })
public class TestEmailService {

    @Autowired
    private EmailService emailService;

    @Test
    public void testEmailService() {
        final String recipientName = "Nasty Ass";
        final String recipientEmail = "nasty.ass@nycsubway.org";
        final String confirmationLink = "http://dummy.com?token=12345";
        final Locale locale = Locale.ENGLISH;

        try {
            emailService.sendAccountRegistrationConfirmationEmail(recipientName, recipientEmail, confirmationLink, locale);
        } catch (Exception e) {
            System.out.println(ExceptionUtils.getMessage(e));
        }

        //assertEquals(1, teamService.findAllTeamsFromCountry("Bulgaria").size());
    }
}

