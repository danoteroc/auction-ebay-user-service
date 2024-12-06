package org.topicsswe.userservice.domain.email;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatusCode;

public interface SendEmailConsumer {

    public HttpStatusCode sendEmail(String destinationEmail, String subject, String message);
}
