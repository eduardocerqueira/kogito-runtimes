package org.kie.kogito.incubation.common;

/**
 * An identifier for a resource. It always contains a local part.
 */
public interface Id {
    LocalId toLocalId();
}
