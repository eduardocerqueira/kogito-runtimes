package org.kie.kogito.incubation.rules.services;

import org.kie.kogito.incubation.common.DataContext;
import org.kie.kogito.incubation.common.LocalId;
import org.kie.kogito.incubation.rules.data.DataId;

/**
 * this may also act internally as a registry (?)
 */
public interface DataSourceService {
    /**
     * @param id identifier of the data source
     * @param ctx data that should be inserted into the data source
     * @return id of the inserted fact
     */
    // "/data-sources/my-data-source", val
    DataId add(LocalId id, DataContext ctx);

    // "/data-sources/7574598375943/data/7525792847584395"
    DataContext get(DataId id);

    void update(DataId id, DataContext ctx);

    void remove(DataId id);
}
