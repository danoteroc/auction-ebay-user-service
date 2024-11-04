package org.topicsswe.userservice.application.controller;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.topicsswe.userservice.application.DTO.UserDTO;
import org.topicsswe.userservice.application.transformers.UserTransformer;
import org.topicsswe.userservice.domain.objects.SiteUser;
import org.topicsswe.userservice.domain.service.UserService;

@RestController
@AllArgsConstructor
public class UserController {

    private final Logger log = LoggerFactory.getLogger(UserController.class);
    private UserService userService;

    @PostMapping("/user")
    public SiteUser addUser(@RequestBody UserDTO userDto) {
        if (!userService.userAlreadyExists(userDto.email())) {
            var user = UserTransformer.userDTOtoUser(userDto);
            return userService.addUserToDatabase(user);
        }
        return null;
    }

    @PostMapping("info/{id}")
    public void updateUserInfo(@RequestBody SiteUser siteUser) {
        if (userService.userAlreadyExists(siteUser.getEmail())) {
            userService.addUserToDatabase(siteUser);
        }
    }

    @GetMapping("user/{username}")
    public SiteUser getUser(@PathVariable String username) {
        log.info("CALLED get user");
        //TODO check
        var user = userService.getUser(username);
        log.info("User is " + user);
        return user;
    }

    @GetMapping("user/me")
    public SiteUser getCurrentUser() {
        return null;
    }
}
