package org.jbpm.ruleflow.instance;

import org.drools.kiesession.session.ProcessRuntimeFactory;
import org.jbpm.process.instance.ProcessRuntimeFactoryServiceImpl;
import org.jbpm.ruleflow.core.RuleFlowProcess;
import org.jbpm.test.util.AbstractBaseTest;
import org.junit.jupiter.api.Test;
import org.kie.kogito.internal.process.runtime.KogitoProcessInstance;
import org.kie.kogito.internal.process.runtime.KogitoProcessRuntime;
import org.slf4j.LoggerFactory;

import static org.assertj.core.api.Assertions.*;

public class RuleFlowProcessInstanceTest extends AbstractBaseTest {

    private static String PROCESS_ID = "process.test";
    static {
        ProcessRuntimeFactory.setProcessRuntimeFactoryService(new ProcessRuntimeFactoryServiceImpl());
    }

    public void addLogger() {
        logger = LoggerFactory.getLogger(this.getClass());
    }

    @Test
    public void testStartProcessThrowException() throws Exception {
        RuleFlowProcess process = new RuleFlowProcess();
        process.setId(PROCESS_ID);
        process.setName("test");
        process.setPackageName("org.mycomp.myprocess");

        KogitoProcessRuntime kruntime = createKogitoProcessRuntime(process);
        assertThatThrownBy(() -> kruntime.startProcess(PROCESS_ID))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void testStartProcessDynamic() throws Exception {
        RuleFlowProcess process = new RuleFlowProcess();
        process.setId(PROCESS_ID);
        process.setName("test");
        process.setPackageName("org.mycomp.myprocess");
        process.setDynamic(true);

        KogitoProcessRuntime kruntime = createKogitoProcessRuntime(process);
        KogitoProcessInstance instance = kruntime.startProcess(PROCESS_ID);
        assertThat(instance).isNotNull();
    }

}
