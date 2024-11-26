package org.topicsswe.userservice.application.DTO;

public record UserEmailReplyDTO(long emailId, String message, String fromAdmin) {
}
