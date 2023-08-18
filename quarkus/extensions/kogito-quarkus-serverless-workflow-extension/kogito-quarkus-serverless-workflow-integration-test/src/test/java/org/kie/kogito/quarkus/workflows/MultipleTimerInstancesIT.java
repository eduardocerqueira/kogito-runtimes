package org.kie.kogito.quarkus.workflows;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusIntegrationTest;

import static org.kie.kogito.test.utils.ProcessInstancesRESTTestUtils.assertProcessInstanceHasFinished;
import static org.kie.kogito.test.utils.ProcessInstancesRESTTestUtils.newProcessInstanceAndGetId;

@QuarkusIntegrationTest
class MultipleTimerInstancesIT {

    private static final String MULTIPLE_TIMER_INSTANCES_EVENT_STATE_TIMEOUTS = "/multiple_timer_instances_event_state_timeouts";
    private static final String MULTIPLE_TIMER_EVENT_STATE_TIMEOUTS_GET_BY_ID_URL = MULTIPLE_TIMER_INSTANCES_EVENT_STATE_TIMEOUTS + "/{id}";
    private static final String EMPTY_WORKFLOW_DATA = "{\"workflowdata\" : \"\"}";
    private static final int AT_LEAST_SECONDS = 1;
    private static final int AT_MOST_SECONDS = 120;

    @Test
    void eventStateTimeouts() {
        // Start 3 simultaneous instances.
        String processInstanceId1 = newProcessInstanceAndGetId(MULTIPLE_TIMER_INSTANCES_EVENT_STATE_TIMEOUTS, EMPTY_WORKFLOW_DATA);
        String processInstanceId2 = newProcessInstanceAndGetId(MULTIPLE_TIMER_INSTANCES_EVENT_STATE_TIMEOUTS, EMPTY_WORKFLOW_DATA);
        String processInstanceId3 = newProcessInstanceAndGetId(MULTIPLE_TIMER_INSTANCES_EVENT_STATE_TIMEOUTS, EMPTY_WORKFLOW_DATA);

        // The three instances must finish in a period of time, otherwise the issue is still present.
        assertProcessInstanceHasFinished(MULTIPLE_TIMER_EVENT_STATE_TIMEOUTS_GET_BY_ID_URL, processInstanceId1, AT_LEAST_SECONDS, AT_MOST_SECONDS);
        assertProcessInstanceHasFinished(MULTIPLE_TIMER_EVENT_STATE_TIMEOUTS_GET_BY_ID_URL, processInstanceId2, AT_LEAST_SECONDS, AT_MOST_SECONDS);
        assertProcessInstanceHasFinished(MULTIPLE_TIMER_EVENT_STATE_TIMEOUTS_GET_BY_ID_URL, processInstanceId3, AT_LEAST_SECONDS, AT_MOST_SECONDS);
    }
}
