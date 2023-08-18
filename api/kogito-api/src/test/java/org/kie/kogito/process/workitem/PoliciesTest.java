package org.kie.kogito.process.workitem;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.kie.kogito.auth.IdentityProvider;

import static org.assertj.core.api.Assertions.assertThat;

class PoliciesTest {

    @Test
    void testPolicies() {
        assertThat(Policies.of(null)).isEmpty();
        Policy<IdentityProvider>[] policies = Policies.of("pepe", Arrays.asList("chief", "of", "the", "universe"));
        assertThat(policies).hasSize(1);
        assertThat(policies[0].value().getName()).isEqualTo("pepe");
        assertThat(policies[0].value().getRoles()).hasSize(4);
    }
}
