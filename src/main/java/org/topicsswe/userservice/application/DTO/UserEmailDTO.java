package org.topicsswe.userservice.application.DTO;

import org.topicsswe.userservice.domain.objects.UserEmail;

public record UserEmailDTO(
        String fromUsername,
        String message
) {
    public UserEmail toUserEmail() {
        return new UserEmail(fromUsername, message);
    }
}
