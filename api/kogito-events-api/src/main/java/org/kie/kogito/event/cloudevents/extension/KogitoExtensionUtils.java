package org.kie.kogito.event.cloudevents.extension;

import java.util.Optional;
import java.util.function.Consumer;

import io.cloudevents.CloudEventExtensions;

public class KogitoExtensionUtils {

    private KogitoExtensionUtils() {
    }

    public static void readStringExtension(CloudEventExtensions extensions, String key, Consumer<String> consumer) {
        Optional.ofNullable(extensions.getExtension(key))
                // there seems to be a bug in the cloudevents sdk so that, when a extension attributes is null,
                // it returns a "null" String instead of a real null object
                .filter(obj -> !("null".equals(obj)))
                .map(Object::toString)
                .ifPresent(consumer);
    }

    public static void readBooleanExtension(CloudEventExtensions extensions, String key, Consumer<Boolean> consumer) {
        Optional.ofNullable(extensions.getExtension(key))
                .filter(Boolean.class::isInstance)
                .map(Boolean.class::cast)
                .ifPresent(consumer);
    }

}
