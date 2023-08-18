package org.kie.kogito.jobs.service.api.utils;

import java.util.Arrays;
import java.util.List;

import org.kie.kogito.jobs.service.api.Job;
import org.kie.kogito.jobs.service.api.JobLookupId;
import org.kie.kogito.jobs.service.api.Recipient;
import org.kie.kogito.jobs.service.api.Schedule;
import org.kie.kogito.jobs.service.api.event.CreateJobEvent;
import org.kie.kogito.jobs.service.api.event.DeleteJobEvent;
import org.kie.kogito.jobs.service.api.event.JobCloudEvent;
import org.kie.kogito.jobs.service.api.recipient.http.HttpRecipient;
import org.kie.kogito.jobs.service.api.recipient.http.HttpRecipientBinaryPayloadData;
import org.kie.kogito.jobs.service.api.recipient.http.HttpRecipientJsonPayloadData;
import org.kie.kogito.jobs.service.api.recipient.http.HttpRecipientStringPayloadData;
import org.kie.kogito.jobs.service.api.recipient.sink.SinkRecipient;
import org.kie.kogito.jobs.service.api.recipient.sink.SinkRecipientBinaryPayloadData;
import org.kie.kogito.jobs.service.api.recipient.sink.SinkRecipientJsonPayloadData;
import org.kie.kogito.jobs.service.api.recipient.sink.serialization.ContentModeDeserializer;
import org.kie.kogito.jobs.service.api.recipient.sink.serialization.ContentModeSerializer;
import org.kie.kogito.jobs.service.api.schedule.cron.CronSchedule;
import org.kie.kogito.jobs.service.api.schedule.timer.TimerSchedule;
import org.kie.kogito.jobs.service.api.serlialization.SpecVersionDeserializer;
import org.kie.kogito.jobs.service.api.serlialization.SpecVersionSerializer;

public class ReflectionUtils {

    private ReflectionUtils() {
    }

    /**
     * Api classes that might be needed for quarkus native compilation.
     */
    public static List<Class<?>> apiReflectiveClasses() {
        return Arrays.asList(
                SpecVersionSerializer.class,
                SpecVersionDeserializer.class,
                Job.class,
                JobLookupId.class,
                Recipient.class,
                HttpRecipient.class,
                HttpRecipientStringPayloadData.class,
                HttpRecipientBinaryPayloadData.class,
                HttpRecipientJsonPayloadData.class,
                SinkRecipient.class,
                ContentModeSerializer.class,
                ContentModeDeserializer.class,
                SinkRecipientBinaryPayloadData.class,
                SinkRecipientJsonPayloadData.class,
                Schedule.class,
                TimerSchedule.class,
                CronSchedule.class,
                JobCloudEvent.class,
                CreateJobEvent.class,
                DeleteJobEvent.class);
    }
}
