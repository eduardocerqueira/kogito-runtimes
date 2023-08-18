package org.kie.kogito.quarkus.workflows;

import org.junit.jupiter.api.Test;
import org.kie.kogito.testcontainers.quarkus.KafkaQuarkusTestResource;

import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusIntegrationTest;

@QuarkusIntegrationTest
@QuarkusTestResource(ExternalServiceMock.class)
@QuarkusTestResource(KafkaQuarkusTestResource.class)
class CallbackStateIT extends AbstractCallbackStateIT {

    private static final String CALLBACK_STATE_SERVICE_URL = "/callback_state";
    private static final String CALLBACK_STATE_SERVICE_GET_BY_ID_URL = CALLBACK_STATE_SERVICE_URL + "/{id}";
    private static final String CALLBACK_STATE_EVENT_TYPE = "callback_state_event_type";
    private static final String CALLBACK_STATE_EVENT_TOPIC = "callback_state_event_type";

    @Test
    @SuppressWarnings("squid:S2699")
    void callbackStateSuccessful() throws Exception {
        executeCallbackStateSuccessfulPath(CALLBACK_STATE_SERVICE_URL,
                CALLBACK_STATE_SERVICE_GET_BY_ID_URL,
                ANSWER,
                CALLBACK_STATE_EVENT_TYPE,
                CALLBACK_STATE_EVENT_TOPIC);
    }

    @Test
    @SuppressWarnings("squid:S2699")
    void callbackStateWithError() throws Exception {
        executeCallbackStateWithErrorPath(CALLBACK_STATE_SERVICE_URL, CALLBACK_STATE_SERVICE_GET_BY_ID_URL);
    }
}
