package org.kie.kogito.quarkus.rules.deployment;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.kie.kogito.quarkus.common.deployment.KogitoCompilationProvider;

public class RulesCompilationProvider extends KogitoCompilationProvider {

    private static final Set<String> MANAGED_EXTENSIONS = Collections.unmodifiableSet(new HashSet<>(Arrays.asList(".drl", ".xls", ".xlsx", ".csv")));

    @Override
    public Set<String> handledExtensions() {
        return MANAGED_EXTENSIONS;
    }
}
