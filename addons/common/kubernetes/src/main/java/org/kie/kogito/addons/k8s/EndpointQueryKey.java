package org.kie.kogito.addons.k8s;

import java.io.Serializable;
import java.util.Collections;
import java.util.Map;
import java.util.Objects;

/**
 * Data Holder for the discovery query key terms.
 */
public class EndpointQueryKey implements Serializable {

    private String name;
    private String namespace;
    private Map<String, String> labels;

    public EndpointQueryKey(final String namespace, final String name) {
        this.name = name;
        this.namespace = namespace;
    }

    public EndpointQueryKey(final String namespace, final Map<String, String> labels) {
        this.namespace = namespace;
        this.labels = labels;
    }

    public Map<String, String> getLabels() {
        return Collections.unmodifiableMap(labels);
    }

    public String getName() {
        return name;
    }

    public String getNamespace() {
        return namespace;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        EndpointQueryKey that = (EndpointQueryKey) o;
        return Objects.equals(name, that.name) && Objects.equals(namespace, that.namespace) && Objects.equals(labels, that.labels);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, namespace, labels);
    }
}
