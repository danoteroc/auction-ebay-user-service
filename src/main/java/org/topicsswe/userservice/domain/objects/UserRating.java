package org.topicsswe.userservice.domain.objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@NoArgsConstructor
public class UserRating {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String username;
    private double ratingValue;
    private Date ratingDate;
    private String ratedByUsername;

    public UserRating(String username, double ratingValue, Date ratingDate, String ratedByUsername) {
        this.username = username;
        this.ratingValue = ratingValue;
        this.ratingDate = ratingDate;
        this.ratedByUsername = ratedByUsername;
    }
}