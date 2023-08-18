package org.kie.kogito.event;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import org.kie.kogito.event.cloudevents.CloudEventMeta;

/**
 * Responsible to hold information about a topic being consumed or produced by a Kogito service
 */
public class Topic {

    private String name;
    private ChannelType type;
    private Set<CloudEventMeta> eventsMeta;

    public Topic() {
        this.eventsMeta = new HashSet<>();
    }

    public Topic(final String name, final ChannelType type) {
        this();
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ChannelType getType() {
        return type;
    }

    public void setType(ChannelType type) {
        this.type = type;
    }

    /**
     * A collection of meta information about events that can be consumed or published by this topic
     *
     * @return a list of events
     */
    public Collection<CloudEventMeta> getEventsMeta() {
        return Collections.unmodifiableSet(this.eventsMeta);
    }

    public void setEventsMeta(Collection<CloudEventMeta> eventsMeta) {
        this.eventsMeta = new HashSet<>(eventsMeta);
    }

    @Override
    public String toString() {
        return "Topic{" +
                "name='" + name + '\'' +
                ", type=" + type +
                ", events=" + eventsMeta +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Topic topic = (Topic) o;
        return Objects.equals(name, topic.name) &&
                type == topic.type &&
                Objects.equals(eventsMeta, topic.eventsMeta);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, type, eventsMeta);
    }
}
