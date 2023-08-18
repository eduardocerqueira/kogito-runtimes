package org.kie.kogito.codegen.rules;

import java.util.*;
import java.util.stream.Collectors;

import org.drools.codegen.common.GeneratedFile;
import org.kie.kogito.KogitoGAV;
import org.kie.kogito.codegen.api.context.KogitoBuildContext;
import org.kie.kogito.codegen.core.DashboardGeneratedFileUtils;
import org.kie.kogito.grafana.GrafanaConfigurationWriter;

public class RuleUnitDashboardCodegen {

    private static final String domainDashboardDrlTemplate = "/grafana-dashboard-template/domain-dashboard-template.json";

    private final KogitoBuildContext context;
    private final Collection<RuleUnitGenerator> ruleUnitGenerators;

    public RuleUnitDashboardCodegen(KogitoBuildContext context, Collection<RuleUnitGenerator> ruleUnitGenerators) {
        this.context = context;
        this.ruleUnitGenerators = ruleUnitGenerators;
    }

    Collection<GeneratedFile> generate() {
        List<GeneratedFile> generatedFiles = new ArrayList<>();

        for (RuleUnitGenerator ruleUnit : ruleUnitGenerators) {
            generatedFiles.addAll(generateRuleUnitDashboard(ruleUnit));
        }

        return generatedFiles;
    }

    private List<GeneratedFile> generateRuleUnitDashboard(RuleUnitGenerator ruleUnit) {
        Optional<String> domainDashboard = GrafanaConfigurationWriter.generateDomainSpecificDrlDashboard(
                domainDashboardDrlTemplate,
                ruleUnit.typeName(),
                context.getPropertiesMap(),
                ruleUnit.typeName(),
                context.getGAV().orElse(KogitoGAV.EMPTY_GAV),
                context.getAddonsConfig().useTracing());
        String dashboardName = GrafanaConfigurationWriter.buildDashboardName(context.getGAV(), ruleUnit.typeName());
        return domainDashboard.stream()
                .flatMap(dashboard -> DashboardGeneratedFileUtils.domain(dashboard, dashboardName + ".json").stream())
                .collect(Collectors.toUnmodifiableList());
    }

}
