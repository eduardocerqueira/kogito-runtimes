package org.kie.kogito.incubation.decisions.services;

import org.kie.kogito.incubation.common.DataContext;
import org.kie.kogito.incubation.common.ExtendedDataContext;
import org.kie.kogito.incubation.common.LocalId;

public interface DecisionService {
    ExtendedDataContext evaluate(LocalId decisionId, DataContext inputContext);
}
