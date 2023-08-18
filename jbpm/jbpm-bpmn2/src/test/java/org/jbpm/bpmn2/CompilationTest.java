package org.jbpm.bpmn2;

import java.util.ArrayList;
import java.util.List;

import org.drools.compiler.compiler.AnalysisResult;
import org.drools.compiler.rule.builder.PackageBuildContext;
import org.drools.drl.ast.descr.ActionDescr;
import org.drools.drl.ast.descr.BaseDescr;
import org.drools.drl.ast.descr.ReturnValueDescr;
import org.jbpm.process.builder.ActionBuilder;
import org.jbpm.process.builder.ReturnValueEvaluatorBuilder;
import org.jbpm.process.builder.dialect.ProcessDialectRegistry;
import org.jbpm.process.builder.dialect.java.JavaActionBuilder;
import org.jbpm.process.builder.dialect.java.JavaProcessDialect;
import org.jbpm.process.builder.dialect.java.JavaReturnValueEvaluatorBuilder;
import org.jbpm.process.core.ContextResolver;
import org.jbpm.process.instance.impl.ReturnValueConstraintEvaluator;
import org.jbpm.workflow.core.DroolsAction;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CompilationTest extends JbpmBpmn2TestCase {

    @Test
    public void testReturnValueDescrCreation() throws Exception {
        TestJavaProcessDialect javaProcessDialect = new TestJavaProcessDialect();
        ProcessDialectRegistry.setDialect("java", javaProcessDialect);

        String filename = "BPMN2-GatewaySplit-SequenceConditions.bpmn2";
        kruntime = createKogitoProcessRuntime(filename);

        assertThat(javaProcessDialect.getActionDescrs()).as("No " + ActionDescr.class.getSimpleName() + " instances caught for testing!").isNotEmpty();
        for (BaseDescr descr : javaProcessDialect.getActionDescrs()) {
            assertThat(descr.getResource()).as(descr.getClass().getSimpleName() + " has a null resource field").isNotNull();
        }

        assertThat(javaProcessDialect.getReturnValueDescrs()).as("No " + ReturnValueDescr.class.getSimpleName() + " instances caught for testing!").isNotEmpty();
        for (BaseDescr descr : javaProcessDialect.getReturnValueDescrs()) {
            assertThat(descr.getResource()).as(descr.getClass().getSimpleName() + " has a null resource field").isNotNull();
        }
    }

    private static class TestJavaProcessDialect extends JavaProcessDialect {

        private ActionBuilder actionBuilder = new TestJavaActionBuilder();
        private ReturnValueEvaluatorBuilder returnValueEvaluatorBuilder = new TestJavaReturnValueEvaluatorBuilder();

        @Override
        public ActionBuilder getActionBuilder() {
            return actionBuilder;
        }

        @Override
        public ReturnValueEvaluatorBuilder getReturnValueEvaluatorBuilder() {
            return returnValueEvaluatorBuilder;
        }

        public List<ActionDescr> getActionDescrs() {
            return ((TestJavaActionBuilder) actionBuilder).actionDescrs;
        }

        public List<ReturnValueDescr> getReturnValueDescrs() {
            return ((TestJavaReturnValueEvaluatorBuilder) returnValueEvaluatorBuilder).returnValueDescrs;
        }
    }

    private static class TestJavaActionBuilder extends JavaActionBuilder {

        List<ActionDescr> actionDescrs = new ArrayList<ActionDescr>();

        @Override
        protected void buildAction(PackageBuildContext context, DroolsAction action, ActionDescr actionDescr,
                ContextResolver contextResolver, String className, AnalysisResult analysis) {
            actionDescrs.add(actionDescr);
            super.buildAction(context, action, actionDescr, contextResolver, className, analysis);
        }
    }

    private static class TestJavaReturnValueEvaluatorBuilder extends JavaReturnValueEvaluatorBuilder {

        List<ReturnValueDescr> returnValueDescrs = new ArrayList<ReturnValueDescr>();

        @Override
        protected void buildReturnValueEvaluator(PackageBuildContext context, ReturnValueConstraintEvaluator constraintNode,
                ReturnValueDescr descr, ContextResolver contextResolver, String className, AnalysisResult analysis) {
            returnValueDescrs.add(descr);
            super.buildReturnValueEvaluator(context, constraintNode, descr, contextResolver, className, analysis);
        }

    }

}
