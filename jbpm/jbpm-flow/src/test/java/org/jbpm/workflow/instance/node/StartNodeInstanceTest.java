package org.jbpm.workflow.instance.node;

import java.util.List;

import org.drools.core.common.InternalKnowledgeRuntime;
import org.jbpm.process.instance.ProcessInstance;
import org.jbpm.ruleflow.core.RuleFlowProcess;
import org.jbpm.ruleflow.instance.RuleFlowProcessInstance;
import org.jbpm.test.util.AbstractBaseTest;
import org.jbpm.workflow.core.Node;
import org.jbpm.workflow.core.impl.ConnectionImpl;
import org.jbpm.workflow.core.node.StartNode;
import org.jbpm.workflow.instance.impl.NodeInstanceFactoryRegistry;
import org.junit.jupiter.api.Test;
import org.kie.api.runtime.process.NodeInstance;
import org.kie.kogito.internal.process.runtime.KogitoProcessRuntime;
import org.slf4j.LoggerFactory;

import static org.assertj.core.api.Assertions.assertThat;

public class StartNodeInstanceTest extends AbstractBaseTest {

    public void addLogger() {
        logger = LoggerFactory.getLogger(this.getClass());
    }

    @Test
    public void testStartNode() {

        KogitoProcessRuntime kruntime = createKogitoProcessRuntime();
        MockNode mockNode = new MockNode();
        MockNodeInstanceFactory mockNodeFactory = new MockNodeInstanceFactory(new MockNodeInstance(mockNode));
        NodeInstanceFactoryRegistry.getInstance(kruntime.getKieRuntime().getEnvironment()).register(mockNode.getClass(), mockNodeFactory);

        RuleFlowProcess process = new RuleFlowProcess();

        StartNode startNode = new StartNode();
        startNode.setId(1);
        startNode.setName("start node");

        mockNode.setId(2);
        new ConnectionImpl(
                startNode, Node.CONNECTION_DEFAULT_TYPE,
                mockNode, Node.CONNECTION_DEFAULT_TYPE);

        process.addNode(startNode);
        process.addNode(mockNode);

        RuleFlowProcessInstance processInstance = new RuleFlowProcessInstance();
        processInstance.setProcess(process);
        processInstance.setKnowledgeRuntime((InternalKnowledgeRuntime) kruntime.getKieSession());

        assertThat(processInstance.getState()).isEqualTo(ProcessInstance.STATE_PENDING);
        processInstance.start();
        assertThat(processInstance.getState()).isEqualTo(ProcessInstance.STATE_ACTIVE);

        MockNodeInstance mockNodeInstance = mockNodeFactory.getMockNodeInstance();
        List<NodeInstance> triggeredBy =
                mockNodeInstance.getTriggers().get(Node.CONNECTION_DEFAULT_TYPE);
        assertThat(triggeredBy).isNotNull().hasSize(1);
        assertThat(triggeredBy.get(0).getNodeId()).isSameAs(startNode.getId());
    }
}
