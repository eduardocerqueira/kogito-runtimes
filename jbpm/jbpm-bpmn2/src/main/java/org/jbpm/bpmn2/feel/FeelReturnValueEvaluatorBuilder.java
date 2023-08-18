package org.jbpm.bpmn2.feel;

import org.drools.compiler.rule.builder.PackageBuildContext;
import org.drools.drl.ast.descr.ReturnValueDescr;
import org.jbpm.process.builder.ReturnValueEvaluatorBuilder;
import org.jbpm.process.core.ContextResolver;
import org.jbpm.process.instance.impl.ReturnValueConstraintEvaluator;

public class FeelReturnValueEvaluatorBuilder implements ReturnValueEvaluatorBuilder {

    public FeelReturnValueEvaluatorBuilder() {

    }

    @Override
    public void build(final PackageBuildContext context,
            final ReturnValueConstraintEvaluator constraintNode,
            final ReturnValueDescr descr,
            final ContextResolver contextResolver) {

        String text = descr.getText();
        FeelReturnValueEvaluator expr = new FeelReturnValueEvaluator(text);
        constraintNode.setEvaluator(expr);

    }

}
