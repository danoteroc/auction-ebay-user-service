package org.topicsswe.userservice.domain.service;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.topicsswe.userservice.configuration.PasswordHasher;
import org.topicsswe.userservice.domain.exceptions.NoUserFoundException;
import org.topicsswe.userservice.domain.objects.SiteUser;
import org.topicsswe.userservice.infrastructure.UserRepository;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    private final PasswordHasher passwordHasher;

    public SiteUser getUser(String username) throws NoUserFoundException {
        return userRepository.findUserByUsername(username).orElseThrow(NoUserFoundException::new);
    }

    public boolean userAlreadyExists(String email) {
        return userRepository.findUserByEmail(email).isPresent();
    }

    public SiteUser addUserToDatabase(SiteUser siteUser) {
        siteUser.setPasswordHash(passwordHasher.passwordHashing(siteUser.getPasswordHash()).getResult());
        return userRepository.saveAndFlush(siteUser);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null; //TODO
    }
}
