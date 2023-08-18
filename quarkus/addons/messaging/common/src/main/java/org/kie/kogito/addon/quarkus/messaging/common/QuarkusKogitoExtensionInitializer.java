package org.kie.kogito.addon.quarkus.messaging.common;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.kie.kogito.event.cloudevents.extension.KogitoExtension;
import org.kie.kogito.event.cloudevents.extension.KogitoPredictionsExtension;
import org.kie.kogito.event.cloudevents.extension.KogitoProcessExtension;
import org.kie.kogito.event.cloudevents.extension.KogitoRulesExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.cloudevents.jackson.JsonFormat;
import io.quarkus.runtime.Startup;

/**
 * The goal of this bean is to register the Kogito CloudEvent extension
 * that allows the system to correctly parse Kogito extension attributes.
 */
@Startup
public class QuarkusKogitoExtensionInitializer {

    private static final Logger LOG = LoggerFactory.getLogger(QuarkusKogitoExtensionInitializer.class);

    @Inject
    ObjectMapper mapper;

    @PostConstruct
    private void onPostConstruct() {
        mapper.registerModule(JsonFormat.getCloudEventJacksonModule());
        KogitoExtension.register();
        KogitoPredictionsExtension.register();
        KogitoProcessExtension.register();
        KogitoRulesExtension.register();
        LOG.info("Registered Kogito CloudEvent extension");
    }
}
