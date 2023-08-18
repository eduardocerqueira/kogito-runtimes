package org.kie.kogito.grafana.utils;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class GrafanaDashboardUtils {

    public static final String DISABLED_OPERATIONAL_DASHBOARDS = "kogito.grafana.disabled.operational.dashboards";
    public static final String DISABLED_DOMAIN_DASHBOARDS = "kogito.grafana.disabled.domain.dashboards";

    private GrafanaDashboardUtils() {
    }

    public static boolean isOperationDashboardEnabled(final Map<String, String> propertiesMap, final String toVerify) {
        return isDashboardEnabled(propertiesMap, DISABLED_OPERATIONAL_DASHBOARDS, toVerify);
    }

    public static boolean isDomainDashboardEnabled(final Map<String, String> propertiesMap, final String toVerify) {
        return isDashboardEnabled(propertiesMap, DISABLED_DOMAIN_DASHBOARDS, toVerify);

    }

    static boolean isDashboardEnabled(final Map<String, String> propertiesMap, final String dashboardProperty, final String toVerify) {
        return Optional.ofNullable(propertiesMap.get(dashboardProperty))
                .map(value -> !containsValue(value, toVerify)).orElse(true);
    }

    static boolean containsValue(String value, String toVerify) {
        List<String> items = Arrays.stream(value.split(","))
                .map(String::trim)
                .collect(Collectors.toList());
        return items.contains(toVerify.trim());
    }
}
