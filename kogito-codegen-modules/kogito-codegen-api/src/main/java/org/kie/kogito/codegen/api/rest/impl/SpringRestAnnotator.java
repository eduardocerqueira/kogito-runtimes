package org.kie.kogito.codegen.api.rest.impl;

import java.util.Optional;
import java.util.stream.Stream;

import org.kie.kogito.codegen.api.rest.RestAnnotator;

import com.github.javaparser.ast.expr.AnnotationExpr;
import com.github.javaparser.ast.nodeTypes.NodeWithAnnotations;

public class SpringRestAnnotator implements RestAnnotator {

    @Override
    public <T extends NodeWithAnnotations<?>> boolean isRestAnnotated(T node) {
        return Stream.of("PostMapping", "GetMapping", "PutMapping", "DeleteMapping")
                .map(node::getAnnotationByName)
                .anyMatch(Optional::isPresent);
    }

    @Override
    public <T extends NodeWithAnnotations<?>> Optional<String> getEndpointValue(T node) {
        Optional<AnnotationExpr> path = node.getAnnotationByName("PostMapping");
        return path
                .flatMap(p -> p.asNormalAnnotationExpr()
                        .getPairs()
                        .stream()
                        .filter(x -> "value".equals(x.getName().asString())).findFirst())
                .map(value -> value.getValue().asStringLiteralExpr().asString());
    }
}
