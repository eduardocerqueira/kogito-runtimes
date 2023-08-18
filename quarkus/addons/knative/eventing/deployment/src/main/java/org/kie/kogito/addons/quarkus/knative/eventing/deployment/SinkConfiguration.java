package org.kie.kogito.addons.quarkus.knative.eventing.deployment;

import java.util.Optional;

import io.quarkus.runtime.annotations.ConfigGroup;
import io.quarkus.runtime.annotations.ConfigItem;

@ConfigGroup
public class SinkConfiguration {

    public static final String DEFAULT_SINK_API_VERSION = "eventing.knative.dev/v1";
    public static final String DEFAULT_SINK_NAME = "default";
    public static final String DEFAULT_SINK_KIND = "Broker";

    /**
     * Namespace where the given Knative Sink is deployed.
     * This sink is used to configure the "sink" property in the generated Knative SinkBinding for this Kogito service.
     */
    @ConfigItem
    Optional<String> namespace;

    /**
     * Kubernetes API Version of the given Knative Sink.
     * <p>
     * This sink is used to configure the "sink" property in the generated Knative SinkBinding for this Kogito service.
     */
    @ConfigItem(defaultValue = DEFAULT_SINK_API_VERSION)
    String apiVersion;

    /**
     * Name of the given Knative Sink.
     * <p>
     * This sink is used to configure the "sink" property in the generated Knative SinkBinding for this Kogito service.
     */
    @ConfigItem(defaultValue = DEFAULT_SINK_NAME)
    String name;

    /**
     * Kubernetes Kind of the given Knative Sink.
     * <p>
     * This sink is used to configure the "sink" property in the generated Knative SinkBinding for this Kogito service.
     */
    @ConfigItem(defaultValue = DEFAULT_SINK_KIND)
    String kind;
}
