package org.jbpm.process.builder;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import org.drools.base.definitions.InternalKnowledgePackage;
import org.drools.compiler.builder.impl.KnowledgeBuilderImpl;
import org.drools.compiler.compiler.PackageRegistry;
import org.drools.compiler.rule.builder.PackageBuildContext;
import org.drools.core.reteoo.CoreComponentFactory;
import org.drools.drl.ast.descr.ActionDescr;
import org.drools.mvel.MVELDialectRuntimeData;
import org.drools.mvel.builder.MVELDialect;
import org.jbpm.process.builder.dialect.mvel.MVELActionBuilder;
import org.jbpm.process.instance.KogitoProcessContextImpl;
import org.jbpm.process.instance.impl.Action;
import org.jbpm.process.instance.impl.MVELAction;
import org.jbpm.test.util.AbstractBaseTest;
import org.jbpm.workflow.core.DroolsAction;
import org.jbpm.workflow.core.impl.DroolsConsequenceAction;
import org.jbpm.workflow.core.node.ActionNode;
import org.junit.jupiter.api.Test;
import org.kie.internal.builder.KnowledgeBuilderFactory;
import org.kie.kogito.internal.process.runtime.KogitoProcessContext;
import org.kie.kogito.internal.process.runtime.KogitoProcessRuntime;

import static org.assertj.core.api.Assertions.assertThat;

public class MVELDecisionBuilderTest extends AbstractBaseTest {

    @Test
    public void testSimpleAction() throws Exception {
        final InternalKnowledgePackage pkg = CoreComponentFactory.get().createKnowledgePackage("pkg1");

        ActionDescr actionDescr = new ActionDescr();
        actionDescr.setText("list.add( 'hello world' )");

        builder = new KnowledgeBuilderImpl(pkg, KnowledgeBuilderFactory.newKnowledgeBuilderConfiguration());

        PackageRegistry pkgReg = builder.getPackageRegistry(pkg.getName());
        MVELDialect mvelDialect = (MVELDialect) pkgReg.getDialectCompiletimeRegistry().getDialect("mvel");

        PackageBuildContext context = new PackageBuildContext();
        context.initContext(builder, pkg, null, pkgReg.getDialectCompiletimeRegistry(), mvelDialect, null);

        builder.addPackageFromDrl(new StringReader("package pkg1;\nglobal java.util.List list;\n"));

        ActionNode actionNode = new ActionNode();
        DroolsAction action = new DroolsConsequenceAction("java", null);
        actionNode.setAction(action);

        final MVELActionBuilder actionBuilder = new MVELActionBuilder();
        actionBuilder.build(context,
                action,
                actionDescr,
                actionNode);

        KogitoProcessRuntime kruntime = createKogitoProcessRuntime();

        List<String> list = new ArrayList<String>();
        kruntime.getKieSession().setGlobal("list", list);

        MVELDialectRuntimeData data = (MVELDialectRuntimeData) builder.getPackage("pkg1").getDialectRuntimeRegistry().getDialectData("mvel");

        KogitoProcessContext processContext = new KogitoProcessContextImpl(kruntime.getKieSession());
        ((MVELAction) actionNode.getAction().getMetaData("Action")).compile(data);
        ((Action) actionNode.getAction().getMetaData("Action")).execute(processContext);

        assertThat(list.get(0)).isEqualTo("hello world");
    }

}
