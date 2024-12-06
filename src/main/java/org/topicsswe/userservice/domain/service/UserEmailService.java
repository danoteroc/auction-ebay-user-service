package org.topicsswe.userservice.domain.service;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.topicsswe.userservice.domain.email.SendEmailConsumer;
import org.topicsswe.userservice.domain.exceptions.NoEmailFoundException;
import org.topicsswe.userservice.domain.objects.UserEmail;
import org.topicsswe.userservice.domain.objects.UserEmailReply;
import org.topicsswe.userservice.infrastructure.UserEmailReplyRepository;
import org.topicsswe.userservice.infrastructure.UserEmailRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class UserEmailService {

    private final Logger log = LoggerFactory.getLogger(UserEmailService.class);
    private final UserEmailRepository repository;
    private final UserEmailReplyRepository replyRepository;
    private final SendEmailConsumer emailConsumer;

    public void sendMessageToAdmin(UserEmail email) {
        repository.save(email);
    }

    public List<UserEmail> getAllMessagesToAdmin() {
        return repository.findByRepliedFalse();
    }

    public void replyTo(long emailId, String fromAdminUsername, String message) {

        // Find email in UserEmailRepository
        var optionalEmail = repository.findById(emailId);
        if (optionalEmail.isEmpty())
            throw new NoEmailFoundException(emailId);

        // Update UserEmailRepository
        var email = optionalEmail.get();

        // Check that there is no reply
        if (email.isReplied())
            return; // Nothing done because there is a reply already //TODO check if we should throw exception



        var statusCode = emailConsumer.sendEmail(
                email.getFromEmail(),
                "Reply to message from admin " + fromAdminUsername,
                message);

        if (statusCode.is2xxSuccessful()) {
            email.setReplied(true);

            // Add to UserEmailReplyRepository
            var reply = new UserEmailReply(email.getFromEmail(), fromAdminUsername, message, email);
            replyRepository.save(reply);
        } else {
            log.error("Failed to send message from admin {}", fromAdminUsername);
        }
    }
}
