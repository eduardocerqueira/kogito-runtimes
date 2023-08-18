package org.kie.kogito.codegen.api.rest.impl;

import java.util.Optional;
import java.util.stream.Stream;

import org.kie.kogito.codegen.api.rest.RestAnnotator;

import com.github.javaparser.ast.expr.AnnotationExpr;
import com.github.javaparser.ast.nodeTypes.NodeWithAnnotations;

public class CDIRestAnnotator implements RestAnnotator {

    @Override
    public <T extends NodeWithAnnotations<?>> boolean isRestAnnotated(T node) {
        return Stream.of("POST", "GET", "PUT", "DELETE")
                .map(node::getAnnotationByName)
                .anyMatch(Optional::isPresent);
    }

    @Override
    public <T extends NodeWithAnnotations<?>> Optional<String> getEndpointValue(T node) {
        Optional<AnnotationExpr> path = node.getAnnotationByName("Path");
        return path.map(annotationExpr -> annotationExpr.asSingleMemberAnnotationExpr().getMemberValue().asStringLiteralExpr().asString());
    }
}
