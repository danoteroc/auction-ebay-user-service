package org.topicsswe.userservice.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.topicsswe.userservice.domain.objects.OverallUserRating;
import org.topicsswe.userservice.domain.objects.UserRating;

import java.util.List;
import java.util.Optional;

public interface UserRatingsRepository extends JpaRepository<UserRating, Long> {

    List<UserRating> findAllByUsername(String username);

    @Query(value =
            """
            SELECT r.username as username, AVG(r.ratingValue) as averageRating
            FROM UserRating r
            WHERE r.username = ?1
            GROUP BY r.username
            """
    )
    Optional<OverallUserRating> getAverageRating(String username);
}
