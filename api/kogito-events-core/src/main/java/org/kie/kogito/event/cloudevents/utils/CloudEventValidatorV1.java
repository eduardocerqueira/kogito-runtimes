package org.kie.kogito.event.cloudevents.utils;

import java.util.List;

import static io.cloudevents.core.v1.CloudEventV1.DATACONTENTTYPE;
import static io.cloudevents.core.v1.CloudEventV1.DATASCHEMA;
import static io.cloudevents.core.v1.CloudEventV1.ID;
import static io.cloudevents.core.v1.CloudEventV1.SOURCE;
import static io.cloudevents.core.v1.CloudEventV1.SUBJECT;
import static io.cloudevents.core.v1.CloudEventV1.TIME;
import static io.cloudevents.core.v1.CloudEventV1.TYPE;

final class CloudEventValidatorV1 extends BaseCloudEventValidator {

    private static final CloudEventValidatorV1 instance = new CloudEventValidatorV1();

    private CloudEventValidatorV1() {
        super();
    }

    @Override
    protected List<String> getNonEmptyAttributes() {
        return List.of(ID, SOURCE, TYPE, DATACONTENTTYPE, DATASCHEMA, SUBJECT);
    }

    @Override
    protected String getRfc3339Attribute() {
        return TIME;
    }

    @Override
    protected String getRfc2046Attribute() {
        return DATACONTENTTYPE;
    }

    static CloudEventValidatorV1 getInstance() {
        return instance;
    }
}
