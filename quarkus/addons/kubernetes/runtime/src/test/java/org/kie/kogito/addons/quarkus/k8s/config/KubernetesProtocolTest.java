package org.kie.kogito.addons.quarkus.k8s.config;

import org.junit.jupiter.api.Test;
import org.kie.kogito.addons.k8s.resource.catalog.KubernetesProtocol;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

class KubernetesProtocolTest {

    @Test
    void parseKubernetes() {
        assertThat(KubernetesProtocol.from("kubernetes"))
                .isEqualTo(KubernetesProtocol.KUBERNETES);
    }

    @Test
    void parseOpenShift() {
        assertThat(KubernetesProtocol.from("openshift"))
                .isEqualTo(KubernetesProtocol.OPENSHIFT);
    }

    @Test
    void parseKnative() {
        assertThat(KubernetesProtocol.from("knative"))
                .isEqualTo(KubernetesProtocol.KNATIVE);
    }

    @Test
    void parseInvalidProtocol() {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> KubernetesProtocol.from("nonexistent_protocol"))
                .withMessage("The provided protocol [nonexistent_protocol] is not " +
                        "supported, supported values are 'kubernetes', 'openshift', and 'knative'");
    }
}