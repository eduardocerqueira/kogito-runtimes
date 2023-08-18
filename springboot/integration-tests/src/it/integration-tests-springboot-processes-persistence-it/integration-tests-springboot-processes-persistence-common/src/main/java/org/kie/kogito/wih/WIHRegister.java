package org.kie.kogito.wih;

import org.springframework.stereotype.Component;

import org.kie.kogito.process.impl.DefaultWorkItemHandlerConfig;

@Component
public class WIHRegister extends DefaultWorkItemHandlerConfig {
    {
        register("CustomTask", new CustomWorkItemHandler());
    }
}
