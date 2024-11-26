package org.topicsswe.userservice.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import org.topicsswe.userservice.domain.service.UserEmailReply;

public interface UserEmailReplyRepository extends JpaRepository<UserEmailReply, Long> {
}
