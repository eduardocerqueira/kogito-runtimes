package org.kie.kogito.incubation.common.objectmapper;

public class MissingInternalObjectMapper extends RuntimeException {
    MissingInternalObjectMapper() {
        super("Cannot find a valid implementation for org.kie.kogito.incubation.common.objectmapper.InternalObjectMapper");
    }
}
