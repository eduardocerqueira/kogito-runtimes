package org.jbpm.workflow.instance.node;

import org.jbpm.test.util.AbstractBaseTest;
import org.jbpm.workflow.core.Node;
import org.jbpm.workflow.core.node.ActionNode;
import org.jbpm.workflow.instance.impl.NodeInstanceFactoryRegistry;
import org.junit.jupiter.api.Test;
import org.slf4j.LoggerFactory;

import static org.assertj.core.api.Assertions.assertThat;

public class ProcessNodeInstanceFactoryTest extends AbstractBaseTest {

    public void addLogger() {
        logger = LoggerFactory.getLogger(this.getClass());
    }

    @Test
    public void testDefaultEntries() throws Exception {
        Node node = new ActionNode();
        assertThat(NodeInstanceFactoryRegistry.getInstance(null).getProcessNodeInstanceFactory(node).getClass()).isNotNull();
    }

    @Test
    public void testDiscoveredEntry() {
        NodeInstanceFactoryRegistry.getInstance(null).register(MockNode.class, new MockNodeInstanceFactory(new MockNodeInstance(new MockNode())));
        assertThat(NodeInstanceFactoryRegistry.getInstance(null).getProcessNodeInstanceFactory(new MockNode()).getClass()).isEqualTo(MockNodeInstanceFactory.class);
    }
}
