package org.kie.kogito.jobs.service.api.event;

import org.kie.kogito.jobs.service.api.JobLookupId;

import static org.assertj.core.api.Assertions.assertThat;
import static org.kie.kogito.jobs.service.api.event.TestConstants.*;

class DeleteJobEventTest extends AbstractJobCloudEventTest<DeleteJobEvent> {

    @Override
    DeleteJobEvent buildEvent() {
        return DeleteJobEvent.builder()
                .id(ID)
                .source(SOURCE)
                .dataSchema(DATA_SCHEMA)
                .time(TIME)
                .subject(SUBJECT)
                .lookupId(JobLookupId.fromCorrelationId(CORRELATION_ID))
                .build();
    }

    @Override
    String eventType() {
        return DeleteJobEvent.TYPE;
    }

    @Override
    void assertFields(DeleteJobEvent event) {
        super.assertFields(event);
        JobLookupId lookupId = event.getData();
        assertThat(lookupId.getCorrelationId()).isNotNull();
        assertThat(lookupId.getCorrelationId()).isEqualTo(CORRELATION_ID);
    }
}
