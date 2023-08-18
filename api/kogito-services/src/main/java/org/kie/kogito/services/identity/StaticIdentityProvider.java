package org.kie.kogito.services.identity;

import java.util.Collections;
import java.util.List;

import org.kie.kogito.auth.IdentityProvider;

/**
 * @deprecated use IdentityProviders.of
 */
@Deprecated
public class StaticIdentityProvider implements IdentityProvider {

    private String name;
    private List<String> roles;

    public StaticIdentityProvider(String name) {
        this(name, Collections.emptyList());
    }

    public StaticIdentityProvider(String name, List<String> roles) {
        this.name = name;
        this.roles = roles == null ? Collections.emptyList() : roles;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public List<String> getRoles() {
        return roles;
    }

    @Override
    public boolean hasRole(String role) {
        return roles.contains(role);
    }

}
