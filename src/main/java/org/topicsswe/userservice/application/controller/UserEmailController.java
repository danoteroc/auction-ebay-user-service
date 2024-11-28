package org.topicsswe.userservice.application.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.topicsswe.userservice.application.DTO.UserEmailDTO;
import org.topicsswe.userservice.application.DTO.UserEmailReplyDTO;
import org.topicsswe.userservice.application.DTO.UserEmailResponseDTO;
import org.topicsswe.userservice.domain.service.UserEmailService;

import java.util.List;

@RestController("/emails")
@AllArgsConstructor
public class UserEmailController {

    public final UserEmailService service;

    @PostMapping("/sendMessage")
    public void sendMessage(@RequestBody UserEmailDTO message) {
        service.sendMessageToAdmin(message.toUserEmail());
    }

    @GetMapping("/admin/getMessages")
    public List<UserEmailResponseDTO> getMessages() {
        return service.getAllMessagesToAdmin().stream()
                .map(UserEmailResponseDTO::toUserEmailResponseDTO)
                .toList();
    }

    @PostMapping("/admin/replyTo")
    public void replyTo(@RequestBody UserEmailReplyDTO dto) {
        service.replyTo(dto.emailId(), dto.fromAdmin(), dto.message());
    }
}
