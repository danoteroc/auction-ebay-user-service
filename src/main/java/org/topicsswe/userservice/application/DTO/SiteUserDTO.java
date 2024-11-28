package org.topicsswe.userservice.application.DTO;

import org.topicsswe.userservice.domain.objects.SiteUser;

public record SiteUserDTO(
        String cognitoUserId,
        String email,
        String name,
        String address,
        String telephoneNumber
) {

    public SiteUser toSiteUser() {
        return new SiteUser(cognitoUserId, email, name, address, telephoneNumber);
    }

}
