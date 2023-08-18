package org.kie.kogito.quarkus.processes.deployment;

import java.util.HashSet;
import java.util.Set;

import org.kie.kogito.quarkus.common.deployment.KogitoCompilationProvider;

import static java.util.Arrays.asList;

public class ProcessesCompilationProvider extends KogitoCompilationProvider {

    @Override
    public Set<String> handledExtensions() {
        return new HashSet<>(asList(".bpmn", ".bpmn2"));
    }
}
