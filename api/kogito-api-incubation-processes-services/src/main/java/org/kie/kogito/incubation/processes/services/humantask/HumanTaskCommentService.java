package org.kie.kogito.incubation.processes.services.humantask;

import org.kie.kogito.incubation.common.DataContext;
import org.kie.kogito.incubation.common.ExtendedDataContext;
import org.kie.kogito.incubation.common.LocalId;

public interface HumanTaskCommentService {
    ExtendedDataContext get(LocalId id);

    ExtendedDataContext add(LocalId taskId, DataContext dataContext);

    ExtendedDataContext update(LocalId commentId, DataContext dataContext);

    ExtendedDataContext delete(LocalId commentId, DataContext dataContext);
}
