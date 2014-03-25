package org.inbloom.notification.client.exceptions;

import javax.management.Notification;
import java.io.Serializable;

/**
 * Encapsulates a Notification Exception which is based on a checked Exception.
 * Created by tfritz on 3/24/14.
 */
public class NotificationException extends Exception implements Serializable {

    private String description;
    private Exception exception;
    private Notification notification;

    /**
     * Default no-args constructor.
     */
    public NotificationException() {
        super();
        this.exception = null;
        this.notification = null;
    }

    /**
     * Constructs a Notification exception that contains the underlying exception and notification message.
     * @param exception
     * @param notification
     */
    private NotificationException(final RuntimeException exception, final Notification notification) {
        super();
        this.exception = exception;
        this.notification = notification;
    }

    /**
     * Returns a description of the NotificationException, which may not be related to the root cause Exception obtained by getRootCauseException().
     * @return
     */
    public String getDescription() { return this.description; }

    /**
     * Sets the Notification Description exception.
     * @param description
     */
    public void setDescription(final String description) {
        this.description = description;
    }

    /**
     * Returns the root cause exception, if an causal exception exists, for the NotificationExcetpion.
     * @return
     */
    public Exception getRootCauseException() {
        return this.exception;
    }

    /**
     * Sets the root cause exception.
     * @param exception
     */
    public void setRootCauseException(final Exception exception) {
        this.exception = exception;
    }

    /**
     * Gets the notification,
     * @return
     */
    public Notification getNotification() {
        return this.notification;
    }

    public void setNotification(final Notification notification) {
        this.notification = notification;
    }

}
