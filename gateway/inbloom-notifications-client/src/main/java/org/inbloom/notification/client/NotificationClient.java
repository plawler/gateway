package org.inbloom.notification.client;

import org.inbloom.notification.client.email.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.mail.MessagingException;
import java.util.Locale;

/**
 * Singleton entrypoint for sending notifications.  This is a simple implementation.  A more robust impl could force notification services (email, etc) to
 * implement a common interface.
 * TODO Right now we don't have many messages to send so this is an ok, first implementation.  Services could become more course grained by utilizing a notification type enum which has a relationship to a notification type parameters enum (would strongly type a notification to the data it needs to replace tokens for.
 * Created by tfritz on 3/27/14.
 */
public class NotificationClient {

    public enum NotificationTypeEnum {
        EMAIL;
    }

    /** Private constructor prevents instantiation from other classes. */
    private NotificationClient() { }

    /**
     * SingletonHolder is loaded on the first execution of Singleton.getInstance()
     * or the first access to SingletonHolder.INSTANCE, not before.
     */
    private static class SingletonHolder {
        private static final NotificationClient INSTANCE = new NotificationClient();
    }

    public static NotificationClient getInstance() {
        return SingletonHolder.INSTANCE;
    }

    @Autowired
    private EmailService emailService;

    /**
     * Builds and sends and account registration confirmation  message, using the specified Locale.
     * @param notificationTypeEnum The type of notification.
     * @param recipientName The name of the recipient the message is intended for.
     * @param recipientEmail The email address to send the message to.
     * @param confirmationLink The link to embed within the email.
     * @param locale The locale for the message (determines which resource bundle is used for multilingual support). Use Locale.ENGLISH.
     * @throws MessagingException
     */
    public void sendAccountRegistrationConfirmation(final NotificationTypeEnum notificationTypeEnum, final String recipientName,
                                                    final String recipientEmail, final String confirmationLink, Locale locale) throws MessagingException {
        if (notificationTypeEnum == null) {
            throw new IllegalStateException("A Notification Type must be provided.");
        }

        switch (notificationTypeEnum) {
            case EMAIL:  this.emailService.sendAccountRegistrationConfirmation(recipientName, recipientEmail,confirmationLink,locale);
                break;
            default:  this.emailService.sendAccountRegistrationConfirmation(recipientName, recipientEmail,confirmationLink,locale);
                break;
        }

    }

}
