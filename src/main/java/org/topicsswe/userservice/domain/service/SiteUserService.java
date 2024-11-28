package org.topicsswe.userservice.domain.service;

import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.topicsswe.userservice.domain.objects.SiteUser;
import org.topicsswe.userservice.domain.objects.Status;
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

    @PostConstruct
    public void updateTableWithExistingCustomers() {
        //TODO
    }

    public void suspendUser(String userId) {
        var user = repository.findByCognitoUserId(userId).orElseThrow(
                () -> new RuntimeException("No user found for suspending")
        );

        user.setStatus(Status.BLOCKED);
        repository.save(user);
    }

    public void grantPrivileges(String username) {
        var user = repository.findByCognitoUserId(username).orElseThrow(
                () -> new RuntimeException("No user found for granting privileges")
        );

        user.setAdmin(true);
        repository.save(user);
    }
}
