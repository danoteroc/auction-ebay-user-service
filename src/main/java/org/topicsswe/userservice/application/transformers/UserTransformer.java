package org.topicsswe.userservice.application.transformers;

import org.topicsswe.userservice.application.DTO.UserDTO;
import org.topicsswe.userservice.domain.objects.Role;
import org.topicsswe.userservice.domain.objects.User;

import java.time.Instant;
import java.util.Date;

public class UserTransformer {

    public static User userDTOtoUser(UserDTO dto) {
        User newUser = new User();
//        TODO extract hard coded admin value
        newUser.setRole(dto.role().equals("ADMIN") ? Role.ADMIN : Role.STANDARD);
        newUser.setRegistrationDate(Date.from(Instant.now()));
        newUser.setEmail(dto.email());

        return newUser;
    }
}
