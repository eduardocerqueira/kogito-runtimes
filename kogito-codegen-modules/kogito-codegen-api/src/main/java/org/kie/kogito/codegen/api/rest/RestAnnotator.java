package org.kie.kogito.codegen.api.rest;

import java.util.Optional;

import com.github.javaparser.ast.nodeTypes.NodeWithAnnotations;

public interface RestAnnotator {
    <T extends NodeWithAnnotations<?>> boolean isRestAnnotated(T node);

    <T extends NodeWithAnnotations<?>> Optional<String> getEndpointValue(T node);
}
