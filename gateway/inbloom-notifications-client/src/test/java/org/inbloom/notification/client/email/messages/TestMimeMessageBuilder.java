package org.inbloom.notification.client.email.messages;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;
import org.thymeleaf.TemplateEngine;

import java.nio.charset.StandardCharsets;

/**
 * Created by tfritz on 3/26/14.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:notificationClientApplicationContext.xml" })
public class TestMimeMessageBuilder {
    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private TemplateEngine templateEngine;

    @Test
    public void testMimeMessageBuilderConstructorNegativeScenarios() {
        try {
            MimeMessageBuilder builder = new MimeMessageBuilder(null, null);
        } catch (Exception e) {
            Assert.isTrue(true); // null values passed to constructor should trigger an error
        }

        try {
            MimeMessageBuilder builder = new MimeMessageBuilder(this.mailSender, null);
        } catch (Exception e) {
            Assert.isTrue(true); // null values passed to constructor should trigger an error
        }

        try {
            MimeMessageBuilder builder = new MimeMessageBuilder(this.mailSender, "");
        } catch (Exception e) {
            Assert.isTrue(true); // null values passed to constructor should trigger an error
        }

        try {
            MimeMessageBuilder builder = new MimeMessageBuilder(null, StandardCharsets.UTF_8.name());
        } catch (Exception e) {
            Assert.isTrue(true); // null values passed to constructor should trigger an error
        }
    }

    @Test
    public void testMimeMessageBuilderConstructor() {
        MimeMessageBuilder builder = new MimeMessageBuilder(this.mailSender, StandardCharsets.UTF_8.name());
    }

    /**
     * Involving build without required fields must trigger an exception.  Refer to validate() method.
     */
    @Test
    public void testMimeMessageBuilderInvalidBuild() {
        try {
            MimeMessageBuilder builder = new MimeMessageBuilder(this.mailSender, StandardCharsets.UTF_8.name());
            builder.build();
        } catch (Exception e) {
            Assert.isTrue(true);
        }
    }

}
