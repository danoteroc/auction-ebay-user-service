package org.topicsswe.userservice.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.topicsswe.userservice.domain.objects.SiteUser;

import java.util.Optional;
import java.util.UUID;


@Repository
public interface UserRepository extends JpaRepository<SiteUser, UUID> {

    Optional<SiteUser> findUserByEmail(String email);

    Optional<SiteUser> findUserByUsername(String username);

}
