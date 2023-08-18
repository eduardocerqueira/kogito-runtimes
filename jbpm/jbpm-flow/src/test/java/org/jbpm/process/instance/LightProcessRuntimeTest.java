package org.jbpm.process.instance;

import java.util.Collections;

import org.jbpm.ruleflow.core.RuleFlowProcess;
import org.jbpm.ruleflow.core.RuleFlowProcessFactory;
import org.junit.jupiter.api.Test;
import org.kie.kogito.Application;
import org.kie.kogito.process.Processes;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class LightProcessRuntimeTest {

    static class MyProcess {
        String result;
        RuleFlowProcess process = RuleFlowProcessFactory.createProcess("org.kie.api2.MyProcessUnit")
                // Header
                .name("HelloWorldProcess")
                .version("1.0")
                .packageName("org.jbpm")
                // Nodes
                .startNode(1).name("Start").done()
                .actionNode(2).name("Action")
                .action(ctx -> {
                    result = "Hello!";
                }).done()
                .endNode(3).name("End").done()
                // Connections
                .connection(1, 2)
                .connection(2, 3).validate().getProcess();
    }

    @Test
    void testInstantiation() {
        LightProcessRuntimeServiceProvider services =
                new LightProcessRuntimeServiceProvider();

        MyProcess myProcess = new MyProcess();
        LightProcessRuntimeContext rtc = new LightProcessRuntimeContext(Collections.singletonList(myProcess.process));

        Application application = mock(Application.class);
        when(application.get(Processes.class)).thenReturn(mock(Processes.class));
        LightProcessRuntime rt = new LightProcessRuntime(rtc, services, application);

        rt.startProcess(myProcess.process.getId());

        assertThat(myProcess.result).isEqualTo("Hello!");

    }

}
