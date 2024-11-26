package org.topicsswe.userservice.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import org.topicsswe.userservice.domain.objects.UserEmail;

import java.util.List;

public interface UserEmailRepository extends JpaRepository<UserEmail, Long> {

    List<UserEmail> findByRepliedFalse();

}
