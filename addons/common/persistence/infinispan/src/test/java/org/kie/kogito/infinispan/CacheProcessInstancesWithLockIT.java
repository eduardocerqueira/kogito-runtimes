package org.kie.kogito.infinispan;

import java.util.Collections;
import java.util.Date;

import org.drools.io.ClassPathResource;
import org.infinispan.client.hotrod.RemoteCacheManager;
import org.infinispan.client.hotrod.configuration.ConfigurationBuilder;
import org.jbpm.workflow.instance.WorkflowProcessInstance;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kie.kogito.process.ProcessInstance;
import org.kie.kogito.process.bpmn2.BpmnProcess;
import org.kie.kogito.process.bpmn2.BpmnVariables;
import org.kie.kogito.process.impl.AbstractProcessInstance;
import org.kie.kogito.testcontainers.KogitoInfinispanContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;
import static org.kie.kogito.test.utils.ProcessInstancesTestUtils.assertOne;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@Testcontainers
class CacheProcessInstancesWithLockIT {

    private static final String TEST_ID = "02ac3854-46ee-42b7-8b63-5186c9889d96";

    @Container
    public KogitoInfinispanContainer container = new KogitoInfinispanContainer();
    private RemoteCacheManager cacheManager;

    @BeforeEach
    void setup() {
        ConfigurationBuilder builder = new ConfigurationBuilder();
        builder
                .addServer()
                .host("127.0.0.1")
                .port(container.getMappedPort());

        cacheManager = new RemoteCacheManager(builder.build());
    }

    @AfterEach
    void close() {
        if (cacheManager != null) {
            cacheManager.close();
        }
    }

    private BpmnProcess createProcess(String fileName) {
        BpmnProcess process = BpmnProcess.from(new ClassPathResource(fileName)).get(0);
        AbstractProcessInstancesFactory factory = mock(AbstractProcessInstancesFactory.class);
        process.setProcessInstancesFactory(factory);
        process.configure();
        return process;
    }

    @Test
    public void testBasic() {
        BpmnProcess process = createProcess("BPMN2-UserTask.bpmn2");

        CacheProcessInstances pi = new CacheProcessInstances(process, cacheManager, null, true);
        assertThat(pi).isNotNull();

        WorkflowProcessInstance createPi = ((AbstractProcessInstance<?>) process.createInstance(BpmnVariables.create(Collections.singletonMap("test", "test")))).internalGetProcessInstance();
        createPi.setId(TEST_ID);
        createPi.setStartDate(new Date());

        AbstractProcessInstance<?> mockCreatePi = mock(AbstractProcessInstance.class);
        mockCreatePi.setVersion(1L);
        when(mockCreatePi.status()).thenReturn(ProcessInstance.STATE_ACTIVE);
        when(mockCreatePi.internalGetProcessInstance()).thenReturn(createPi);
        when(mockCreatePi.id()).thenReturn(TEST_ID);
        pi.create(TEST_ID, mockCreatePi);
        assertOne(pi);
        assertThat(pi.exists(TEST_ID)).isTrue();

        WorkflowProcessInstance updatePi = ((AbstractProcessInstance<?>) process.createInstance(BpmnVariables.create(Collections.singletonMap("test", "test")))).internalGetProcessInstance();
        updatePi.setId(TEST_ID);
        updatePi.setStartDate(new Date());
        AbstractProcessInstance<?> mockUpdatePi = mock(AbstractProcessInstance.class);
        when(mockUpdatePi.status()).thenReturn(ProcessInstance.STATE_ACTIVE);
        when(mockUpdatePi.internalGetProcessInstance()).thenReturn(updatePi);
        when(mockUpdatePi.id()).thenReturn(TEST_ID);

        try {
            pi.update(TEST_ID, mockUpdatePi);
            fail("Updating process should have failed");
        } catch (RuntimeException e) {
            assertThat(e.getMessage()).isEqualTo("Process instance with id '" + TEST_ID + "' updated or deleted by other request");
        }
        pi.remove(TEST_ID);
        assertThat(pi.exists(TEST_ID)).isFalse();
    }
}
