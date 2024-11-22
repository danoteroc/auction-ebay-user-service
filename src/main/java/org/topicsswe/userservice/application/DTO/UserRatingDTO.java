package org.topicsswe.userservice.application.DTO;

import org.topicsswe.userservice.domain.objects.UserRating;

import java.time.Instant;
import java.util.Date;

public record UserRatingDTO(String forUser, String byUser, double rating) {

    public UserRating toUserRating() {
        return new UserRating(forUser, rating, Date.from(Instant.now()), byUser);
    }
}
