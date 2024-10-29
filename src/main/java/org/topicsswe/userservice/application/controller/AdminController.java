package org.topicsswe.userservice.application.controller;

import org.springframework.web.bind.annotation.*;

@RestController("/admin")
public class AdminController {

    @PostMapping("/block/{id}")
    public void blockUser (@PathVariable String id) {
//        TODO block user
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
