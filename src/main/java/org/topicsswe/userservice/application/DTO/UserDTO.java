package org.topicsswe.userservice.application.DTO;

import org.topicsswe.userservice.domain.objects.SiteUser;
import org.topicsswe.userservice.domain.objects.Status;

import java.time.Instant;
import java.util.Date;

public record UserDTO(
        String status,
        String name,
        String email,
        String username,
        String password
) {
    public SiteUser toUser() {
        SiteUser user = new SiteUser();
        user.setUsername(username);
        user.setPasswordHash(password);
        user.setName(name);
        user.setStatus(Status.valueOf(status));
        user.setEmail(email);
        user.setRegistrationDate(Date.from(Instant.now()));

        return user;
    }

    public static UserDTO toUserDTO(SiteUser user) {
        return new UserDTO(
                "",
                user.getName(),
                user.getEmail(),
                user.getUsername(),
                ""
                );
    }
}
