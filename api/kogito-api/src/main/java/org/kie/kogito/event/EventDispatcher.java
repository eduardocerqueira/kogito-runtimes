package org.kie.kogito.event;

import java.util.concurrent.CompletionStage;

import org.kie.kogito.Model;
import org.kie.kogito.process.ProcessInstance;

public interface EventDispatcher<M extends Model, D> {

    CompletionStage<ProcessInstance<M>> dispatch(String trigger, DataEvent<D> event);
}
