package org.topicsswe.userservice.domain.objects;

public record LoginResponse(String idToken, String accessToken, String refreshToken)
{
}
