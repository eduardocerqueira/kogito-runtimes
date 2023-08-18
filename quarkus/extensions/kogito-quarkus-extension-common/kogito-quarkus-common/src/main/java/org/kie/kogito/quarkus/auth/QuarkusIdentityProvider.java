package org.kie.kogito.quarkus.auth;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.control.ActivateRequestContext;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;

import org.kie.kogito.auth.IdentityProvider;

import io.quarkus.security.identity.SecurityIdentity;

@ApplicationScoped
public class QuarkusIdentityProvider implements IdentityProvider {

    @Inject
    Instance<SecurityIdentity> identity;

    private Optional<SecurityIdentity> getIdentity() {
        return identity.isResolvable() ? Optional.of(identity.get()) : Optional.empty();
    }

    @ActivateRequestContext
    @Override
    public String getName() {
        return getIdentity().filter(securityIdentity -> !securityIdentity.isAnonymous()).map(securityIdentity -> securityIdentity.getPrincipal().getName()).orElse(null);
    }

    @ActivateRequestContext
    @Override
    public Collection<String> getRoles() {
        return getIdentity().map(SecurityIdentity::getRoles).orElse(Collections.emptySet());
    }

    @ActivateRequestContext
    @Override
    public boolean hasRole(String role) {
        return getIdentity().map(securityIdentity -> securityIdentity.hasRole(role)).orElse(false);
    }
}
