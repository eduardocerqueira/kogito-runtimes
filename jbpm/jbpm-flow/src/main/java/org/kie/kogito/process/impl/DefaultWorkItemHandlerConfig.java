package org.kie.kogito.process.impl;

import org.jbpm.process.instance.impl.demo.SystemOutWorkItemHandler;
import org.jbpm.process.instance.impl.humantask.HumanTaskWorkItemHandler;

public class DefaultWorkItemHandlerConfig extends CachedWorkItemHandlerConfig {
    {
        register("Log", new SystemOutWorkItemHandler());
        register("Human Task", new HumanTaskWorkItemHandler());
    }
}
