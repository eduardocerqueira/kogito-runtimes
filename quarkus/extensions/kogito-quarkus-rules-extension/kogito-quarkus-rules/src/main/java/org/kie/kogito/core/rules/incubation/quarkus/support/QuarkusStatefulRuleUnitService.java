package org.kie.kogito.core.rules.incubation.quarkus.support;

import java.util.stream.Stream;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;

import org.kie.kogito.incubation.common.ExtendedDataContext;
import org.kie.kogito.incubation.common.ExtendedReferenceContext;
import org.kie.kogito.incubation.common.LocalId;
import org.kie.kogito.incubation.common.MetaDataContext;
import org.kie.kogito.incubation.rules.services.StatefulRuleUnitService;
import org.kie.kogito.rules.RuleUnits;

@ApplicationScoped
public class QuarkusStatefulRuleUnitService implements StatefulRuleUnitService {
    @Inject
    Instance<RuleUnits> ruleUnits;
    StatefulRuleUnitServiceImpl delegate;

    @PostConstruct
    void startup() {
        delegate = new StatefulRuleUnitServiceImpl(ruleUnits.get());
    }

    @Override
    public MetaDataContext create(LocalId localId, ExtendedReferenceContext extendedDataContext) {
        return delegate.create(localId, extendedDataContext);
    }

    @Override
    public MetaDataContext dispose(LocalId localId) {
        return delegate.dispose(localId);
    }

    @Override
    public MetaDataContext fire(LocalId localId) {
        return delegate.fire(localId);
    }

    @Override
    public Stream<ExtendedDataContext> query(LocalId localId, ExtendedReferenceContext params) {
        return delegate.query(localId, params);
    }
}
