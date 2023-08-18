package org.kie.kogito.auth;

import java.util.Collection;

/**
 * Delivers security information about given identity that includes name and assigned roles.
 *
 */
public interface IdentityProvider {

    /**
     * Returns name assigned to the current context, usually refers to user name
     * 
     * @return assigned name taken from security context
     */
    String getName();

    /**
     * Returns roles assigned to the current context if any
     * 
     * @return Collection of assigned roles or empty one
     */
    Collection<String> getRoles();

    /**
     * Checks if given role is assigned to current context
     * 
     * @param role role to be checked
     * @return true if the role is found otherwise null
     */
    boolean hasRole(String role);
}
