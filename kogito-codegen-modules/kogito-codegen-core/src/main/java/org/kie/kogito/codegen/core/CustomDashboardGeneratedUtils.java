package org.kie.kogito.codegen.core;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.drools.codegen.common.GeneratedFile;
import org.kie.api.io.Resource;
import org.kie.kogito.codegen.api.context.KogitoBuildContext;
import org.kie.kogito.codegen.api.io.CollectedResource;
import org.kie.kogito.codegen.core.io.CollectedResourceProducer;

import static org.kie.kogito.codegen.core.DashboardGeneratedFileUtils.DOMAIN_DASHBOARD_PREFIX;
import static org.kie.kogito.codegen.core.DashboardGeneratedFileUtils.OPERATIONAL_DASHBOARD_PREFIX;

public class CustomDashboardGeneratedUtils {

    private CustomDashboardGeneratedUtils() {
    }

    private static final Function<Resource, String> grouperFunction = resource -> {
        String fileName = resource.getSourcePath().substring(resource.getSourcePath().lastIndexOf(File.separator) + 1);
        if (fileName.startsWith(OPERATIONAL_DASHBOARD_PREFIX)) {
            return OPERATIONAL_DASHBOARD_PREFIX;
        } else if (fileName.startsWith(DOMAIN_DASHBOARD_PREFIX)) {
            return DOMAIN_DASHBOARD_PREFIX;
        } else {
            throw new IllegalStateException("Unexpected prefix!");
        }
    };

    static final BiFunction<String, String, List<GeneratedFile>> operationalFunction =
            DashboardGeneratedFileUtils::operational;

    static final BiFunction<String, String, List<GeneratedFile>> domainFunction =
            DashboardGeneratedFileUtils::domain;

    public static Collection<GeneratedFile> loadCustomGrafanaDashboardsList(final KogitoBuildContext context) {
        Collection<CollectedResource> collectedResources =
                CollectedResourceProducer.fromPaths(context.ignoreHiddenFiles(), context.getAppPaths().getPaths());
        Map<String, List<Resource>> dashboardJsonsMap = getMappedJsons(collectedResources);
        Collection<GeneratedFile> toReturn = new ArrayList<>();
        if (dashboardJsonsMap.get(OPERATIONAL_DASHBOARD_PREFIX) != null) {
            addToGeneratedFiles(dashboardJsonsMap.get(OPERATIONAL_DASHBOARD_PREFIX),
                    toReturn,
                    operationalFunction,
                    OPERATIONAL_DASHBOARD_PREFIX);
        }
        if (dashboardJsonsMap.get(DOMAIN_DASHBOARD_PREFIX) != null) {
            addToGeneratedFiles(dashboardJsonsMap.get(DOMAIN_DASHBOARD_PREFIX),
                    toReturn,
                    domainFunction,
                    DOMAIN_DASHBOARD_PREFIX);
        }
        return toReturn;
    }

    static void addToGeneratedFiles(final List<Resource> toAdd, final Collection<GeneratedFile> target,
            final BiFunction<String, String, List<GeneratedFile>> generator,
            final String dashboardPrefix) {
        toAdd.forEach(resource -> {
            try {
                String dashboard = new BufferedReader(
                        new InputStreamReader(resource.getInputStream(),
                                StandardCharsets.UTF_8))
                                        .lines()
                                        .collect(Collectors.joining("\n"));
                String dashboardName = resource.getSourcePath().substring(resource.getSourcePath().lastIndexOf(File.separator) + 1).substring(dashboardPrefix.length());
                target.addAll(generator.apply(dashboard, dashboardName));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    static Map<String, List<Resource>> getMappedJsons(final Collection<CollectedResource> collectedResources) {
        return collectedResources.stream()
                .filter(CustomDashboardGeneratedUtils::isValidResource)
                .map(CollectedResource::resource)
                .collect(Collectors.groupingBy(grouperFunction));
    }

    static boolean isValidResource(CollectedResource toVerify) {
        String sourcePath = toVerify.resource().getSourcePath();
        String fileName = sourcePath.substring(sourcePath.lastIndexOf(File.separator) + 1);
        return sourcePath.contains("META-INF" + File.separator + "dashboards") &&
                (fileName.startsWith(OPERATIONAL_DASHBOARD_PREFIX) || fileName.startsWith(DOMAIN_DASHBOARD_PREFIX)) &&
                fileName.endsWith(".json");
    }
}
