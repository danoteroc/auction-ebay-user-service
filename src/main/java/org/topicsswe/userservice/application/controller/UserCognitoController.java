package org.topicsswe.userservice.application.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.topicsswe.userservice.application.DTO.UserDTO;
import org.topicsswe.userservice.application.transformers.UserTransformer;
import org.topicsswe.userservice.domain.service.UserCognitoService;

@RestController
@AllArgsConstructor
public class UserCognitoController {

    public UserCognitoService userService;

    @PostMapping("/register")
    public String register(@RequestBody UserDTO user) {
        return userService.register(UserTransformer.userDTOtoUser(user));
    }

}
