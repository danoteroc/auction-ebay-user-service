package org.topicsswe.userservice.application.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.topicsswe.userservice.application.DTO.SiteUserDTO;
import org.topicsswe.userservice.domain.objects.SiteUser;
import org.topicsswe.userservice.domain.service.SiteUserService;

import java.util.List;

@RestController
@AllArgsConstructor
public class UserInfoController {

    private final SiteUserService service;

    @GetMapping("/userInfo/{cognitoId}")
    public SiteUser getUserInfo(@PathVariable String cognitoId) {
        return service.getInfo(cognitoId);
    }

    @PutMapping("/userInfo")
    public SiteUser updateUserInfo(@RequestBody SiteUserDTO dto) {
        return service.updateInfo(dto.toSiteUser());
    }

    @GetMapping("/admin/userInfo")
    public List<SiteUser> getAllUsersInfo() {
        return service.getAllInfo();
    }
}
