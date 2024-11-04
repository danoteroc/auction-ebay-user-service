package org.topicsswe.userservice.infrastructure.cognito;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.cognitoidentityprovider.CognitoIdentityProviderClient;
import software.amazon.awssdk.services.cognitoidentityprovider.model.AttributeDataType;
import software.amazon.awssdk.services.cognitoidentityprovider.model.AttributeType;
import software.amazon.awssdk.services.cognitoidentityprovider.model.SignUpRequest;
import software.amazon.awssdk.services.cognitoidentityprovider.model.SignUpResponse;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Date;
import java.util.List;

// https://docs.aws.amazon.com/sdk-for-java/latest/developer-guide/java_cognito-identity-provider_code_examples.html
@Component
public class CognitoClient {

    private final CognitoIdentityProviderClient cognitoClient;
    @Value("${cognito.clientId}")
    private String clientId;
    @Value("${cognito.userPool}")
    private String userPool; // TODO add in .yaml

    public CognitoClient() {
        this.cognitoClient = CognitoIdentityProviderClient.builder()
                .region(Region.US_EAST_2)
                .credentialsProvider(ProfileCredentialsProvider.create()) //TODO check this portion
                .build();
    }

    public String authenticateUser(String username, String password) {
        return null; //TODO complete
    }

    public String registerUser(String username, String password, String name) {

        long unixTimestamp = System.currentTimeMillis() / 1000L;

        List<AttributeType> at = List.of(
//                Updated_at
                AttributeType.builder()
                        .name("updated_at")
                        .value(Long.toString(unixTimestamp))
                        .build(),
//        name
                AttributeType.builder()
                        .name("name")
                        .value(name)
                        .build(),
//                role
                AttributeType.builder()
                        .name("custom:role")
                        .value("STANDARD")
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
}
