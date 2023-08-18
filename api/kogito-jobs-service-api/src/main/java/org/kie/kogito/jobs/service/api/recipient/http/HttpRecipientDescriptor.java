package org.kie.kogito.jobs.service.api.recipient.http;

import org.kie.kogito.jobs.service.api.RecipientDescriptor;

public class HttpRecipientDescriptor implements RecipientDescriptor<HttpRecipient> {

    public static final String NAME = "http";

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public Class<HttpRecipient> getType() {
        return HttpRecipient.class;
    }
}
