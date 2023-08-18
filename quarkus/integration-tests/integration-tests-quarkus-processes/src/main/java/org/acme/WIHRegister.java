package org.acme;

import javax.enterprise.context.ApplicationScoped;

import org.kie.kogito.process.impl.DefaultWorkItemHandlerConfig;

@ApplicationScoped
public class WIHRegister extends DefaultWorkItemHandlerConfig {
    {
        register("CustomTask", new CustomTaskWorkItemHandler());
    }
}
