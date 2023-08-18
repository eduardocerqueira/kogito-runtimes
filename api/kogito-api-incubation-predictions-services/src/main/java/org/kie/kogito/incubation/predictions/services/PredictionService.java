package org.kie.kogito.incubation.predictions.services;

import org.kie.kogito.incubation.common.DataContext;
import org.kie.kogito.incubation.common.ExtendedDataContext;
import org.kie.kogito.incubation.common.LocalId;

public interface PredictionService {
    ExtendedDataContext evaluate(LocalId decisionId, DataContext inputContext);
}
