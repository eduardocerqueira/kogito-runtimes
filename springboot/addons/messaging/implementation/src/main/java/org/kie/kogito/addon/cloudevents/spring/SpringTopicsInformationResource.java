package org.kie.kogito.addon.cloudevents.spring;

import java.util.List;

import org.kie.kogito.addon.cloudevents.AbstractTopicsInformationResource;
import org.kie.kogito.event.Topic;
import org.kie.kogito.event.TopicDiscovery;
import org.kie.kogito.event.cloudevents.CloudEventMeta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/messaging/topics")
@Component()
public class SpringTopicsInformationResource extends AbstractTopicsInformationResource {

    @Autowired
    public SpringTopicsInformationResource(TopicDiscovery topicDiscovery, List<CloudEventMeta> cloudEventMetaIterable) {
        setup(topicDiscovery, cloudEventMetaIterable);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Topic>> getTopics() {
        return ResponseEntity.ok(getTopicList());
    }
}
