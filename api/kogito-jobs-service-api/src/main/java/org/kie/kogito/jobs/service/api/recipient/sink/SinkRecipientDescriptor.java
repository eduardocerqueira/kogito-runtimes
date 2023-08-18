package org.kie.kogito.jobs.service.api.recipient.sink;

import org.kie.kogito.jobs.service.api.RecipientDescriptor;

public class SinkRecipientDescriptor implements RecipientDescriptor<SinkRecipient> {

    public static final String NAME = "sink";

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public Class<SinkRecipient> getType() {
        return SinkRecipient.class;
    }
}
