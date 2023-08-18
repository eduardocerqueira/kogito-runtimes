package org.jbpm.process.builder;

import org.drools.compiler.rule.builder.PackageBuildContext;
import org.drools.drl.ast.descr.ReturnValueDescr;
import org.jbpm.process.core.ContextResolver;
import org.jbpm.process.instance.impl.ReturnValueConstraintEvaluator;

public interface ReturnValueEvaluatorBuilder {

    void build(PackageBuildContext context,
            ReturnValueConstraintEvaluator evaluator,
            ReturnValueDescr returnValueDescr,
            ContextResolver contextResolver);

}
