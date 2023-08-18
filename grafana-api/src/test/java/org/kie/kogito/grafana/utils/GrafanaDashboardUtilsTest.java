package org.kie.kogito.grafana.utils;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.kie.kogito.grafana.utils.GrafanaDashboardUtils.DISABLED_DOMAIN_DASHBOARDS;
import static org.kie.kogito.grafana.utils.GrafanaDashboardUtils.DISABLED_OPERATIONAL_DASHBOARDS;

class GrafanaDashboardUtilsTest {

    @Test
    void isOperationDashboardEnabled() {
        Map<String, String> propertiesMap = new HashMap<>();
        assertThat(GrafanaDashboardUtils.isOperationDashboardEnabled(propertiesMap, "Loan")).isTrue();
        assertThat(GrafanaDashboardUtils.isOperationDashboardEnabled(propertiesMap, "Hello")).isTrue();
        propertiesMap.put(DISABLED_OPERATIONAL_DASHBOARDS, "");
        assertThat(GrafanaDashboardUtils.isOperationDashboardEnabled(propertiesMap, "Loan")).isTrue();
        assertThat(GrafanaDashboardUtils.isOperationDashboardEnabled(propertiesMap, "Hello")).isTrue();

        String values = "Hello";
        propertiesMap.put(DISABLED_OPERATIONAL_DASHBOARDS, values);
        assertThat(GrafanaDashboardUtils.isOperationDashboardEnabled(propertiesMap, "Loan")).isTrue();
        assertThat(GrafanaDashboardUtils.isOperationDashboardEnabled(propertiesMap, "Hello")).isFalse();

        values = "Hello,Loan";
        propertiesMap.put(DISABLED_OPERATIONAL_DASHBOARDS, values);
        assertThat(GrafanaDashboardUtils.isOperationDashboardEnabled(propertiesMap, "Traffic")).isTrue();
        assertThat(GrafanaDashboardUtils.isOperationDashboardEnabled(propertiesMap, "Loan")).isFalse();
        assertThat(GrafanaDashboardUtils.isOperationDashboardEnabled(propertiesMap, "Hello")).isFalse();

        values = " Hello, Loan ";
        propertiesMap.put(DISABLED_OPERATIONAL_DASHBOARDS, values);
        assertThat(GrafanaDashboardUtils.isOperationDashboardEnabled(propertiesMap, "Traffic")).isTrue();
        assertThat(GrafanaDashboardUtils.isOperationDashboardEnabled(propertiesMap, "Loan")).isFalse();
        assertThat(GrafanaDashboardUtils.isOperationDashboardEnabled(propertiesMap, "Hello")).isFalse();
    }

    @Test
    void isDomainDashboardEnabled() {
        Map<String, String> propertiesMap = new HashMap<>();

        assertThat(GrafanaDashboardUtils.isDomainDashboardEnabled(propertiesMap, "Loan")).isTrue();
        assertThat(GrafanaDashboardUtils.isDomainDashboardEnabled(propertiesMap, "Hello")).isTrue();
        propertiesMap.put(DISABLED_DOMAIN_DASHBOARDS, "");
        assertThat(GrafanaDashboardUtils.isDomainDashboardEnabled(propertiesMap, "Loan")).isTrue();
        assertThat(GrafanaDashboardUtils.isDomainDashboardEnabled(propertiesMap, "Hello")).isTrue();

        String values = "Hello";
        propertiesMap.put(DISABLED_DOMAIN_DASHBOARDS, values);
        assertThat(GrafanaDashboardUtils.isDomainDashboardEnabled(propertiesMap, "Loan")).isTrue();
        assertThat(GrafanaDashboardUtils.isDomainDashboardEnabled(propertiesMap, "Hello")).isFalse();

        values = "Hello,Loan";
        propertiesMap.put(DISABLED_DOMAIN_DASHBOARDS, values);
        assertThat(GrafanaDashboardUtils.isDomainDashboardEnabled(propertiesMap, "Traffic")).isTrue();
        assertThat(GrafanaDashboardUtils.isDomainDashboardEnabled(propertiesMap, "Loan")).isFalse();
        assertThat(GrafanaDashboardUtils.isDomainDashboardEnabled(propertiesMap, "Hello")).isFalse();

        values = " Hello, Loan ";
        propertiesMap.put(DISABLED_DOMAIN_DASHBOARDS, values);
        assertThat(GrafanaDashboardUtils.isDomainDashboardEnabled(propertiesMap, "Traffic")).isTrue();
        assertThat(GrafanaDashboardUtils.isDomainDashboardEnabled(propertiesMap, "Loan")).isFalse();
        assertThat(GrafanaDashboardUtils.isDomainDashboardEnabled(propertiesMap, "Hello")).isFalse();
    }
}