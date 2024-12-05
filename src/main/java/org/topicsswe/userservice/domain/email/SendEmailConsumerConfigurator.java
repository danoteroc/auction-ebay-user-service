package org.topicsswe.userservice.domain.email;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Configuration
public class SendEmailConsumerConfigurator {

    private final RestTemplate restTemplate;

    public SendEmailConsumerConfigurator() {
        restTemplate = new RestTemplate();
    }

    @Profile("{local | dev}")
    @Bean
    public SendEmailConsumer sendMockEmailConsumer() {
        return (destinationEmail, message) -> {

        };
    }

    @Profile("{docker}")
    @Bean
    public SendEmailConsumer sendDockerEmailConsumer() {
        return (destinationEmail, subject, message) -> restTemplate.postForEntity(
                "api-gateway:42069/api/notifications/sendEmail", //TODO fill with correct URI
                new SendEmail(
                        List.of(destinationEmail),
                        subject,
                        message
                ),
                String.class
        );
    }

}
