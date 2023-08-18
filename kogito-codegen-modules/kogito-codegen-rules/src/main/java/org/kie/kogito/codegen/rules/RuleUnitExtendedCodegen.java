package org.kie.kogito.codegen.rules;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.drools.codegen.common.GeneratedFile;
import org.kie.kogito.codegen.api.context.KogitoBuildContext;

public class RuleUnitExtendedCodegen {
    private final RuleUnitQueryDashboardCodegen dashboards;
    private final RuleUnitQueryEventCodegen events;
    private final RuleUnitQueryRestCodegen rest;
    private final RuleObjectMapperCodegen objectMapper;

    public RuleUnitExtendedCodegen(
            KogitoBuildContext context,
            Collection<QueryGenerator> validQueries) {
        this.rest = new RuleUnitQueryRestCodegen(validQueries);
        this.events = new RuleUnitQueryEventCodegen(context, validQueries);
        this.dashboards = new RuleUnitQueryDashboardCodegen(context, rest.endpointGenerators());
        this.objectMapper = new RuleObjectMapperCodegen(context);
    }

    Collection<GeneratedFile> generate() {
        List<GeneratedFile> generatedFiles = new ArrayList<>();

        generatedFiles.addAll(events.generate());
        generatedFiles.addAll(rest.generate());
        generatedFiles.addAll(dashboards.generate());
        generatedFiles.add(objectMapper.generate());

        return generatedFiles;
    }

}
