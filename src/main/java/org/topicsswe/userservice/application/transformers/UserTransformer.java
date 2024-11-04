package org.topicsswe.userservice.application.transformers;

import org.topicsswe.userservice.application.DTO.UserDTO;
import org.topicsswe.userservice.domain.objects.Status;
import org.topicsswe.userservice.domain.objects.SiteUser;

import java.time.Instant;
import java.util.Date;

public class UserTransformer {


    public static SiteUser userDTOtoUser(UserDTO dto) {
        SiteUser newSiteUser = new SiteUser();
        newSiteUser.setStatus(Status.valueOf(dto.status()));
        newSiteUser.setRegistrationDate(Date.from(Instant.now()));
        newSiteUser.setEmail(dto.email());
        newSiteUser.setUsername(dto.username());
        newSiteUser.setPasswordHash(dto.password());
        newSiteUser.setName(dto.name());

        return newSiteUser;
    }
}
