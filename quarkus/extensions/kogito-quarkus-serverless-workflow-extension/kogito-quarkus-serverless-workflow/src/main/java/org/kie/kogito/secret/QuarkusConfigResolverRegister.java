package org.kie.kogito.secret;

import javax.annotation.PostConstruct;

import org.kie.kogito.serverless.workflow.utils.ConfigResolverHolder;

import io.quarkus.runtime.Startup;

@Startup
public class QuarkusConfigResolverRegister {

    @PostConstruct
    void init() {
        ConfigResolverHolder.setConfigResolver(new QuarkusConfigResolver());
    }

}
