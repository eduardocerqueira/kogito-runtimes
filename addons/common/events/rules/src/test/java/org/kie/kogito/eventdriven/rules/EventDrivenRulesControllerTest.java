package org.kie.kogito.eventdriven.rules;

import org.junit.jupiter.api.Test;
import org.kie.kogito.config.ConfigBean;
import org.kie.kogito.event.EventEmitter;
import org.kie.kogito.event.EventReceiver;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;

class EventDrivenRulesControllerTest {

    @Test
    void testSubscribe() {
        ConfigBean configMock = mock(ConfigBean.class);
        EventEmitter eventEmitterMock = mock(EventEmitter.class);
        EventReceiver eventReceiverMock = mock(EventReceiver.class);
        EventDrivenQueryExecutor queryExecutorMock = mock(EventDrivenQueryExecutor.class);

        // option #1: parameters via constructor + parameterless setup
        EventDrivenRulesController controller1 = new EventDrivenRulesController(configMock, eventEmitterMock, eventReceiverMock);
        controller1.subscribe(queryExecutorMock, Object.class);
        verify(eventReceiverMock).subscribe(any(), any());

        reset(eventReceiverMock);

        // option #2: parameterless via constructor + parameters via setup (introduced for Quarkus CDI)
        EventDrivenRulesController controller2 = new EventDrivenRulesController();
        controller1.subscribe(queryExecutorMock, Object.class);
        controller2.init(configMock, eventEmitterMock, eventReceiverMock);
        verify(eventReceiverMock).subscribe(any(), any());
    }

}
