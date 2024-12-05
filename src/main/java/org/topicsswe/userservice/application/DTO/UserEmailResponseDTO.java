package org.topicsswe.userservice.application.DTO;

import org.topicsswe.userservice.domain.objects.UserEmail;

import java.util.Date;

public record UserEmailResponseDTO(long id, String fromUsername, Date timestamp, String message) {

    public static UserEmailResponseDTO toUserEmailResponseDTO(UserEmail email) {
        return new UserEmailResponseDTO(email.getId(),
                email.getFrom(),
                email.getTimestamp(),
                email.getMessage());
    }
}
