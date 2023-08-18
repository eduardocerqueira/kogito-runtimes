package org.jbpm.process.builder;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import org.drools.base.definitions.InternalKnowledgePackage;
import org.drools.compiler.builder.impl.KnowledgeBuilderImpl;
import org.drools.compiler.compiler.DialectCompiletimeRegistry;
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

public class MVELActionBuilderTest extends AbstractBaseTest {

    @Test
    public void testSimpleAction() throws Exception {
        final InternalKnowledgePackage pkg = CoreComponentFactory.get().createKnowledgePackage("pkg1");

        ActionDescr actionDescr = new ActionDescr();
        actionDescr.setText("list.add( 'hello world' )");

        builder = new KnowledgeBuilderImpl(pkg, KnowledgeBuilderFactory.newKnowledgeBuilderConfiguration());
        DialectCompiletimeRegistry dialectRegistry = builder.getPackageRegistry(pkg.getName()).getDialectCompiletimeRegistry();
        MVELDialect mvelDialect = (MVELDialect) dialectRegistry.getDialect("mvel");

        PackageBuildContext context = new PackageBuildContext();
        context.initContext(builder, pkg, null, dialectRegistry, mvelDialect, null);

        builder.addPackageFromDrl(new StringReader("package pkg1;\nglobal java.util.List list;\n"));

        ActionNode actionNode = new ActionNode();
        DroolsAction action = new DroolsConsequenceAction("mvel", null);
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

        ((MVELAction) actionNode.getAction().getMetaData("Action")).compile(data);

        KogitoProcessContext processContext = new KogitoProcessContextImpl(kruntime.getKieRuntime());
        ((Action) actionNode.getAction().getMetaData("Action")).execute(processContext);

        assertThat(list.get(0)).isEqualTo("hello world");
    }

}
