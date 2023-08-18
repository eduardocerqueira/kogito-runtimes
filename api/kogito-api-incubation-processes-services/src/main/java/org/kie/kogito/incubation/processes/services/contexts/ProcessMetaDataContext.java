package org.kie.kogito.incubation.processes.services.contexts;

import org.kie.kogito.incubation.common.DefaultCastable;
import org.kie.kogito.incubation.common.LocalId;
import org.kie.kogito.incubation.common.MetaDataContext;
import org.kie.kogito.incubation.processes.ProcessIdParser;

public class ProcessMetaDataContext implements DefaultCastable, MetaDataContext {
    private String id;

    private String businessKey;
    private String startFrom;

    protected ProcessMetaDataContext() {
    }

    public static ProcessMetaDataContext of(LocalId localId) {
        ProcessMetaDataContext mdc = new ProcessMetaDataContext();
        mdc.setId(localId);
        return mdc;
    }

    public <T extends LocalId> T id(Class<T> expected) {
        return ProcessIdParser.parse(id, expected);
    }

    String id() {
        return id;
    }

    public String businessKey() {
        return businessKey;
    }

    public String startFrom() {
        return startFrom;
    }

    void setId(String id) {
        this.id = id;
    }

    void setId(LocalId localId) {
        this.id = localId.asLocalUri().path();
    }

    void setBusinessKey(String businessKey) {
        this.businessKey = businessKey;
    }

    void setStartFrom(String startFrom) {
        this.startFrom = startFrom;
    }

    @Override
    public String toString() {
        return "ProcessMetaDataContext{" +
                "id='" + id + '\'' +
                '}';
    }
}