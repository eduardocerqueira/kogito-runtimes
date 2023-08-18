package org.kie.kogito.jobs.service.api;

import java.util.LinkedHashSet;
import java.util.ServiceLoader;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractDescriptorRegistry<T extends Descriptor> {

    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractDescriptorRegistry.class);
    protected final LinkedHashSet<T> descriptors = new LinkedHashSet<>();
    protected final Class<T> clazz;

    protected AbstractDescriptorRegistry(Class<T> clazz) {
        this.clazz = clazz;
        loadDescriptors();
    }

    public Set<T> getDescriptors() {
        return descriptors;
    }

    public java.util.Optional<T> getDescriptor(Recipient<?> recipient) {
        return getDescriptors().stream()
                .filter(descr -> descr.getType().isInstance(recipient))
                .findFirst();
    }

    protected void loadDescriptors() {
        LOGGER.debug("Loading recipient descriptor registry");
        final ServiceLoader<T> loader = ServiceLoader.load(clazz);
        loader.iterator().forEachRemaining(descriptor -> {
            LOGGER.debug("adding -> ({}) to registry", descriptor);
            descriptors.add(descriptor);
        });
        LOGGER.debug("total descriptors: {}", descriptors.size());
    }
}
