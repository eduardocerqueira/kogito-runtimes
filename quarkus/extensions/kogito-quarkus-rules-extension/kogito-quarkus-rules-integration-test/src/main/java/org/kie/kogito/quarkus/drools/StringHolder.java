package org.kie.kogito.quarkus.drools;

import org.kie.kogito.incubation.common.DataContext;
import org.kie.kogito.incubation.common.DefaultCastable;

public class StringHolder implements DataContext, DefaultCastable {
    private String value;

    public StringHolder(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
