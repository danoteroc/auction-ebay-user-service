package org.topicsswe.userservice.application.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.topicsswe.userservice.application.DTO.UserDTO;
import org.topicsswe.userservice.application.transformers.UserTransformer;
import org.topicsswe.userservice.domain.objects.User;
import org.topicsswe.userservice.domain.service.UserService;
import org.topicsswe.userservice.infrastructure.UserRepository;

import java.util.UUID;

@RestController
@AllArgsConstructor
public class UserController {

    private UserRepository userRepository;
    private UserService userService;

    @PostMapping("/user")
    public void addUser(@RequestBody UserDTO userDto) {
        User user = UserTransformer.userDTOtoUser(userDto);
        userRepository.saveAndFlush(user);
//        TODO check that the user is not registered
    }

    @PostMapping("info/{id}")
    public void updateUserInfo(@RequestBody User user) {
//        TODO check that the user exists
        userRepository.save(user);
    }

    @GetMapping("user/{id}")
    public User getUser(@PathVariable String id) {
        //TODO check
        return userService.getUser(id);

    }
}
