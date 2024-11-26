package org.topicsswe.userservice.domain.objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.Date;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class UserEmail {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String fromUsername;
    private Date timestamp;
    private String message;
    private boolean replied;

    public UserEmail(String fromUsername, String message) {
        this.fromUsername = fromUsername;
        this.timestamp = Date.from(Instant.now());
        this.message = message;
        this.replied = false;
    }
}
