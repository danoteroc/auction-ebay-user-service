package org.topicsswe.userservice.domain.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.topicsswe.userservice.domain.objects.SiteUser;
import org.topicsswe.userservice.infrastructure.cognito.CognitoClient;

@Service
@AllArgsConstructor
public class UserCognitoService {

    private final CognitoClient cognitoClient;

    public String register(SiteUser user) {
        return cognitoClient.registerUser(user.getUsername(), user.getPasswordHash(), user.getName());
    }
}
