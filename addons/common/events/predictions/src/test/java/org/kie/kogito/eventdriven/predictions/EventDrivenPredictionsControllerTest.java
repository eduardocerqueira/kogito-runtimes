package org.kie.kogito.eventdriven.predictions;

import org.junit.jupiter.api.Test;
import org.kie.kogito.config.ConfigBean;
import org.kie.kogito.event.EventEmitter;
import org.kie.kogito.event.EventReceiver;
import org.kie.kogito.prediction.PredictionModels;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;

class EventDrivenPredictionsControllerTest {

    @Test
    void testSubscribe() {
        PredictionModels predictionModelsMock = mock(PredictionModels.class);
        ConfigBean configMock = mock(ConfigBean.class);
        EventEmitter eventEmitterMock = mock(EventEmitter.class);
        EventReceiver eventReceiverMock = mock(EventReceiver.class);

        // option #1: parameters via constructor + parameterless setup
        EventDrivenPredictionsController controller1 = new EventDrivenPredictionsController(predictionModelsMock, configMock, eventEmitterMock, eventReceiverMock);
        controller1.subscribe();
        verify(eventReceiverMock).subscribe(any(), any());

        reset(eventReceiverMock);

        // option #2: parameterless via constructor + parameters via setup (introduced for Quarkus CDI)
        EventDrivenPredictionsController controller2 = new EventDrivenPredictionsController();
        controller2.init(predictionModelsMock, configMock, eventEmitterMock, eventReceiverMock);
        controller2.subscribe();
        verify(eventReceiverMock).subscribe(any(), any());
    }

}
