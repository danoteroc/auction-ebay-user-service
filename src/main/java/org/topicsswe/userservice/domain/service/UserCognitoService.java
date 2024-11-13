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
        var ans =  cognitoClient.registerUser(user.getUsername(), user.getPasswordHash(), user.getName());
        cognitoClient.confirmSignUp(user.getUsername());
        return ans;
    }

    public String login(String username, String password) {
        return cognitoClient.loginUser(username, password);
    }

    public String getUserInfo() {
//        cognitoClient.getCurrentUserInfo();
        return null;
    }

    public String addPrivileges(String username) {
        return cognitoClient.addPrivileges(username);
    }

    public String suspendUser(String username) {
        return cognitoClient.suspendUser(username);
    }
}
