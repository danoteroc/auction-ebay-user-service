package org.topicsswe.userservice.infrastructure.cognito;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.topicsswe.userservice.domain.objects.LoginResponse;
import software.amazon.awssdk.auth.credentials.*;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.cognitoidentityprovider.CognitoIdentityProviderClient;
import software.amazon.awssdk.services.cognitoidentityprovider.model.*;

import java.util.List;
import java.util.Map;

// https://docs.aws.amazon.com/sdk-for-java/latest/developer-guide/java_cognito-identity-provider_code_examples.html
@Component
public class CognitoClient {

    private final CognitoIdentityProviderClient cognitoClient;
    @Value("${cognito.clientId}")
    private String clientId;
    @Value("${cognito.userPool}")
    private String userPool;

    public CognitoClient() {
        this.cognitoClient = CognitoIdentityProviderClient.builder()
                .credentialsProvider(ProfileCredentialsProvider.create("auction-project"))
                .region(Region.US_EAST_2)
                .build();
    }

    /**
     * Register a new user
     * @param username username
     * @param password password
     * @param name name of the user
     * @return user sub
     */
    public String registerUser(String username, String password, String name) {

        long unixTimestamp = System.currentTimeMillis() / 1000L;

        List<AttributeType> at = List.of(
                // Updated_at
                AttributeType.builder()
                        .name("updated_at")
                        .value(Long.toString(unixTimestamp))
                        .build(),
                // name
                AttributeType.builder()
                        .name("name")
                        .value(name)
                        .build(),
                // role
                AttributeType.builder()
                        .name("custom:role")
                        .value("STANDARD") //TODO switch
                        .build()
        );

        var request = SignUpRequest.builder()
                .clientId(clientId)
                .userAttributes(at)
                .username(username)
                .password(password)
                .build();
        SignUpResponse response = cognitoClient.signUp(request);
        return response.userSub();
    }

    public void confirmSignUp(String username) {
        var request = AdminConfirmSignUpRequest.builder()
                .username(username)
                .userPoolId(userPool)
                .build();
        cognitoClient.adminConfirmSignUp(request);
    }

    /**
     * Logs in a user
     * @param username username
     * @param password password
     * @return access token //TODO add id token to response
     */
    public LoginResponse loginUser(String username, String password) {
        var request = InitiateAuthRequest.builder()
                .clientId(clientId)
                .authFlow("USER_PASSWORD_AUTH")
                .authParameters(
                        Map.of(
                                "USERNAME", username,
                                "PASSWORD", password
                        )
                )
                .build();

        InitiateAuthResponse response = cognitoClient.initiateAuth(request);
        return new LoginResponse(
                response.authenticationResult().idToken(),
                response.authenticationResult().accessToken(),
                response.authenticationResult().refreshToken()
        );
    }

    public GetUserResponse getCurrentUserInfo(String accessToken) {
        var request = GetUserRequest.builder()
                .accessToken(accessToken)
                .build();
        return cognitoClient.getUser(request);
    }

    public String addPrivileges(String username) {
        var request = AdminAddUserToGroupRequest.builder()
                .username(username)
                .groupName("admin")
                .userPoolId(userPool)
                .build();
        var response = cognitoClient.adminAddUserToGroup(request);
        return response.toString();
    }

    public String suspendUser(String username) {
        var request = AdminDisableUserRequest.builder()
                .username(username)
                .userPoolId(userPool)
                .build();
        var response = cognitoClient.adminDisableUser(request);
        return response.toString();
    }

    public List<UserType> getAllUsersInCognito() {
        var request = ListUsersRequest.builder()
                .userPoolId(userPool)
                .build();
        var response = cognitoClient.listUsers(request);
        return response.users();
    }
}
