package org.topicsswe.userservice.domain.exceptions;

public class NoUserFoundException extends RuntimeException {
    public NoUserFoundException() {
        super("User was not found in the database");
    }
}
