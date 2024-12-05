package org.topicsswe.userservice.domain.email;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Configuration
public class SendEmailConsumerConfigurator {

    @Value("${notification-service-url}")
    private String notificationsServiceUrl;
    private final RestTemplate restTemplate;

    public SendEmailConsumerConfigurator() {
        this.notificationsServiceUrl = notificationsServiceUrl;
        restTemplate = new RestTemplate();
    }

    @Profile("local")
    @Bean
    public SendEmailConsumer sendMockEmailConsumer() {
        return (destinationEmail, subject, message) -> {

        };
    }

    @Profile("dev | docker")
    @Bean
    public SendEmailConsumer sendDockerEmailConsumer() {
        return (destinationEmail, subject, message) -> restTemplate.postForEntity(
                notificationsServiceUrl + "/sendEmail",
                new SendEmail(
                        List.of(destinationEmail),
                        subject,
                        message
                ),
                String.class
        );
    }

}
