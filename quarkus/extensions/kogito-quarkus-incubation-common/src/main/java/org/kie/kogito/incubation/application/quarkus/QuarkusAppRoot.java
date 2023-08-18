package org.kie.kogito.incubation.application.quarkus;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;

import org.kie.kogito.incubation.application.AppRoot;
import org.kie.kogito.incubation.common.ComponentRoot;

@ApplicationScoped
public class QuarkusAppRoot extends AppRoot {
    @Inject
    private Instance<ComponentRoot> componentRoots;

    QuarkusAppRoot() {
        super("kogito-app");
    }

    @Override
    public <T extends ComponentRoot> T get(Class<T> providerId) {
        return componentRoots.select(providerId).get();
    }
}