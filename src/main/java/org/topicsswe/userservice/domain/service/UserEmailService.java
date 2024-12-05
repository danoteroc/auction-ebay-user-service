package org.topicsswe.userservice.domain.service;

import lombok.AllArgsConstructor;
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

        email.setReplied(true);

        // Add to UserEmailReplyRepository
        var reply = new UserEmailReply(email.getFrom(), fromAdminUsername, message, email);
        replyRepository.save(reply);

        emailConsumer.sendEmail(email.getFrom(), message);
    }
}
