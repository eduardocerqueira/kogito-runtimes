package org.kie.kogito.jobs.api.event.serialization;

import java.net.URI;
import java.time.ZonedDateTime;

class SerializationTestConstants {
    static final String ID = "ID";
    static final String SPEC_VERSION = "1.0";
    static final URI SOURCE = URI.create("http://localhost:8080/kogito-process");
    static final ZonedDateTime TIME = ZonedDateTime.parse("2021-11-24T18:01:15.001+01:00");
    static final String SUBJECT = "SUBJECT";
    static final String JOB_ID = "JOB_ID";
    static final String PROCESS_INSTANCE_ID_VALUE = "PROCESS_INSTANCE_ID";
    static final String PROCESS_ID_VALUE = "PROCESS_ID";
    static final String ROOT_PROCESS_INSTANCE_ID_VALUE = "ROOT_PROCESS_INSTANCE_ID";
    static final String ROOT_PROCESS_ID_VALUE = "ROOT_PROCESS_ID";
    static final String KOGITO_ADDONS_VALUE = "KOGITO_ADDONS";
    static final String NODE_INSTANCE_ID = "NODE_INSTANCE_ID";
    static final long REPEAT_INTERVAL = 6000;
    static final int REPEAT_LIMIT = 3;
    static final ZonedDateTime EXPIRATION_TIME = ZonedDateTime.parse("2021-11-30T22:01:15.001+01:00");
    static final int PRIORITY = 5;
    static final String CALLBACK_ENDPOINT = "http://localhost:8080/management/jobs/PROCESS_ID/instances/PROCESS_INSTANCE_ID/timers/NODE_INSTANCE_ID";
}
