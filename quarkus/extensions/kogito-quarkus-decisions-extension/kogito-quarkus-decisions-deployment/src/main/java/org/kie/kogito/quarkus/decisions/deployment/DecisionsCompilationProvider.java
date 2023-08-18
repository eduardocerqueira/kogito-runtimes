package org.kie.kogito.quarkus.decisions.deployment;

import java.util.Collections;
import java.util.Set;

import org.kie.kogito.quarkus.common.deployment.KogitoCompilationProvider;

public class DecisionsCompilationProvider extends KogitoCompilationProvider {

    @Override
    public Set<String> handledExtensions() {
        return Collections.singleton(".dmn");
    }
}
