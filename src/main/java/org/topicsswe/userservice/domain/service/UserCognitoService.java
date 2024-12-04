package org.topicsswe.userservice.domain.service;

import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.topicsswe.userservice.domain.objects.LoginResponse;
import org.topicsswe.userservice.domain.objects.SiteUser;
import org.topicsswe.userservice.infrastructure.SiteUserRepository;
import org.topicsswe.userservice.infrastructure.cognito.CognitoClient;
import software.amazon.awssdk.services.cognitoidentityprovider.model.AttributeType;

@Service
@AllArgsConstructor
public class UserCognitoService {

    public static final String EMAIL = "email";
    private final CognitoClient cognitoClient;
    private final SiteUserRepository repository;

    @PostConstruct
    public void init() {
        saveCurrentCognitoUsersToDatabase();
    }

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

    public void saveCurrentCognitoUsersToDatabase() {
        var users = cognitoClient.getAllUsersInCognito();

        // map to SiteUser that the DB can understand and add to database
        users.forEach(awsUser -> {
            var id = awsUser.username();
            var foundUser = repository.findByCognitoUserId(id);
            var email = awsUser.attributes().stream().filter(at -> at.name().equals(EMAIL))
                    .findFirst()
                    .map(AttributeType::value)
                    .orElse(null);
            if (foundUser.isPresent()) {
                var user = foundUser.get();
                if (user.getEmail() == null || user.getEmail().isEmpty()) {
                    user.setEmail(email);
                }
                repository.save(user);
            } else {
                var user = new SiteUser(
                        awsUser.username(),
                        email,
                        false,
                        "STANDARD_ACTIVE"
                );
                repository.save(user);
            }
        });
    }
}
