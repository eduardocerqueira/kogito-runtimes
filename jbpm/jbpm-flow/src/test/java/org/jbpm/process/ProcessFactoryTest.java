package org.jbpm.process;

import org.jbpm.ruleflow.core.RuleFlowProcessFactory;
import org.jbpm.test.util.AbstractBaseTest;
import org.junit.jupiter.api.Test;
import org.slf4j.LoggerFactory;

public class ProcessFactoryTest extends AbstractBaseTest {

    public void addLogger() {
        logger = LoggerFactory.getLogger(this.getClass());
    }

    @Test
    public void testProcessFactory() throws Exception {
        RuleFlowProcessFactory factory = RuleFlowProcessFactory.createProcess("org.drools.core.process");
        factory
                // header
                .name("My process").packageName("org.drools")
                // nodes
                .startNode(1).name("Start").done()
                .actionNode(2).name("Action")
                .action("java",
                        "System.out.println(\"Action\");")
                .done()
                .endNode(3).name("End").done()
                // connections
                .connection(1,
                        2)
                .connection(2,
                        3);
        factory.validate().getProcess();
    }
}
