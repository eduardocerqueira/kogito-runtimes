package org.jbpm.workflow.core.node;

import java.util.ArrayList;
import java.util.List;

import org.jbpm.process.core.context.variable.Variable;
import org.jbpm.process.core.datatype.impl.type.ObjectDataType;
import org.jbpm.process.test.TestProcessEventListener;
import org.jbpm.ruleflow.core.RuleFlowProcess;
import org.jbpm.test.util.AbstractBaseTest;
import org.jbpm.workflow.core.Node;
import org.junit.jupiter.api.Test;
import org.kie.kogito.internal.process.runtime.KogitoProcessInstance;
import org.kie.kogito.internal.process.runtime.KogitoProcessRuntime;
import org.slf4j.LoggerFactory;

import static org.assertj.core.api.Assertions.assertThat;

public class NodeInnerClassesTest extends AbstractBaseTest {

    @Override
    public void addLogger() {
        logger = LoggerFactory.getLogger(this.getClass());
    }

    @Test
    public void testNodeReading() {

        RuleFlowProcess process = new RuleFlowProcess();
        process.setId("org.drools.core.process.event");
        process.setName("Event Process");

        List<Variable> variables = new ArrayList<Variable>();
        Variable variable = new Variable();
        variable.setName("event");
        ObjectDataType personDataType = new ObjectDataType();
        personDataType.setClassName("org.drools.Person");
        variable.setType(personDataType);
        variables.add(variable);
        process.getVariableScope().setVariables(variables);

        process.setDynamic(true);
        CompositeNode compositeNode = new CompositeNode();
        compositeNode.setName("CompositeNode");
        compositeNode.setId(2);

        ForEachNode forEachNode = new ForEachNode();
        ForEachNode.ForEachSplitNode split = new ForEachNode.ForEachSplitNode();
        split.setName("ForEachSplit");
        split.setMetaData("hidden", true);
        split.setMetaData("UniqueId", forEachNode.getMetaData("Uniqueid") + ":foreach:split");
        forEachNode.internalAddNode(split);
        forEachNode.linkIncomingConnections(
                Node.CONNECTION_DEFAULT_TYPE,
                new CompositeNode.NodeAndType(split, Node.CONNECTION_DEFAULT_TYPE));

        process.addNode(forEachNode);
        KogitoProcessRuntime kruntime = createKogitoProcessRuntime(process);
        TestProcessEventListener procEventListener = new TestProcessEventListener();
        kruntime.getProcessEventManager().addEventListener(procEventListener);

        KogitoProcessInstance processInstance = kruntime.startProcess("org.drools.core.process.event");
        assertThat(processInstance).isNotNull();
    }

}
