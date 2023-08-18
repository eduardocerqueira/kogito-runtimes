package org.jbpm.process.builder;

import java.io.StringReader;

import org.drools.base.definitions.InternalKnowledgePackage;
import org.drools.compiler.builder.impl.KnowledgeBuilderImpl;
import org.drools.compiler.compiler.DialectCompiletimeRegistry;
import org.drools.compiler.rule.builder.PackageBuildContext;
import org.drools.core.common.InternalKnowledgeRuntime;
import org.drools.core.reteoo.CoreComponentFactory;
import org.drools.drl.ast.descr.ReturnValueDescr;
import org.drools.mvel.MVELDialectRuntimeData;
import org.drools.mvel.builder.MVELDialect;
import org.jbpm.process.builder.dialect.mvel.MVELReturnValueEvaluatorBuilder;
import org.jbpm.process.instance.impl.MVELReturnValueEvaluator;
import org.jbpm.process.instance.impl.ReturnValueConstraintEvaluator;
import org.jbpm.ruleflow.instance.RuleFlowProcessInstance;
import org.jbpm.test.util.AbstractBaseTest;
import org.jbpm.workflow.instance.node.SplitInstance;
import org.junit.jupiter.api.Test;
import org.kie.internal.builder.KnowledgeBuilderFactory;
import org.kie.kogito.internal.process.runtime.KogitoProcessRuntime;

import static org.assertj.core.api.Assertions.assertThat;

public class MVELReturnValueConstraintEvaluatorBuilderTest extends AbstractBaseTest {

    @Test
    public void testSimpleReturnValueConstraintEvaluator() throws Exception {
        final InternalKnowledgePackage pkg = CoreComponentFactory.get().createKnowledgePackage("pkg1");

        ReturnValueDescr descr = new ReturnValueDescr();
        descr.setText("return value");

        builder = new KnowledgeBuilderImpl(pkg, KnowledgeBuilderFactory.newKnowledgeBuilderConfiguration());
        DialectCompiletimeRegistry dialectRegistry = builder.getPackageRegistry(pkg.getName()).getDialectCompiletimeRegistry();
        MVELDialect mvelDialect = (MVELDialect) dialectRegistry.getDialect("mvel");

        PackageBuildContext context = new PackageBuildContext();
        context.initContext(builder,
                pkg,
                null,
                dialectRegistry,
                mvelDialect,
                null);

        builder.addPackageFromDrl(new StringReader("package pkg1;\nglobal Boolean value;"));

        ReturnValueConstraintEvaluator node = new ReturnValueConstraintEvaluator();

        final MVELReturnValueEvaluatorBuilder evaluatorBuilder = new MVELReturnValueEvaluatorBuilder();
        evaluatorBuilder.build(context,
                node,
                descr,
                null);

        KogitoProcessRuntime kruntime = createKogitoProcessRuntime();

        kruntime.getKieSession().setGlobal("value", true);

        RuleFlowProcessInstance processInstance = new RuleFlowProcessInstance();
        processInstance.setKnowledgeRuntime((InternalKnowledgeRuntime) kruntime.getKieSession());

        SplitInstance splitInstance = new SplitInstance();
        splitInstance.setProcessInstance(processInstance);

        MVELDialectRuntimeData data = (MVELDialectRuntimeData) builder.getPackage("pkg1").getDialectRuntimeRegistry().getDialectData("mvel");

        ((MVELReturnValueEvaluator) node.getReturnValueEvaluator()).compile(data);

        assertThat(node.evaluate(splitInstance,
                null,
                null)).isTrue();

        kruntime.getKieSession().setGlobal("value", false);

        assertThat(node.evaluate(splitInstance,
                null,
                null)).isFalse();
    }

}
