package org.kie.kogito.incubation.processes.services;

import org.kie.kogito.incubation.common.DataContext;
import org.kie.kogito.incubation.common.Id;

public interface StraightThroughProcessService {
    DataContext evaluate(Id processId, DataContext inputContext);
}
