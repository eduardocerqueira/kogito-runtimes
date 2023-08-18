package org.kie.kogito.eventdriven.rules;

import org.kie.kogito.event.DataEvent;

public interface EventDrivenQueryExecutor<D> {

    String getRuleUnitId();

    String getQueryName();

    Object executeQuery(DataEvent<D> input);
}
