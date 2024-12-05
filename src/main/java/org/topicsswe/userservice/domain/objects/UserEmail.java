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
    private String from;
    private Date timestamp;
    private String message;
    private boolean replied;

    public UserEmail(String from, String message) {
        this.from = from;
        this.timestamp = Date.from(Instant.now());
        this.message = message;
        this.replied = false;
    }
}
