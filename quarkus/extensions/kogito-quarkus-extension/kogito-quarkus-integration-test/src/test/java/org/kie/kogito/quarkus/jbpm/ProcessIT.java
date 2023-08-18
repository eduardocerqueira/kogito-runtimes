package org.kie.kogito.quarkus.jbpm;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import org.junit.jupiter.api.Test;
import org.kie.kogito.Model;
import org.kie.kogito.internal.process.runtime.KogitoProcessInstance;
import org.kie.kogito.process.Process;
import org.kie.kogito.process.ProcessInstance;

import io.quarkus.test.junit.QuarkusTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@QuarkusTest
public class ProcessIT {

    @Inject
    @Named("tests")
    Process<? extends Model> process;

    @Test
    public void testProcess() throws Exception {

        Model m = process.createModel();
        Map<String, Object> parameters = new HashMap<>();
        m.fromMap(parameters);

        ProcessInstance<?> processInstance = process.createInstance(m);
        processInstance.start();
        assertEquals(KogitoProcessInstance.STATE_COMPLETED, processInstance.status());
    }
}
