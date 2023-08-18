package org.kie.kogito.event.cloudevents.utils;

import java.util.List;

import static io.cloudevents.core.v03.CloudEventV03.DATACONTENTENCODING;
import static io.cloudevents.core.v03.CloudEventV03.DATACONTENTTYPE;
import static io.cloudevents.core.v03.CloudEventV03.ID;
import static io.cloudevents.core.v03.CloudEventV03.SUBJECT;
import static io.cloudevents.core.v03.CloudEventV03.TIME;
import static io.cloudevents.core.v03.CloudEventV03.TYPE;

final class CloudEventValidatorV03 extends BaseCloudEventValidator {

    private static final CloudEventValidatorV03 instance = new CloudEventValidatorV03();

    private CloudEventValidatorV03() {
        super();
    }

    @Override
    protected String getRfc3339Attribute() {
        return TIME;
    }

    @Override
    protected String getRfc2046Attribute() {
        return DATACONTENTTYPE;
    }

    @Override
    protected List<String> getNonEmptyAttributes() {
        return List.of(ID, TYPE, DATACONTENTTYPE, DATACONTENTENCODING, SUBJECT);
    }

    static CloudEventValidatorV03 getInstance() {
        return instance;
    }
}
