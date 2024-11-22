package org.topicsswe.userservice.application.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/test")
    public String test() {
        return "Testing standard";
    }

    @GetMapping("/admin/test")
    public String testAdmin() {
        return "Testing admin";
    }
}
