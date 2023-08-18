package org.kie.kogito.incubation.application;

import org.kie.kogito.incubation.common.ComponentRoot;

/**
 * Useful for testing. Creates Components reflectively.
 */
public class ReflectiveAppRoot extends AppRoot {
    public ReflectiveAppRoot(String name) {
        super(name);
    }

    public ReflectiveAppRoot() {
        super("kogito-app");
    }

    @Override
    public <T extends ComponentRoot> T get(Class<T> providerId) {
        try {
            return providerId.getConstructor().newInstance();
        } catch (ReflectiveOperationException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
