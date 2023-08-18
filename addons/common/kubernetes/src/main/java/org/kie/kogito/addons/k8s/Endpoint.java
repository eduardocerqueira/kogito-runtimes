package org.kie.kogito.addons.k8s;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Reference of the discovered endpoint
 */
public class Endpoint implements Serializable {

    private String url;
    private String name;
    private Map<String, String> secondaryURLs = new HashMap<>();
    private Map<String, String> labels = new HashMap<>();

    public Endpoint() {
    }

    public Endpoint(final String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * An optional name for this endpoint.
     */
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * Labels given to the Service
     */
    public Map<String, String> getLabels() {
        return Collections.unmodifiableMap(labels);
    }

    public void setLabels(Map<String, String> labels) {
        this.labels = labels;
    }

    /**
     * Map to secondary URLs found in the target service.
     * For example, a service might expose monitoring ports targeting the very same service.
     * Key is the name of the port, and the value the resolved URL via Cluster IP.
     */
    public Map<String, String> getSecondaryURLs() {
        return Collections.unmodifiableMap(secondaryURLs);
    }

    /**
     * @param name non-null, non-empty URL name
     * @param url non-null, non-empty URL
     * @throws NullPointerException in case either parameters are null or empty
     */
    public void addSecondaryUrl(final String name, final String url) {
        if (name == null || name.isEmpty()) {
            throw new NullPointerException("Service port name can't be null or empty");
        }
        if (url == null || url.isEmpty()) {
            throw new NullPointerException("Endpoint URL can't be null or empty");
        }
        if (this.secondaryURLs == null) {
            this.secondaryURLs = new HashMap<>();
        }
        this.secondaryURLs.put(name, url);
    }

    public String getSecondaryUrl(final String name) {
        return this.secondaryURLs.get(name);
    }

    public void removeSecondaryUrl(final String name) {
        this.secondaryURLs.remove(name);
    }

    /**
     * Set the given URL as primary if none defined.
     *
     * @param url the given URL
     */
    public void setUrlIfEmpty(final String name, final String url) {
        if (url != null && !url.isEmpty() && this.urlIsEmpty()) {
            this.setUrl(url);
            this.setName(name);
        }
    }

    public boolean urlIsEmpty() {
        return this.getUrl() == null || this.getUrl().isEmpty();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Endpoint endpoint = (Endpoint) o;
        return Objects.equals(name, endpoint.name) && Objects.equals(url, endpoint.url) && Objects.equals(secondaryURLs, endpoint.secondaryURLs);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, url, secondaryURLs);
    }
}
