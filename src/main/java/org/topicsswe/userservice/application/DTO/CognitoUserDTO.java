package org.topicsswe.userservice.application.DTO;

import org.topicsswe.userservice.domain.objects.SiteUser;
import org.topicsswe.userservice.domain.objects.Status;

import java.time.Instant;
import java.util.Date;

public record CognitoUserDTO(
        String status,
        String name,
        String email
) {
    public SiteUser toUser() {
        SiteUser user = new SiteUser();
        user.setName(name);
        user.setStatus(Status.valueOf(status));
        user.setEmail(email);

        return user;
    }

    public static CognitoUserDTO toUserDTO(SiteUser user) {
        return new CognitoUserDTO(
                "",
                user.getName(),
                user.getEmail()
                );
    }
}
