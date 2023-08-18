package org.kie.kogito.quarkus.drools;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.drools.ruleunits.api.DataStore;
import org.drools.ruleunits.api.RuleUnitData;
import org.kie.kogito.incubation.common.ReferenceContext;

import io.quarkus.arc.Unremovable;

@ApplicationScoped
@Unremovable // temporary workaround to allow injecting RuleUnitInstance<AnotherService>
             // without injecting AnotherService directly (otherwise Quarkus ArC will ignore this)
             // KOGITO-6529 Quarkus extension should make RuleUnitData/DataContext @Unremovable
public class AnotherService implements RuleUnitData, ReferenceContext {
    @Inject
    DataStore<StringHolder> strings;
    @Inject
    DataStore<StringHolder> greetings;

    protected AnotherService() {
    }

    public AnotherService(DataStore<StringHolder> strings, DataStore<StringHolder> greetings) {
        this.strings = strings;
        this.greetings = greetings;
    }

    public DataStore<StringHolder> getStrings() {
        return strings;
    }

    public DataStore<StringHolder> getGreetings() {
        return greetings;
    }

}
