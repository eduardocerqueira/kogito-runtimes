package org.kie.kogito.event;

import java.util.List;

import org.kie.kogito.event.cloudevents.CloudEventMeta;

public interface TopicDiscovery {

    List<Topic> getTopics(List<CloudEventMeta> events);

}
