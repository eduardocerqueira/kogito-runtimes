package org.kie.kogito.codegen.rules;

import java.util.Collection;
import java.util.stream.Collectors;

import org.drools.codegen.common.GeneratedFile;

public class RuleUnitQueryRestCodegen {

    private final Collection<QueryEndpointGenerator> endpointGenerators;

    public RuleUnitQueryRestCodegen(Collection<QueryGenerator> validQueries) {
        this.endpointGenerators =
                validQueries.stream().map(QueryEndpointGenerator::new)
                        .collect(Collectors.toUnmodifiableList());
    }

    Collection<QueryEndpointGenerator> endpointGenerators() {
        return endpointGenerators;
    }

    Collection<GeneratedFile> generate() {
        return endpointGenerators.stream()
                .map(QueryEndpointGenerator::generate)
                .collect(Collectors.toUnmodifiableList());
    }

}
