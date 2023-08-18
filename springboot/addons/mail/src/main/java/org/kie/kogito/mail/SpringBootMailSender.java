package org.kie.kogito.mail;

import org.kie.kogito.event.process.UserTaskDeadlineDataEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class SpringBootMailSender {

    private static Logger logger = LoggerFactory.getLogger(SpringBootMailSender.class);

    @Autowired
    private JavaMailSender emailSender;

    @KafkaListener(id = "${kogito.addon.mail.group.id:mail}",
            topics = "${kogito.events.deadline.topic:kogito-deadline-events}")
    public void onMessage(UserTaskDeadlineDataEvent record) {
        MailInfo mailInfo = MailInfo.of(record.getData());
        logger.info("Sending e-mail {}", mailInfo);
        SimpleMailMessage message = new SimpleMailMessage();
        if (mailInfo.to() != null) {
            message.setTo(mailInfo.to());
        }
        if (mailInfo.from() != null) {
            message.setFrom(mailInfo.from());
        }
        if (mailInfo.replyTo() != null) {
            message.setReplyTo(mailInfo.replyTo());
        }
        if (mailInfo.subject() != null) {
            message.setSubject(mailInfo.subject());
        }
        if (mailInfo.body() != null) {
            message.setText(mailInfo.body());
        }
        try {
            emailSender.send(message);
        } catch (Exception ex) {
            logger.error("Error sending e-mail " + mailInfo, ex);
        }
    }
}
