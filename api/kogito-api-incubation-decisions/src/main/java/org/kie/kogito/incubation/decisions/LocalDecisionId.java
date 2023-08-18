package org.kie.kogito.incubation.decisions;

import org.kie.kogito.incubation.common.Id;
import org.kie.kogito.incubation.common.LocalId;
import org.kie.kogito.incubation.common.LocalUri;
import org.kie.kogito.incubation.common.LocalUriId;

public class LocalDecisionId extends LocalUriId implements Id {
    public static final String PREFIX = "decisions";

    private final String namespace;
    private final String name;

    public LocalDecisionId(String namespace, String name) {
        super(makeLocalUri(namespace, name));
        this.namespace = namespace;
        this.name = name;
    }

    public String namespace() {
        return namespace;
    }

    public String name() {
        return name;
    }

    public DecisionServiceIds services() {
        return new DecisionServiceIds(this);
    }

    @Override
    public LocalId toLocalId() {
        return this;
    }

    private static LocalUri makeLocalUri(String namespace, String name) {
        String fullId = String.format("%s#%s", namespace, name);
        return LocalUri.Root.append(PREFIX).append(fullId);
    }

}
