package com.sample.gateway.core.event;

/**
 * Created By: paullawler
 */
public interface Event {

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

    public Status status();

}
