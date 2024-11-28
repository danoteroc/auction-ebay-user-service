package org.topicsswe.userservice.application.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.topicsswe.userservice.domain.service.SiteUserService;
import org.topicsswe.userservice.domain.service.UserCognitoService;

@RestController
@AllArgsConstructor
public class UserCognitoController {

    public final UserCognitoService cognitoService;
    public final SiteUserService userManagementService;

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
        userManagementService.grantPrivileges(username);
        return cognitoService.addPrivileges(username);
    }

    @PutMapping("/admin/suspend/{userId}")
    public String suspendUser(@PathVariable String userId) {
        userManagementService.suspendUser(userId);
        return cognitoService.suspendUser(userId);
    }

//    @GetMapping("/user/cognito")
//    public CognitoUserDTO getUserInformation(@RequestParam String accessToken) {
//        return CognitoUserDTO.toUserDTO(userService.getUserInfo(accessToken));
//    }
}
