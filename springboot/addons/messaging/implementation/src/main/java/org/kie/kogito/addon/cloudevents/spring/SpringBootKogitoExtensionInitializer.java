package org.kie.kogito.addon.cloudevents.spring;

import javax.annotation.PostConstruct;

import org.kie.kogito.event.cloudevents.extension.KogitoExtension;
import org.kie.kogito.event.cloudevents.extension.KogitoPredictionsExtension;
import org.kie.kogito.event.cloudevents.extension.KogitoProcessExtension;
import org.kie.kogito.event.cloudevents.extension.KogitoRulesExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.cloudevents.jackson.JsonFormat;

/**
 * The goal of this bean is to register the Kogito CloudEvent extension
 * that allows the system to correctly parse Kogito extension attributes.
 */
@Component
public class SpringBootKogitoExtensionInitializer {

    private static final Logger LOG = LoggerFactory.getLogger(SpringBootKogitoExtensionInitializer.class);

    @Autowired
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
