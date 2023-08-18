package org.kie.kogito.event.impl;

import java.util.Collections;
import java.util.List;

import org.kie.kogito.event.Topic;
import org.kie.kogito.event.TopicDiscovery;
import org.kie.kogito.event.cloudevents.CloudEventMeta;

/**
 * Default {@link TopicDiscovery} implementation for services with no eventing requirement
 */
public class NoOpTopicDiscovery implements TopicDiscovery {

    @Override
    public List<Topic> getTopics(List<CloudEventMeta> events) {
        return Collections.emptyList();
    }
}
