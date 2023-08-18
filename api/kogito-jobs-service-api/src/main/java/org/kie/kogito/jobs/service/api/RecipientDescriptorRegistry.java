package org.kie.kogito.jobs.service.api;

public final class RecipientDescriptorRegistry extends AbstractDescriptorRegistry<RecipientDescriptor> {

    private static final RecipientDescriptorRegistry INSTANCE = new RecipientDescriptorRegistry();

    private RecipientDescriptorRegistry() {
        super(RecipientDescriptor.class);
    }

    public static RecipientDescriptorRegistry getInstance() {
        return INSTANCE;
    }
}
