package org.kie.kogito.core.rules.incubation.quarkus.support;

import java.util.stream.Stream;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;

import org.drools.ruleunits.api.RuleUnits;
import org.kie.kogito.incubation.common.DataContext;
import org.kie.kogito.incubation.common.Id;
import org.kie.kogito.incubation.rules.services.RuleUnitService;

@ApplicationScoped
public class QuarkusRuleUnitService implements RuleUnitService {

    @Inject
    Instance<RuleUnits> ruleUnits;
    RuleUnitServiceImpl delegate;

    @PostConstruct
    void startup() {
        this.delegate = new RuleUnitServiceImpl(ruleUnits.get());
    }

    @Override
    public Stream<DataContext> evaluate(Id id, DataContext inputContext) {
        return this.delegate.evaluate(id, inputContext);
    }

}
