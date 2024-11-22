package org.topicsswe.userservice.application.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.topicsswe.userservice.application.DTO.UserRatingDTO;
import org.topicsswe.userservice.domain.objects.OverallUserRating;
import org.topicsswe.userservice.domain.service.UserRatingsService;

@RestController
@RequestMapping("/userRatings/")
@AllArgsConstructor
public class UserRatingsController {

    private final UserRatingsService userRatingsService;

    @GetMapping("/average/{username}")
    public OverallUserRating getUserRating(@PathVariable String username) {
        return userRatingsService.getUserRating(username);
    }

    @PostMapping("/add")
    public void addUserRating(@RequestBody UserRatingDTO userRating) {
        userRatingsService.addRatingToUser(userRating.toUserRating());
    }

}
