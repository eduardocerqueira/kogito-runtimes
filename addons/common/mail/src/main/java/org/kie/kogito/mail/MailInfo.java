package org.kie.kogito.mail;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;

import org.kie.kogito.event.process.UserTaskDeadlineEventBody;
import org.mvel2.templates.TemplateRuntime;

public class MailInfo {

    protected static final String TO_PROPERTY = "toemails";
    protected static final String FROM_PROPERTY = "from";
    protected static final String SUBJECT_PROPERTY = "subject";
    protected static final String REPLY_TO_PROPERTY = "replyTo";
    protected static final String BODY_PROPERTY = "body";

    private String[] to;
    private String from;
    private String subject;
    private String replyTo;
    private String body;

    public static MailInfo of(UserTaskDeadlineEventBody data) {
        Map<String, Object> info = data.getNotification();
        return new MailInfo(Optional.ofNullable((String) info.get(TO_PROPERTY)).map(s -> s.split(",")).orElse(null),
                (String) info.get(FROM_PROPERTY), evalTemplate((String) info.get(SUBJECT_PROPERTY), data),
                (String) info.get(REPLY_TO_PROPERTY), evalTemplate((String) info.get(BODY_PROPERTY), data));
    }

    private static String evalTemplate(String template, UserTaskDeadlineEventBody data) {
        return template != null ? TemplateRuntime.eval(template, data).toString() : null;
    }

    protected MailInfo(String[] to, String from, String subject, String replyTo, String body) {
        this.to = to;
        this.from = from;
        this.subject = subject;
        this.replyTo = replyTo;
        this.body = body;
    }

    public String[] to() {
        return to;
    }

    public String subject() {
        return subject;
    }

    public String from() {
        return from;
    }

    public String replyTo() {
        return replyTo;
    }

    public String body() {
        return body;
    }

    @Override
    public String toString() {
        return "MailInfo [to=" + Arrays.toString(to) + ", from=" + from + ", subject=" + subject + ", replyTo=" +
                replyTo + ", body=" + body + "]";
    }
}
