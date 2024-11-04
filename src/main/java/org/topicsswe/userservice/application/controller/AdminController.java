package org.topicsswe.userservice.application.controller;

import org.springframework.web.bind.annotation.*;
import org.topicsswe.userservice.domain.service.AdminService;

@RestController("/admin")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping("/block/{email}")
    public void blockUser (@PathVariable String email) {
        adminService.blockUser(email);
    }

    @DeleteMapping("delete/{id}")
    public void deleteUser(@PathVariable String id) {
//        TODO delete user
    }

    @PostMapping("privileges/{id}")
    public void addAdminPrivilegesToUser(@PathVariable String id) {
//        TODO add admin privileges to user
    }

}
