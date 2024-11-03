package org.topicsswe.userservice.domain.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.topicsswe.userservice.domain.exceptions.NoUserFoundException;
import org.topicsswe.userservice.domain.objects.SiteUser;
import org.topicsswe.userservice.infrastructure.UserRepository;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public SiteUser getUser(String username) throws NoUserFoundException {
        return userRepository.findUserByUsername(username);
//                .orElseThrow(NoUserFoundException::new);
    }

    public boolean userAlreadyExists(String email) {
        return !userRepository.findByEmail(email).isEmpty();
    }

    public SiteUser addUserToDatabase(SiteUser siteUser) {
        return userRepository.saveAndFlush(siteUser);
    }
}
