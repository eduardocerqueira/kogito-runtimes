package org.kie.kogito.addons.quarkus.knative.eventing.deployment;

import io.quarkus.runtime.annotations.ConfigItem;
import io.quarkus.runtime.annotations.ConfigPhase;
import io.quarkus.runtime.annotations.ConfigRoot;

@ConfigRoot(prefix = "org.kie.kogito.addons.knative", name = "eventing", phase = ConfigPhase.BUILD_TIME)
public class EventingConfiguration {

    /**
     * Name of the default Knative Broker deployed in the target Knative namespace.
     * <p>
     * This broker is used as the reference to create the Knative Triggers responsible
     * to delegate the events that this Kogito service will consume.
     */
    @ConfigItem(defaultValue = "default")
    String broker;

    /**
     * Whether the extension should generate a default Knative Broker in memory to sink and dispatch the messages.
     * <p>
     * Turn this property to `false` in case you already have a broker installed in your namespace rather than the default one.
     * Note that you can use `org.kie.kogito.addons.knative.eventing.sink.*` to configure your custom Sink.
     * If not defined, this auto generated Broker will work as the Sink.
     */
    @ConfigItem(defaultValue = "true")
    Boolean autoGenerateBroker;

    /**
     * Whether to generate the Knative [KogitoSource](https://github.com/knative-sandbox/eventing-kogito) instead of a Knative SinkBinding.
     * <p>
     * KogitoSource is the custom SinkBinding created specifically to bound with the Kogito ecosystem such as Data Index,
     * Jobs Service, and so on.
     * <p>
     * For minimal configuration and setup, you can leave this property set to `false` (default).
     */
    @ConfigItem(defaultValue = "false")
    Boolean generateKogitoSource;

    SinkConfiguration sink;
}
