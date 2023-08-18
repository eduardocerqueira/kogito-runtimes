package org.kie.kogito.quarkus.workflows;

import org.junit.jupiter.api.Test;
import org.kie.kogito.testcontainers.quarkus.KafkaQuarkusTestResource;

import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusIntegrationTest;

import static org.kie.kogito.quarkus.workflows.ExternalServiceMock.SUCCESSFUL_QUERY;
import static org.kie.kogito.test.utils.ProcessInstancesRESTTestUtils.assertProcessInstanceExists;
import static org.kie.kogito.test.utils.ProcessInstancesRESTTestUtils.assertProcessInstanceHasFinished;
import static org.kie.kogito.test.utils.ProcessInstancesRESTTestUtils.newProcessInstanceAndGetId;

@QuarkusIntegrationTest
@QuarkusTestResource(ExternalServiceMock.class)
@QuarkusTestResource(KafkaQuarkusTestResource.class)
class CallbackStateTimeoutsIT extends AbstractCallbackStateIT {

    private static final String CALLBACK_STATE_TIMEOUTS_SERVICE_URL = "/callback_state_timeouts";
    private static final String CALLBACK_STATE_TIMEOUTS_GET_BY_ID_URL = CALLBACK_STATE_TIMEOUTS_SERVICE_URL + "/{id}";
    private static final String CALLBACK_STATE_TIMEOUTS_EVENT_TYPE = "callback_state_timeouts_event_type";
    private static final String CALLBACK_STATE_TIMEOUTS_TOPIC = "callback_state_timeouts_event_type";

    @Test
    @SuppressWarnings("squid:S2699")
    void callbackStateTimeoutsSuccessful() throws Exception {
        executeCallbackStateSuccessfulPath(CALLBACK_STATE_TIMEOUTS_SERVICE_URL,
                CALLBACK_STATE_TIMEOUTS_GET_BY_ID_URL,
                ANSWER,
                CALLBACK_STATE_TIMEOUTS_EVENT_TYPE,
                CALLBACK_STATE_TIMEOUTS_TOPIC);
    }

    @Test
    void callbackStateTimeoutsExceeded() {
        // start a new process instance by sending a query and collect the process instance id.
        String processInput = buildProcessInput(SUCCESSFUL_QUERY);
        String processInstanceId = newProcessInstanceAndGetId(CALLBACK_STATE_TIMEOUTS_SERVICE_URL, processInput);
        // assert the process instance is there
        assertProcessInstanceExists(CALLBACK_STATE_TIMEOUTS_GET_BY_ID_URL, processInstanceId);
        // do nothing more and wait until eventTimeout is fired and the process instance finalizes.
        assertProcessInstanceHasFinished(CALLBACK_STATE_TIMEOUTS_GET_BY_ID_URL, processInstanceId, 1, 10);
    }

    @Test
    @SuppressWarnings("squid:S2699")
    void callbackStateWithError() throws Exception {
        executeCallbackStateWithErrorPath(CALLBACK_STATE_TIMEOUTS_SERVICE_URL, CALLBACK_STATE_TIMEOUTS_GET_BY_ID_URL);
    }
}
