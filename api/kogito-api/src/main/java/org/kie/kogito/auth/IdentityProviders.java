package org.kie.kogito.auth;

import java.util.Collection;
import java.util.Collections;

public class IdentityProviders {

    public static final String UNKNOWN_USER_IDENTITY = "unknown";

    private static class DefaultIdentityProvider implements IdentityProvider {

        private String name;
        private Collection<String> roles;

        public DefaultIdentityProvider(String name, Collection<String> roles) {
            this.name = name;
            this.roles = roles;
        }

        @Override
        public String getName() {
            return name;
        }

        @Override
        public Collection<String> getRoles() {
            return roles;
        }

        @Override
        public boolean hasRole(String role) {
            return roles.contains(role);
        }

    }

    public static IdentityProvider of(String name) {
        return new DefaultIdentityProvider(name, Collections.emptyList());
    }

    public static IdentityProvider of(String name, Collection<String> roles) {
        return new DefaultIdentityProvider(name, roles == null ? Collections.emptyList() : roles);
    }

    private IdentityProviders() {
    }

}
