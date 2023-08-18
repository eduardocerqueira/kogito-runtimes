package org.kie.kogito.incubation.rules.services;

import java.util.stream.Stream;

import org.kie.kogito.incubation.common.*;

public interface StatefulRuleUnitService {
    MetaDataContext create(LocalId ruleUnitId, ExtendedReferenceContext extendedDataContext);

    MetaDataContext dispose(LocalId ruleUnitInstanceId);

    MetaDataContext fire(LocalId ruleUnitInstanceId);

    /**
     * @param queryId identifier of the query
     * @param params params + meta-data for the query
     * @return a stream of results (data + meta)
     */
    Stream<ExtendedDataContext> query(LocalId queryId, ExtendedReferenceContext params);

}
