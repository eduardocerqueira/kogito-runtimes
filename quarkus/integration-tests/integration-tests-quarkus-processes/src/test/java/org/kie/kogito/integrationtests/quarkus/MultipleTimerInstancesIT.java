package org.kie.kogito.integrationtests.quarkus;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusIntegrationTest;

import static org.kie.kogito.test.utils.ProcessInstancesRESTTestUtils.assertProcessInstanceHasFinished;
import static org.kie.kogito.test.utils.ProcessInstancesRESTTestUtils.newProcessInstanceAndGetId;

@QuarkusIntegrationTest
class MultipleTimerInstancesIT {

    private static final String MULTIPLE_TIMER_INSTANCES_BOUNDARY_TIMER_EVENT = "/MultipleTimerInstancesBoundaryTimerEvent";
    private static final String MULTIPLE_TIMER_INSTANCES_BOUNDARY_TIMER_EVENT_URL_GET_BY_ID_URL = MULTIPLE_TIMER_INSTANCES_BOUNDARY_TIMER_EVENT + "/{id}";
    private static final String MULTIPLE_TIMER_INSTANCES_TIMER_EVENT = "/MultipleTimerInstancesTimerEvent";
    private static final String MULTIPLE_TIMER_INSTANCES_TIMER_EVENT_URL_GET_BY_ID_URL = MULTIPLE_TIMER_INSTANCES_TIMER_EVENT + "/{id}";
    private static final String EMPTY_DATA = "{}";
    private static final int AT_LEAST_SECONDS = 1;
    private static final int AT_MOST_SECONDS = 120;

    @Test
    void boundaryTimerEvent() {
        executeInstancesAndEnsureTermination(MULTIPLE_TIMER_INSTANCES_BOUNDARY_TIMER_EVENT, MULTIPLE_TIMER_INSTANCES_BOUNDARY_TIMER_EVENT_URL_GET_BY_ID_URL);
    }

    @Test
    void timerEvent() {
        executeInstancesAndEnsureTermination(MULTIPLE_TIMER_INSTANCES_TIMER_EVENT, MULTIPLE_TIMER_INSTANCES_TIMER_EVENT_URL_GET_BY_ID_URL);
    }

    private static void executeInstancesAndEnsureTermination(String processUrl, String processGetByIdQuery) {
        // Start simultaneous instances.
        String processInstanceId1 = newProcessInstanceAndGetId(processUrl, EMPTY_DATA);
        String processInstanceId2 = newProcessInstanceAndGetId(processUrl, EMPTY_DATA);
        String processInstanceId3 = newProcessInstanceAndGetId(processUrl, EMPTY_DATA);

        // All the instances must finish in a period of time, otherwise the issue is still present.
        assertProcessInstanceHasFinished(processGetByIdQuery, processInstanceId1, AT_LEAST_SECONDS, AT_MOST_SECONDS);
        assertProcessInstanceHasFinished(processGetByIdQuery, processInstanceId2, AT_LEAST_SECONDS, AT_MOST_SECONDS);
        assertProcessInstanceHasFinished(processGetByIdQuery, processInstanceId3, AT_LEAST_SECONDS, AT_MOST_SECONDS);
    }
}