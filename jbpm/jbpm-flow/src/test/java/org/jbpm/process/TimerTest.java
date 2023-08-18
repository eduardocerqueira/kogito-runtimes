package org.jbpm.process;

import org.drools.core.common.InternalWorkingMemory;
import org.drools.kiesession.session.ProcessRuntimeFactory;
import org.jbpm.process.instance.InternalProcessRuntime;
import org.jbpm.process.instance.ProcessRuntimeFactoryServiceImpl;
import org.jbpm.ruleflow.instance.RuleFlowProcessInstance;
import org.jbpm.test.util.AbstractBaseTest;
import org.junit.jupiter.api.Test;
import org.kie.kogito.internal.process.runtime.KogitoProcessRuntime;
import org.kie.kogito.jobs.DurationExpirationTime;
import org.kie.kogito.jobs.ExactExpirationTime;
import org.kie.kogito.jobs.JobsService;
import org.kie.kogito.jobs.ProcessInstanceJobDescription;
import org.kie.kogito.services.jobs.impl.LegacyInMemoryJobService;
import org.kie.kogito.services.uow.CollectingUnitOfWorkFactory;
import org.kie.kogito.services.uow.DefaultUnitOfWorkManager;
import org.kie.kogito.timer.TimerInstance;
import org.slf4j.LoggerFactory;

import static org.assertj.core.api.Assertions.assertThat;
import static org.jbpm.workflow.instance.node.TimerNodeInstance.TIMER_TRIGGERED_EVENT;

public class TimerTest extends AbstractBaseTest {

    public void addLogger() {
        logger = LoggerFactory.getLogger(this.getClass());
    }

    private int counter = 0;

    static {
        ProcessRuntimeFactory.setProcessRuntimeFactoryService(new ProcessRuntimeFactoryServiceImpl());
    }

    @Test
    public void testTimer() {
        KogitoProcessRuntime kruntime = createKogitoProcessRuntime();

        RuleFlowProcessInstance processInstance = new RuleFlowProcessInstance() {
            private static final long serialVersionUID = 510l;

            public void signalEvent(String type, Object event) {
                if (TIMER_TRIGGERED_EVENT.equals(type)) {
                    TimerInstance timer = (TimerInstance) event;
                    logger.info("Timer {} triggered", timer.getId());
                    counter++;
                }
            }
        };
        processInstance.setKnowledgeRuntime(((InternalWorkingMemory) kruntime.getKieSession()).getKnowledgeRuntime());
        processInstance.setId("1234");
        InternalProcessRuntime processRuntime = ((InternalProcessRuntime) ((InternalWorkingMemory) kruntime.getKieSession()).getProcessRuntime());
        processRuntime.getProcessInstanceManager().internalAddProcessInstance(processInstance);

        new Thread(() -> kruntime.getKieSession().fireUntilHalt()).start();
        JobsService jobService = new LegacyInMemoryJobService(kruntime, new DefaultUnitOfWorkManager(new CollectingUnitOfWorkFactory()));

        ProcessInstanceJobDescription desc = ProcessInstanceJobDescription.builder()
                .expirationTime(ExactExpirationTime.now())
                .processInstanceId(processInstance.getStringId())
                .processId("test")
                .id("job1")
                .timerId("timer1")
                .build();
        jobService.scheduleProcessInstanceJob(desc);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            // do nothing
        }
        assertThat(counter).isEqualTo(1);

        counter = 0;
        desc = ProcessInstanceJobDescription.builder()
                .expirationTime(DurationExpirationTime.after(500))
                .processInstanceId(processInstance.getStringId())
                .processId("test")
                .id("job2")
                .timerId("timer2")
                .build();
        jobService.scheduleProcessInstanceJob(desc);
        assertThat(counter).isZero();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            // do nothing
        }
        assertThat(counter).isEqualTo(1);

        counter = 0;
        desc = ProcessInstanceJobDescription.builder()
                .expirationTime(DurationExpirationTime.repeat(500, 300L))
                .processInstanceId(processInstance.getStringId())
                .processId("test")
                .id("job3")
                .timerId("timer3")
                .build();
        String jobId = jobService.scheduleProcessInstanceJob(desc);
        assertThat(counter).isZero();
        try {
            Thread.sleep(700);
        } catch (InterruptedException e) {
            // do nothing
        }
        assertThat(counter).isEqualTo(1);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            // do nothing
        }
        // we can't know exactly how many times this will fire as timers are not precise, but should be at least 4
        assertThat(counter >= 4).isTrue();

        jobService.cancelJob(jobId);
        int lastCount = counter;
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            // do nothing
        }
        assertThat(counter).isEqualTo(lastCount);
    }

}
