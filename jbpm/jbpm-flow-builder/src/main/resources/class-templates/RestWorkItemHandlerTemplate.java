package org.jbpm.process.codegen;

import io.vertx.mutiny.ext.web.client.WebClient;
import io.vertx.ext.web.client.WebClientOptions;
import io.vertx.mutiny.core.Vertx;
import org.kogito.workitem.rest.RestWorkItemHandler;
import static org.kogito.workitem.rest.RestWorkItemHandlerUtils.sslWebClientOptions;

public class xxxRestWorkItemHandler extends RestWorkItemHandler {

    public xxxRestWorkItemHandler() {
        this(Vertx.vertx(), sslWebClientOptions());
    }

    public xxxRestWorkItemHandler(Vertx vertx, WebClientOptions sslOptions) {
    	super(WebClient.create(vertx), WebClient.create(vertx, sslOptions));
    }
    
    @Override
    public String getName() {
        return this.getClass().getName();
    }

}