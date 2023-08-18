package org.kie.kogito.serverless.workflow.executor;

import org.kogito.workitem.rest.RestWorkItemHandler;
import org.kogito.workitem.rest.RestWorkItemHandlerUtils;

import io.vertx.ext.web.client.WebClientOptions;
import io.vertx.mutiny.core.Vertx;
import io.vertx.mutiny.ext.web.client.WebClient;

class StaticRestWorkItemHandler extends RestWorkItemHandler implements AutoCloseable {

    public StaticRestWorkItemHandler(Vertx vertx) {
        this(vertx, new WebClientOptions(), RestWorkItemHandlerUtils.sslWebClientOptions());
    }

    public StaticRestWorkItemHandler(Vertx vertx, WebClientOptions httpOptions, WebClientOptions httpsOptions) {
        super(WebClient.create(vertx, httpOptions), WebClient.create(vertx, httpsOptions));
    }

    @Override
    public String getName() {
        return RestWorkItemHandler.REST_TASK_TYPE;
    }

    @Override
    public void close() {
        httpClient.close();
        httpsClient.close();
    }
}
