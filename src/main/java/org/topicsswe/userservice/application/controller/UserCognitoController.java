package org.topicsswe.userservice.application.controller;

import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.topicsswe.userservice.application.DTO.LoginDTO;
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

    @GetMapping("/login")
    public String login(@RequestBody LoginDTO dto) {
        return userService.login(dto.username(), dto.password());
    }

    @GetMapping("/test")
    public String test() {
        return "Testing login";
    }

    @GetMapping("/admin/test")
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String testAdmin() {
        return "Testing login from Admin";
    }
}
