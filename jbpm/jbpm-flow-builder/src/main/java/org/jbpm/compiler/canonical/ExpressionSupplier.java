package org.jbpm.compiler.canonical;

import org.kie.kogito.internal.process.runtime.KogitoNode;

import com.github.javaparser.ast.expr.Expression;

public interface ExpressionSupplier {

    public Expression get(KogitoNode node, ProcessMetaData metadata);
}
