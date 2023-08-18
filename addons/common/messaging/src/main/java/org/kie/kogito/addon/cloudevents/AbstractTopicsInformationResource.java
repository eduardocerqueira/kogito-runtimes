package org.kie.kogito.addon.cloudevents;

import java.util.List;
import java.util.stream.StreamSupport;

import org.kie.kogito.event.Topic;
import org.kie.kogito.event.TopicDiscovery;
import org.kie.kogito.event.cloudevents.CloudEventMeta;

import static java.util.stream.Collectors.toList;

public class AbstractTopicsInformationResource {

    private TopicDiscovery topicDiscovery;
    private List<CloudEventMeta> cloudEventMetaList;

    protected void setup(TopicDiscovery topicDiscovery, Iterable<CloudEventMeta> cloudEventMetaIterable) {
        this.topicDiscovery = topicDiscovery;
        this.cloudEventMetaList = StreamSupport.stream(cloudEventMetaIterable.spliterator(), false).collect(toList());
    }

    protected List<Topic> getTopicList() {
        return topicDiscovery.getTopics(cloudEventMetaList);
    }
}
