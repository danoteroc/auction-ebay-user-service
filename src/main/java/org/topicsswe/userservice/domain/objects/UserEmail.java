package org.topicsswe.userservice.domain.objects;

import jakarta.persistence.*;
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
    @Column(name = "id")
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
