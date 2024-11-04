package org.topicsswe.userservice.domain.objects;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
public class SiteUser {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String username;
    private String email;
    @JsonIgnore
    private String passwordHash;
    private String name;
    private Status status;
    private Date registrationDate;
    private boolean isBlocked;
}
