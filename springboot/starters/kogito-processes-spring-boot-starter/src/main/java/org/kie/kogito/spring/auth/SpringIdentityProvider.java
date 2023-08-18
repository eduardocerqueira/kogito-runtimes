package org.kie.kogito.spring.auth;

import java.security.Principal;
import java.util.Collection;
import java.util.Optional;

import org.kie.kogito.auth.IdentityProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import static java.util.Collections.emptySet;
import static java.util.stream.Collectors.toSet;

@Component
public class SpringIdentityProvider implements IdentityProvider {

    private Optional<Authentication> getAuthentication() {
        return Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication());
    }

    @Override
    public String getName() {
        return getAuthentication().map(Principal::getName).orElse(null);
    }

    @Override
    public Collection<String> getRoles() {
        return getAuthentication().map(a -> a.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(toSet())).orElse(emptySet());
    }

    @Override
    public boolean hasRole(String role) {
        return getAuthentication().map(a -> a.getAuthorities().stream().anyMatch(auth -> auth.getAuthority().equals(role))).orElse(false);
    }
}
