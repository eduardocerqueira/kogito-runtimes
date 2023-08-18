package org.kie.kogito.jobs.service.api;

public interface Descriptor<T> {
    String getName();

    Class<T> getType();
}
