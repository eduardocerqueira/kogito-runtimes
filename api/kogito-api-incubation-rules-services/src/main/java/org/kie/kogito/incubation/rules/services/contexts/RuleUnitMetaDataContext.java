package org.kie.kogito.incubation.rules.services.contexts;

import org.kie.kogito.incubation.common.DefaultCastable;
import org.kie.kogito.incubation.common.LocalId;
import org.kie.kogito.incubation.common.MetaDataContext;
import org.kie.kogito.incubation.rules.RuleUnitIdParser;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RuleUnitMetaDataContext implements DefaultCastable, MetaDataContext {
    @JsonProperty
    private String id;

    protected RuleUnitMetaDataContext() {
    }

    public static RuleUnitMetaDataContext of(LocalId localId) {
        RuleUnitMetaDataContext mdc = new RuleUnitMetaDataContext();
        mdc.setId(localId);
        return mdc;
    }

    public <T extends LocalId> T id(Class<T> expected) {
        return RuleUnitIdParser.parse(id, expected);
    }

    String id() {
        return id;
    }

    void setId(String id) {
        this.id = id;
    }

    void setId(LocalId localId) {
        this.id = localId.asLocalUri().path();
    }

}
