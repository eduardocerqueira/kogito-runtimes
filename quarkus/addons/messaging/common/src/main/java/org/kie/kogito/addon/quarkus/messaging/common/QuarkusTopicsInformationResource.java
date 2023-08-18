package org.kie.kogito.addon.quarkus.messaging.common;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.kie.kogito.addon.cloudevents.AbstractTopicsInformationResource;
import org.kie.kogito.event.TopicDiscovery;
import org.kie.kogito.event.cloudevents.CloudEventMeta;

@Path("/messaging/topics")
@ApplicationScoped()
public class QuarkusTopicsInformationResource extends AbstractTopicsInformationResource {

    @Inject
    private TopicDiscovery topicDiscovery;

    @Inject
    private Instance<CloudEventMeta> cloudEventMetaIterable;

    @PostConstruct
    private void onPostConstruct() {
        setup(topicDiscovery, cloudEventMetaIterable);
    }

    @GET()
    @Produces(MediaType.APPLICATION_JSON)
    public javax.ws.rs.core.Response getTopics() {
        return javax.ws.rs.core.Response.ok(getTopicList()).build();
    }
}
