package org.kie.kogito.integrationtests;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import org.kie.kogito.addon.quarkus.messaging.common.http.AbstractQuarkusCloudEventResource;

import io.cloudevents.CloudEvent;

@Path("/")
public class QuarkusCloudEventResource extends AbstractQuarkusCloudEventResource {

    @Override
    public CompletionStage<Response> cloudEventListener(CloudEvent event) {
        return CompletableFuture.completedFuture(Response.ok(this.serialize(event)).build());
    }
}
