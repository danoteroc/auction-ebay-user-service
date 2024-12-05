package org.topicsswe.userservice.domain.email;

import java.util.List;

public record SendEmail(
        List<String> to,
        String subject,
        String content
) {
}
