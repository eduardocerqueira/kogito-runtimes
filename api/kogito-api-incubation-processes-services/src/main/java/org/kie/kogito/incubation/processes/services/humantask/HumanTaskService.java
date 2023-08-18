package org.kie.kogito.incubation.processes.services.humantask;

import org.kie.kogito.incubation.common.DataContext;
import org.kie.kogito.incubation.common.ExtendedDataContext;
import org.kie.kogito.incubation.common.LocalId;
import org.kie.kogito.incubation.common.MetaDataContext;

public interface HumanTaskService {
    ExtendedDataContext get(LocalId id, MetaDataContext meta);

    ExtendedDataContext create(LocalId taskId);

    ExtendedDataContext abort(LocalId taskId, MetaDataContext meta);

    ExtendedDataContext complete(LocalId processId, DataContext dataContext);

    ExtendedDataContext update(LocalId processId, DataContext dataContext);

    ExtendedDataContext transition(LocalId processId, DataContext dataContext);

}
