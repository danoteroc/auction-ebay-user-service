package org.topicsswe.userservice.application.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.topicsswe.userservice.domain.service.UserCognitoService;

@RestController
@AllArgsConstructor
public class UserCognitoController {

    public UserCognitoService userService;

//    @PostMapping("/register")
//    public String register(@RequestBody CognitoUserDTO user) {
//        return userService.register(user.toUser());
//    }

//    @PostMapping("/login")
//    public LoginResponse login(@RequestBody LoginDTO dto) {
//        return userService.login(dto.username(), dto.password());
//    }

    @PutMapping("/admin/privileges/{username}")
    public String addPrivilegesToUser(@PathVariable String username) {
        return userService.addPrivileges(username);
    }

    @PutMapping("/admin/suspend/{username}")
    public String suspendUser(@PathVariable String username) {
        return userService.suspendUser(username);
    }

//    @GetMapping("/user/cognito")
//    public CognitoUserDTO getUserInformation(@RequestParam String accessToken) {
//        return CognitoUserDTO.toUserDTO(userService.getUserInfo(accessToken));
//    }
}
