package org.kie.kogito.mail;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.kie.kogito.event.process.UserTaskDeadlineEventBody;

import static org.assertj.core.api.Assertions.assertThat;

public class MailInfoTest {

    @Test
    public void testMailInfo() {
        Map<String, Object> notification = new HashMap<>();
        notification.put(MailInfo.SUBJECT_PROPERTY, "${inputs.name}");
        notification.put(MailInfo.BODY_PROPERTY, "My name for process ${processInstanceId} is ${inputs.name}");
        notification.put(MailInfo.FROM_PROPERTY, "javierito");
        notification.put(MailInfo.TO_PROPERTY, "javierito@doesnotexist.com,fulanito@doesnotexist.com");
        notification.put(MailInfo.REPLY_TO_PROPERTY, "javierito@doesnotexist.com");
        MailInfo mailInfo = MailInfo.of(UserTaskDeadlineEventBody.create("1", notification).inputs(Collections
                .singletonMap("name", "Javierito")).processInstanceId("1").build());
        assertThat(mailInfo.subject()).isEqualTo("Javierito");
        assertThat(mailInfo.body()).isEqualTo("My name for process 1 is Javierito");
        assertThat(mailInfo.from()).isEqualTo("javierito");
        assertThat(mailInfo.replyTo()).isEqualTo("javierito@doesnotexist.com");
        assertThat(mailInfo.to()).containsExactly("javierito@doesnotexist.com", "fulanito@doesnotexist.com");
    }
}
