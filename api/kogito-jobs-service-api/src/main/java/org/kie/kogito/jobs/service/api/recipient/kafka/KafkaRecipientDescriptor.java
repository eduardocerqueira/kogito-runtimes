package org.kie.kogito.jobs.service.api.recipient.kafka;

import org.kie.kogito.jobs.service.api.RecipientDescriptor;

public class KafkaRecipientDescriptor implements RecipientDescriptor<KafkaRecipient> {

    public static final String NAME = "kafka";

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public Class<KafkaRecipient> getType() {
        return KafkaRecipient.class;
    }
}
