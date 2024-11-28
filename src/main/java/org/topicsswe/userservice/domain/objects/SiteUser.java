package org.topicsswe.userservice.domain.objects;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SiteUser {
    @Id
    private String cognitoUserId;
    private String email;
    private String name;
    private String address;
    private String telephoneNumber;
    @JsonIgnore
    private boolean isBlocked;
    @Enumerated(value = EnumType.STRING)
    @JsonIgnore
    private Status status;
    private boolean isAdmin;

    public SiteUser(String cognitoUserId, String email, String name, String address, String telephoneNumber) {
        this.cognitoUserId = cognitoUserId;
        this.email = email;
        this.name = name;
        this.address = address;
        this.telephoneNumber = telephoneNumber;
        this.isBlocked = false;
        this.status = Status.STANDARD_ACTIVE;
        this.isAdmin = false;
    }

    public SiteUser(String cognitoUserId) {
        this.cognitoUserId = cognitoUserId;
        this.isBlocked = false;
        this.status = Status.STANDARD_ACTIVE;
        this.isAdmin = false;
    }
}
