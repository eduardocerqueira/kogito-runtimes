package org.kie.kogito.mongodb;

import org.junit.jupiter.api.Test;
import org.kie.kogito.mongodb.transaction.AbstractTransactionManager;
import org.kie.kogito.process.Process;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.mock;

class KogitoProcessInstancesFactoryIT extends TestHelper {

    @Test
    void test() {
        AbstractTransactionManager transactionManager = mock(AbstractTransactionManager.class);

        AbstractProcessInstancesFactory factory = new AbstractProcessInstancesFactory(getMongoClient(), DB_NAME, false, transactionManager) {
        };
        assertThat(factory).isNotNull();
        Process<?> process = mock(Process.class);
        lenient().when(process.id()).thenReturn(PROCESS_NAME);
        lenient().when(process.name()).thenReturn(PROCESS_NAME);
        MongoDBProcessInstances<?> instance = factory.createProcessInstances(process);
        assertThat(instance).isNotNull();
    }
}
