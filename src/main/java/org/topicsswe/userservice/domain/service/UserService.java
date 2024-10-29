package org.topicsswe.userservice.domain.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.topicsswe.userservice.domain.exceptions.NoUserFoundException;
import org.topicsswe.userservice.domain.objects.User;
import org.topicsswe.userservice.infrastructure.UserRepository;

import java.util.UUID;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User getUser(String id) throws NoUserFoundException {
        return userRepository.findById(UUID.fromString(id)).orElseThrow(NoUserFoundException::new);
    }
}
