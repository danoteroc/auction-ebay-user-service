package org.topicsswe.userservice.domain.exceptions;

public class NoValidRoleException extends RuntimeException {
    public NoValidRoleException() {
        super("No valid role for the user");
    }
}
