package org.kie.kogito.jobs.service.api;

public abstract class PayloadData<T> implements HasData<T> {
    protected PayloadData() {
        // Marshalling constructor.
    }
}
