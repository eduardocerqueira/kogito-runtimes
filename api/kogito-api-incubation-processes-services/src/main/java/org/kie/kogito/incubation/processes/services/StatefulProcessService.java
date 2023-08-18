package org.kie.kogito.incubation.processes.services;

import org.kie.kogito.incubation.common.DataContext;
import org.kie.kogito.incubation.common.ExtendedDataContext;
import org.kie.kogito.incubation.common.LocalId;

public interface StatefulProcessService {
    ExtendedDataContext signal(LocalId processId, DataContext dataContext);

    ExtendedDataContext create(LocalId processInstanceId, DataContext dataContext);

    ExtendedDataContext update(LocalId processInstanceId, DataContext dataContext);

    ExtendedDataContext abort(LocalId processInstanceId);

    ExtendedDataContext get(LocalId processInstanceId);
}
