package org.inbloom.notification.client.email.services;

/**
 * Created by tfritz on 3/24/14.
 */

import org.inbloom.notification.client.email.messages.MimeMessageBuilder;
import org.inbloom.notification.domain.NotificationTemplateEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.nio.charset.StandardCharsets;
import java.util.Locale;

/**
 * Simple service to encapsulate construction and sending of email-templates.
 * For example of how to us the Email Service, refer to the controller example:
 * <url>http://www.thymeleaf.org/springmail.html</url>
 */

@Service
public class EmailService {
    final Logger log = LoggerFactory.getLogger(EmailService.class);   //IOC friendly to use instance variable for logger.

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private TemplateEngine templateEngine;

    /**
     * Builds and sends and account registration confirmation email message, using the specified Locale.
     * @param recipientName The name of the recipient the message is intended for.
     * @param recipientEmail The email address to send the message to.
     * @param confirmationLink The link to embed within the email.
     * @param locale The locale for the message (determines which resource bundle is used for multilingual support). Use Locale.ENGLISH.
     * @throws MessagingException
     */
    public void sendAccountRegistrationConfirmationEmail(final String recipientName, final String recipientEmail, final String confirmationLink, Locale locale) throws MessagingException {
        final String subject = "inBloom Developer Account Validation";
        final String replyTo = "do-notreply@inbloom.org";
        final String from = replyTo;
        if (locale == null) {
            locale = Locale.ENGLISH;
        }

        // Prepare the evaluation context
        final Context context = new Context(locale);
        context.setVariable("name", recipientName);
        context.setVariable("link", confirmationLink);
        context.setVariable("comma", ",");  //used by resource bundle to append punctuation.

        final String htmlContent = this.templateEngine.process(NotificationTemplateEnum.CONFIRM_ACCOUNT_REGISTRATION.getTemplateNameName(), context);

        MimeMessageBuilder msgBuilder = new MimeMessageBuilder(this.mailSender, StandardCharsets.UTF_8.name());  //uses a spring helper within the builder
        final MimeMessage msg = msgBuilder.subject(subject).replyTo(replyTo).from(from).to(recipientEmail).body(htmlContent).isHtml(true).build();
        mailSender.send(msg);
    }
}
