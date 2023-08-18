package org.jbpm.workflow.instance.node;

import org.drools.core.common.InternalKnowledgeRuntime;
import org.jbpm.process.instance.ProcessInstance;
import org.jbpm.ruleflow.instance.RuleFlowProcessInstance;
import org.jbpm.test.util.AbstractBaseTest;
import org.jbpm.workflow.core.Node;
import org.jbpm.workflow.core.impl.ConnectionImpl;
import org.jbpm.workflow.core.impl.WorkflowProcessImpl;
import org.jbpm.workflow.core.node.EndNode;
import org.jbpm.workflow.instance.impl.NodeInstanceFactoryRegistry;
import org.junit.jupiter.api.Test;
import org.kie.kogito.internal.process.runtime.KogitoProcessRuntime;
import org.slf4j.LoggerFactory;

import static org.assertj.core.api.Assertions.assertThat;

public class EndNodeInstanceTest extends AbstractBaseTest {

    public void addLogger() {
        logger = LoggerFactory.getLogger(this.getClass());
    }

    @Test
    public void testEndNode() {
        KogitoProcessRuntime kruntime = createKogitoProcessRuntime();
        MockNode mockNode = new MockNode();
        MockNodeInstanceFactory factory = new MockNodeInstanceFactory(new MockNodeInstance(mockNode));
        NodeInstanceFactoryRegistry.getInstance(kruntime.getKieRuntime().getEnvironment()).register(mockNode.getClass(), factory);

        WorkflowProcessImpl process = new WorkflowProcessImpl();
        Node endNode = new EndNode();
        endNode.setId(1);
        endNode.setName("end node");

        mockNode.setId(2);
        new ConnectionImpl(mockNode, Node.CONNECTION_DEFAULT_TYPE, endNode, Node.CONNECTION_DEFAULT_TYPE);

        process.addNode(mockNode);
        process.addNode(endNode);

        RuleFlowProcessInstance processInstance = new RuleFlowProcessInstance();
        processInstance.setId("1223");
        processInstance.setState(ProcessInstance.STATE_ACTIVE);
        processInstance.setProcess(process);
        processInstance.setKnowledgeRuntime((InternalKnowledgeRuntime) kruntime.getKieSession());

        MockNodeInstance mockNodeInstance = (MockNodeInstance) processInstance.getNodeInstance(mockNode);
        mockNodeInstance.triggerCompleted();
        assertThat(processInstance.getState()).isEqualTo(ProcessInstance.STATE_COMPLETED);
    }
}
