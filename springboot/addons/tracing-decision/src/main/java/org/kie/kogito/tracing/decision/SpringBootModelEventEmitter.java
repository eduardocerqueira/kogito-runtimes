package org.kie.kogito.tracing.decision;

import javax.annotation.PostConstruct;

import org.kie.kogito.decision.DecisionModelResourcesProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class SpringBootModelEventEmitter extends BaseModelEventEmitter {

    private final KafkaTemplate<String, String> template;
    private final String kafkaTopicName;

    @Autowired
    public SpringBootModelEventEmitter(final DecisionModelResourcesProvider decisionModelResourcesProvider,
            final KafkaTemplate<String, String> template,
            final @Value(value = "${kogito.addon.tracing.model.kafka.topic.name:kogito-tracing-model}") String kafkaTopicName) {
        super(decisionModelResourcesProvider);
        this.template = template;
        this.kafkaTopicName = kafkaTopicName;
    }

    @Override
    @PostConstruct
    public void publishDecisionModels() {
        super.publishDecisionModels();
    }

    @Override
    public void emit(final String payload) {
        template.send(kafkaTopicName, payload);
    }
}
