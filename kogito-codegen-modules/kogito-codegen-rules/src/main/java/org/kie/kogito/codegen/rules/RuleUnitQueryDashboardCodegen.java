package org.kie.kogito.codegen.rules;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.drools.codegen.common.GeneratedFile;
import org.kie.kogito.KogitoGAV;
import org.kie.kogito.codegen.api.context.KogitoBuildContext;
import org.kie.kogito.codegen.core.DashboardGeneratedFileUtils;
import org.kie.kogito.grafana.GrafanaConfigurationWriter;

public class RuleUnitQueryDashboardCodegen {

    private static final String operationalDashboardDrlTemplate = "/grafana-dashboard-template/operational-dashboard-template.json";

    private final KogitoBuildContext context;
    private final Collection<QueryEndpointGenerator> validQueries;

    public RuleUnitQueryDashboardCodegen(KogitoBuildContext context, Collection<QueryEndpointGenerator> validQueries) {
        this.context = context;
        this.validQueries = validQueries;
    }

    Collection<GeneratedFile> generate() {
        List<GeneratedFile> generatedFiles = new ArrayList<>();

        for (QueryEndpointGenerator queryEndpoint : validQueries) {
            generatedFiles.addAll(generateQueryDashboard(queryEndpoint));
        }

        return generatedFiles;
    }

    private List<GeneratedFile> generateQueryDashboard(QueryEndpointGenerator query) {
        if (context.getAddonsConfig().usePrometheusMonitoring()) {
            String dashboardName = GrafanaConfigurationWriter.buildDashboardName(context.getGAV(), query.getEndpointName());
            Optional<String> operationalDashboard = GrafanaConfigurationWriter.generateOperationalDashboard(
                    operationalDashboardDrlTemplate,
                    query.getEndpointName(),
                    context.getPropertiesMap(),
                    query.getEndpointName(),
                    context.getGAV().orElse(KogitoGAV.EMPTY_GAV),
                    context.getAddonsConfig().useTracing());
            return operationalDashboard.stream()
                    .flatMap(dashboard -> DashboardGeneratedFileUtils.operational(dashboard, dashboardName + ".json").stream())
                    .collect(Collectors.toUnmodifiableList());
        }
        return Collections.emptyList();
    }

}
