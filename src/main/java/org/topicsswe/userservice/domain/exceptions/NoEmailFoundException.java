package org.topicsswe.userservice.domain.exceptions;

import lombok.Getter;

@Getter
public class NoEmailFoundException extends RuntimeException {

    private final long id;

    public NoEmailFoundException(long id) {
        super("No email found for ID: " + id);
        this.id = id;
    }

}
