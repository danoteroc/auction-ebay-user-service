package org.topicsswe.userservice.domain.service;

import org.springframework.stereotype.Service;
import org.topicsswe.userservice.domain.objects.SiteUser;
import org.topicsswe.userservice.infrastructure.UserRepository;

@Service
public class AdminService {

    private final UserRepository userRepository;

    public AdminService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Blocks a user given an email. This method assumes that the callee is an admin.
     * It also assumes the user given by the email exists
     * @param email email
     * @return true if the user was blocked
     */
    public boolean blockUser(String email) {
        SiteUser user = userRepository.findUserByEmail(email).get();
        user.setBlocked(true);
        userRepository.save(user);
        return true;
    }
}
