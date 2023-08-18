package org.kie.kogito.quarkus.runtime;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

import io.quarkus.arc.DefaultBean;
import io.vertx.ext.web.client.WebClientOptions;

import static org.kogito.workitem.rest.RestWorkItemHandlerUtils.sslWebClientOptions;

@ApplicationScoped
public class SSLWebClientOptionsProducer {

    @Produces
    @DefaultBean
    public WebClientOptions webClientOptions() {
        return sslWebClientOptions();
    }
}
