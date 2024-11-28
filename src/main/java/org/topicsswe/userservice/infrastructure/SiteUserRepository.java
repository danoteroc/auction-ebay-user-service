package org.topicsswe.userservice.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import org.topicsswe.userservice.domain.objects.SiteUser;

import java.util.Optional;

public interface SiteUserRepository extends JpaRepository<SiteUser, Long> {

    Optional<SiteUser> findByCognitoUserId(String cognitoUserId);

}
