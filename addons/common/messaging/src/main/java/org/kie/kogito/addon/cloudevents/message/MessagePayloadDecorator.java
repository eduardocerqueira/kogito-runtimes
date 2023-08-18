package org.kie.kogito.addon.cloudevents.message;

/**
 * Provides an interface for decorating produced messages with JSON Payload.
 * Add your own implementation by having a
 * <code>META-INF/services/org.kie.kogito.addon.cloudevents.message.MessagePayloadDecorator</code>
 * in the classpath.
 */
public interface MessagePayloadDecorator {

    String decorate(String jsonPayload);

}
