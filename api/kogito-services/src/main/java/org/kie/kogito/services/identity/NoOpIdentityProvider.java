package org.kie.kogito.services.identity;

import java.util.Collections;
import java.util.List;

import org.kie.kogito.auth.IdentityProvider;

public class NoOpIdentityProvider implements IdentityProvider {

    @Override
    public String getName() {
        return null;
    }

    @Override
    public List<String> getRoles() {
        return Collections.emptyList();
    }

    @Override
    public boolean hasRole(String role) {
        return false;
    }

}
