package org.inbloom.gateway.core.event;

/**
 * Created By: paullawler
 */
public interface ResponseEvent {

    enum Status {
        SUCCESS("completed successfully"),
        NOT_FOUND("not found"),
        FAILED("failed");

        private String message;

        Status(String message) {
            this.message = message;
        }

        public String getMessage() {
            return this.message;
        }
    }

    Status status();

}
