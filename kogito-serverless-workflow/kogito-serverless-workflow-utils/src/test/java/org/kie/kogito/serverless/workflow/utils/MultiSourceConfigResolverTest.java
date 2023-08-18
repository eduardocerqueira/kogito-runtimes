package org.kie.kogito.serverless.workflow.utils;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MultiSourceConfigResolverTest {
    @Test
    void testMultiList() {
        ConfigResolver combined = MultiSourceConfigResolver.of(Arrays.asList(new MapConfigResolver(Map.of("list", "pepa,pepe")), new MapConfigResolver(Map.of("list", "pepa,pepi,pepo,pepu"))));
        assertThat(combined.getIndexedConfigProperty("list", String.class)).isEqualTo(Set.of("pepa", "pepe", "pepi", "pepo", "pepu"));
    }
}
