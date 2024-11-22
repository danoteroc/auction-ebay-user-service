package org.topicsswe.userservice.domain.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.topicsswe.userservice.domain.objects.OverallUserRating;
import org.topicsswe.userservice.domain.objects.UserRating;
import org.topicsswe.userservice.infrastructure.UserRatingsRepository;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserRatingsService {

    private final UserRatingsRepository userRatingsRepository;
    public OverallUserRating getUserRating(String username) {
        Optional<OverallUserRating> averageUserRating = userRatingsRepository.getAverageRating(username);
        return averageUserRating.orElseThrow(() -> new RuntimeException("No average found")); //TODO fix exception
    }

    public void addRatingToUser(UserRating rating) {
        userRatingsRepository.save(rating);
    }
}
