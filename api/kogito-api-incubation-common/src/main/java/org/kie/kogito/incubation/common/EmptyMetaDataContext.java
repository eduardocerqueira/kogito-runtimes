package org.kie.kogito.incubation.common;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

/**
 * An empty DataContext singleton
 */
@JsonAutoDetect // ensure Jackson won't complain even if it is an empty object
public final class EmptyMetaDataContext implements MetaDataContext, DefaultCastable {
    public static final MetaDataContext Instance = new EmptyMetaDataContext();

    private EmptyMetaDataContext() {
    }
}
