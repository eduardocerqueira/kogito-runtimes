package org.kie.kogito;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Provides direct information about addons installed and available
 * within the running service.
 *
 */
public class Addons {
    /**
     * Default empty addons instance
     */
    public static final Addons EMTPY = new Addons(Collections.emptySet());

    private final Set<String> availableAddons;

    public Addons(Set<String> availableAddons) {
        this.availableAddons = availableAddons;
    }

    /**
     * Returns all available addons
     * 
     * @return returns addons
     */
    public Set<String> availableAddons() {
        return availableAddons;
    }

    @Override
    public String toString() {
        return availableAddons.stream().collect(Collectors.joining(","));
    }
}
