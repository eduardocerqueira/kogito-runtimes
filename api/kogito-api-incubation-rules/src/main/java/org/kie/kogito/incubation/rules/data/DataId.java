package org.kie.kogito.incubation.rules.data;

import org.kie.kogito.incubation.common.LocalId;
import org.kie.kogito.incubation.common.LocalUriId;

public class DataId extends LocalUriId implements LocalId {

    public static final String PREFIX = "data";
    private final String dataId;

    public DataId(DataSourceId parent, String dataId) {
        super(parent.asLocalUri().append(PREFIX).append(dataId));
        this.dataId = dataId;
    }

    public DataSourceId dataSourceId() {
        return dataSourceId();
    }

    public String dataId() {
        return dataId;
    }
}
