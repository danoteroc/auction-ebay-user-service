package org.topicsswe.userservice.domain.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.topicsswe.userservice.domain.objects.LoginResponse;
import org.topicsswe.userservice.domain.objects.SiteUser;
import org.topicsswe.userservice.infrastructure.cognito.CognitoClient;
import software.amazon.awssdk.services.cognitoidentityprovider.model.GetUserResponse;

@Service
@AllArgsConstructor
public class UserCognitoService {

    private final CognitoClient cognitoClient;

//    public String register(SiteUser user) {
//        var ans =  cognitoClient.registerUser(user.getUsername(), user.getPasswordHash(), user.getName());
//        cognitoClient.confirmSignUp(user.getUsername());
//        return ans;
//    }

    public LoginResponse login(String username, String password) {
        return cognitoClient.loginUser(username, password);
    }

//    public SiteUser getUserInfo(String jwt) {
//        GetUserResponse response =  cognitoClient.getCurrentUserInfo(jwt);
//        SiteUser user = new SiteUser();
//        user.setUsername(response.getValueForField("Username", String.class).orElseThrow(() ->
//                new RuntimeException("User registered without username. This should not be happening")));
//        response.getValueForField("name", String.class).ifPresent(user::setName);
//        response.getValueForField("email", String.class).ifPresent(user::setEmail);
//
//        return user;
//    }

    public String addPrivileges(String username) {
        return cognitoClient.addPrivileges(username);
    }

    public String suspendUser(String username) {
        return cognitoClient.suspendUser(username);
    }
}
