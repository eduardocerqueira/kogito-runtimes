package org.kie.kogito.services.jobs.impl;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kie.kogito.process.Process;
import org.kie.kogito.process.ProcessInstance;
import org.kie.kogito.process.ProcessInstances;
import org.kie.kogito.uow.UnitOfWork;
import org.kie.kogito.uow.UnitOfWorkManager;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class TriggerJobCommandTest {

    private static final String PROCESS_INSTANCE_ID = "PROCESS_INSTANCE_ID";
    private static final String JOB_ID = "JOB_ID";
    private static final String TIMER_ID = "TIMER_ID";
    private static final int LIMIT = 1;

    @Mock
    private UnitOfWorkManager unitOfWorkManager;

    @Mock
    private UnitOfWork unitOfWork;

    @Mock
    private Process<?> process;

    @Mock
    private ProcessInstances<?> instances;

    @Mock
    private ProcessInstance<?> processInstance;

    private TriggerJobCommand command;

    @BeforeEach
    void setUp() {
        command = new TriggerJobCommand(PROCESS_INSTANCE_ID, JOB_ID, TIMER_ID, LIMIT, process, unitOfWorkManager);
    }

    @Test
    void executeWhenProcessInstanceNotFound() {
        doReturn(unitOfWork).when(unitOfWorkManager).newUnitOfWork();
        doReturn(instances).when(process).instances();
        doReturn(Optional.empty()).when(instances).findById(PROCESS_INSTANCE_ID);
        assertThat(command.execute()).isFalse();
    }

    @Test
    void executeWhenProcessInstanceFound() {
        doReturn(unitOfWork).when(unitOfWorkManager).newUnitOfWork();
        doReturn(instances).when(process).instances();
        doReturn(Optional.of(processInstance)).when(instances).findById(PROCESS_INSTANCE_ID);
        assertThat(command.execute()).isTrue();
        verify(processInstance).send(any());
    }
}
