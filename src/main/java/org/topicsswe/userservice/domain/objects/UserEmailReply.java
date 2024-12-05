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
public class UserEmailReply {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String toUsername;
    private String fromAdminUsername;
    private Date timestamp;
    private String message;
    @OneToOne()
    @JoinColumn(name = "emailRepliedId", referencedColumnName = "id")
    private UserEmail repliedToEmail;

    public UserEmailReply(String toUsername, String fromAdminUsername,
                          String message, UserEmail repliedToEmail) {
        this.toUsername = toUsername;
        this.fromAdminUsername = fromAdminUsername;
        this.timestamp = Date.from(Instant.now());
        this.message = message;
        this.repliedToEmail = repliedToEmail;
    }
}
