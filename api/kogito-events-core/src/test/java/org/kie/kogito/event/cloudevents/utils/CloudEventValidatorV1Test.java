package org.kie.kogito.event.cloudevents.utils;

import java.util.HashMap;
import java.util.Map;

import io.cloudevents.SpecVersion;

import static io.cloudevents.core.v1.CloudEventV1.DATACONTENTTYPE;
import static io.cloudevents.core.v1.CloudEventV1.ID;
import static io.cloudevents.core.v1.CloudEventV1.SOURCE;
import static io.cloudevents.core.v1.CloudEventV1.SPECVERSION;
import static io.cloudevents.core.v1.CloudEventV1.TIME;
import static io.cloudevents.core.v1.CloudEventV1.TYPE;
import static org.kie.kogito.event.cloudevents.utils.CloudEventUtils.DATA;

class CloudEventValidatorV1Test extends BaseCloudEventValidatorTest<CloudEventValidatorV1> {

    CloudEventValidatorV1Test() {
        super(CloudEventValidatorV1.getInstance(), SpecVersion.V1);
    }

    @Override
    protected Map<String, Object> createValidCloudEvent() {
        Map<String, Object> cloudEvent = new HashMap<>();
        cloudEvent.put(SPECVERSION, SpecVersion.V1.toString());
        cloudEvent.put(ID, "abc-123");
        cloudEvent.put(SOURCE, "/myapp");
        cloudEvent.put(TYPE, "com.example.someevent");
        cloudEvent.put(DATACONTENTTYPE, "application/json");
        cloudEvent.put(TIME, "2023-05-03T12:34:56Z");
        cloudEvent.put(DATA, "{\"foo\":\"bar\"}");
        return cloudEvent;
    }
}