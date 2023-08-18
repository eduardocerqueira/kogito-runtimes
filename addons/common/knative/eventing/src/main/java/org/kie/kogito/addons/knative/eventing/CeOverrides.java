package org.kie.kogito.addons.knative.eventing;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Representation of @{@link KnativeEventingMessagePayloadDecorator#K_CE_OVERRIDES} value
 * 
 * @see <a href="https://knative.dev/docs/developer/eventing/sources/sinkbinding/reference/#cloudevent-overrides">Knative Eventing SinkBinding - CloudEvent Overrides</a>
 */
public class CeOverrides {
    private Map<String, Object> extensions;

    public CeOverrides() {
        this.extensions = new HashMap<>();
    }

    public Map<String, Object> getExtensions() {
        return Collections.unmodifiableMap(this.extensions);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CeOverrides that = (CeOverrides) o;
        return Objects.equals(extensions, that.extensions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(extensions);
    }
}
