package org.kie.kogito.incubation.rules.services;

import java.util.stream.Stream;

import org.kie.kogito.incubation.common.DataContext;
import org.kie.kogito.incubation.common.Id;

public interface RuleUnitService {
    Stream<DataContext> evaluate(Id queryId, DataContext inputContext);
}
