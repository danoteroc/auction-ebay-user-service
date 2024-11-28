package org.topicsswe.userservice.domain.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.topicsswe.userservice.domain.objects.SiteUser;
import org.topicsswe.userservice.infrastructure.SiteUserRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class SiteUserService {

    private final SiteUserRepository repository;

    public SiteUser getInfo(String cognitoUserId) {

        // If there is no user in the db, save it with empty values for all fields
        return repository.findByCognitoUserId(cognitoUserId).orElseGet(() -> {
            var newUser = new SiteUser(cognitoUserId);
            repository.save(newUser);
            return newUser;
        });
    }

    public SiteUser updateInfo(SiteUser newUserInfo) {
        // TODO Check that the current user exists before saving?

        var getCurrentUser = getInfo(newUserInfo.getCognitoUserId());

        newUserInfo.setBlocked(getCurrentUser.isBlocked());
        newUserInfo.setStatus(getCurrentUser.getStatus());

        // Update repository
        return repository.save(newUserInfo);
    }

    public List<SiteUser> getAllInfo() {
        return repository.findAll();
    }
}
