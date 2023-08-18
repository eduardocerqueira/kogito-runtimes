package org.kie.kogito.grafana;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.kie.kogito.KogitoGAV;

import static org.assertj.core.api.Assertions.assertThat;

public class GrafanaConfigurationWriterTest {

    @Test
    public void testGrafanaDashboardName() {
        KogitoGAV gav = new KogitoGAV("groupId", "artifactId", "versionId");
        String handlerName = "myHandler";
        String expected = "artifactId_versionId - myHandler";

        String dashboardName = GrafanaConfigurationWriter.buildDashboardName(Optional.of(gav), handlerName);

        assertThat(dashboardName).isEqualTo(expected);
    }

    @Test
    public void testGrafanaDashboardNameWithEmptyGav() {
        String handlerName = "myHandler";
        String expected = "myHandler";

        String dashboardName = GrafanaConfigurationWriter.buildDashboardName(Optional.empty(), handlerName);

        assertThat(dashboardName).isEqualTo(expected);
    }
}
