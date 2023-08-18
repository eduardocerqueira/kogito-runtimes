package org.kie.kogito.incubation.common;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

/**
 * An empty DataContext singleton
 */
@JsonAutoDetect // ensure Jackson won't complain even if it is an empty object
public final class EmptyDataContext implements DataContext, DefaultCastable {
    public static final DataContext Instance = new EmptyDataContext();

    private EmptyDataContext() {
    }
}
