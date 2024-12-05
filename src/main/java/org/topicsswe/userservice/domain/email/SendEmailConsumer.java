package org.topicsswe.userservice.domain.email;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

public interface SendEmailConsumer {

    public void sendEmail(String destinationEmail, String message);
}
