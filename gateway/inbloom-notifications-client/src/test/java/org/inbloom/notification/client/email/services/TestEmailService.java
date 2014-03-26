package org.inbloom.notification.client.email.services;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.jvnet.mock_javamail.Mailbox;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.mail.Message;
import java.util.List;
import java.util.Locale;

/**
 * Created by tfritz on 3/25/14.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:notificationClientApplicationContext.xml" })
public class TestEmailService {
    final Logger log = LoggerFactory.getLogger(TestEmailService.class);   //IOC friendly to use instance variable for logger.

    @Autowired
    private EmailService emailService;

    @Test
    public void testEmailService() {
        log.info(">>>TestEmailService.testEmailService()");
        final String recipientName = "Nasty Ass";
        final String recipientEmail = "nasty.ass@nycsubway.org";
        final String confirmationLink = "http://dummy.com?token=12345";
        final Locale locale = Locale.ENGLISH;

        try {
            emailService.sendAccountRegistrationConfirmationEmail(recipientName, recipientEmail, confirmationLink, locale);
            List<Message> inbox = Mailbox.get(recipientEmail);
            log.info("   # of messages within mailbox: " + inbox.size());
            for (Message msg : inbox) {
                log.info("   Content Type: " + msg.getContentType());
                log.info("   Content     : " + (String)msg.getContent());
            }
        } catch (Exception e) {
            System.out.println(ExceptionUtils.getMessage(e));
        }
        log.info("<<<TestEmailService.testEmailService()");
        //assertEquals(1, teamService.findAllTeamsFromCountry("Bulgaria").size());
    }
}

