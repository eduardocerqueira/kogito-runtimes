package org.kie.kogito.mail;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kie.kogito.event.process.UserTaskDeadlineDataEvent;
import org.kie.kogito.event.process.UserTaskDeadlineEventBody;

import io.quarkus.mailer.Mail;
import io.quarkus.mailer.MockMailbox;
import io.quarkus.test.junit.QuarkusTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@QuarkusTest
public class QuarkusMailSenderTest {

    @Inject
    MockMailbox mailBox;

    @Inject
    QuarkusMailSender sender;

    @BeforeEach
    void init() {
        mailBox.clear();
    }

    private static final String TO = "javierito@doesnotexist.com";
    private static final String SUBJECT = "Here we are";
    private static final String TEXT = "For singing you a song";

    @Test
    void testMail() {
        Map<String, Object> notification = new HashMap<>();
        notification.put(MailInfo.SUBJECT_PROPERTY, SUBJECT);
        notification.put(MailInfo.BODY_PROPERTY, TEXT);
        notification.put(MailInfo.FROM_PROPERTY, "realbetisbalompie@gmail.com");
        notification.put(MailInfo.TO_PROPERTY, TO + ",fulanito@doesnotexist.com");

        UserTaskDeadlineEventBody eventData = UserTaskDeadlineEventBody.create("1", notification).build();
        UserTaskDeadlineDataEvent event = new UserTaskDeadlineDataEvent(null, null, null, null, eventData, null, null, null, null);
        sender.onDeadline(event);
        List<Mail> messages = mailBox.getMessagesSentTo(TO);
        assertEquals(1, messages.size());
        Mail message = messages.get(0);
        assertEquals(TEXT, message.getText());
        assertEquals(SUBJECT, message.getSubject());
    }
}
