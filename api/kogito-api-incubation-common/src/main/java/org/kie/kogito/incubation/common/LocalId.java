package org.kie.kogito.incubation.common;

/**
 * An identifier for a local resource
 */
public interface LocalId extends Id {
    LocalUri asLocalUri();
}
