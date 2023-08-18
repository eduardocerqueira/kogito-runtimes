package org.jbpm.bpmn2.xpath;

import org.drools.compiler.compiler.DescrBuildError;
import org.drools.compiler.rule.builder.PackageBuildContext;
import org.drools.drl.ast.descr.ReturnValueDescr;
import org.jbpm.process.builder.ReturnValueEvaluatorBuilder;
import org.jbpm.process.core.ContextResolver;
import org.jbpm.process.instance.impl.ReturnValueConstraintEvaluator;
import org.jbpm.process.instance.impl.XPATHReturnValueEvaluator;

public class XPATHReturnValueEvaluatorBuilder
        implements
        ReturnValueEvaluatorBuilder {

    public XPATHReturnValueEvaluatorBuilder() {

    }

    public void build(final PackageBuildContext context,
            final ReturnValueConstraintEvaluator constraintNode,
            final ReturnValueDescr descr,
            final ContextResolver contextResolver) {

        String text = descr.getText();

        try {
            XPATHReturnValueEvaluator expr = new XPATHReturnValueEvaluator(text,
                    null);

            constraintNode.setEvaluator(expr);

        } catch (final Exception e) {
            context.getErrors().add(new DescrBuildError(context.getParentDescr(),
                    descr,
                    null,
                    "Unable to build expression for 'constraint' " + descr.getText() + "': " + e));
        }
    }

}
