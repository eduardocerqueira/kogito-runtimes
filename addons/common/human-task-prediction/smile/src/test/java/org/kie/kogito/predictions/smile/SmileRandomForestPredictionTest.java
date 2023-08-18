package org.kie.kogito.predictions.smile;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.drools.io.ClassPathResource;
import org.jbpm.process.instance.impl.humantask.HumanTaskWorkItemHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kie.kogito.Model;
import org.kie.kogito.prediction.api.PredictionAwareHumanTaskLifeCycle;
import org.kie.kogito.prediction.api.PredictionService;
import org.kie.kogito.process.ProcessConfig;
import org.kie.kogito.process.ProcessInstance;
import org.kie.kogito.process.bpmn2.BpmnProcess;
import org.kie.kogito.process.bpmn2.BpmnVariables;
import org.kie.kogito.process.impl.CachedWorkItemHandlerConfig;
import org.kie.kogito.process.impl.DefaultProcessEventListenerConfig;
import org.kie.kogito.process.impl.StaticProcessConfig;
import org.kie.kogito.services.uow.CollectingUnitOfWorkFactory;
import org.kie.kogito.services.uow.DefaultUnitOfWorkManager;

import static org.assertj.core.api.Assertions.assertThat;
import static org.kie.kogito.internal.process.runtime.KogitoProcessInstance.STATE_COMPLETED;

public class SmileRandomForestPredictionTest {

    private PredictionService predictionService;

    private ProcessConfig config;

    @BeforeEach
    public void configure() {

        final RandomForestConfiguration configuration = new RandomForestConfiguration();

        final Map<String, AttributeType> inputFeatures = new HashMap<>();
        inputFeatures.put("ActorId", AttributeType.NOMINAL);
        configuration.setInputFeatures(inputFeatures);

        configuration.setOutcomeName("output");
        configuration.setOutcomeType(AttributeType.NOMINAL);
        configuration.setConfidenceThreshold(0.7);
        configuration.setNumTrees(1);

        predictionService = new SmileRandomForest(configuration);
        CachedWorkItemHandlerConfig wiConfig = new CachedWorkItemHandlerConfig();
        wiConfig.register("Human Task", new HumanTaskWorkItemHandler(new PredictionAwareHumanTaskLifeCycle(predictionService)));
        config = new StaticProcessConfig(wiConfig, new DefaultProcessEventListenerConfig(), new DefaultUnitOfWorkManager(new CollectingUnitOfWorkFactory()));

        for (int i = 0; i < 10; i++) {
            predictionService.train(null, Collections.singletonMap("ActorId", "john"), Collections.singletonMap("output", "predicted value"));
        }
        for (int i = 0; i < 8; i++) {
            predictionService.train(null, Collections.singletonMap("ActorId", "mary"), Collections.singletonMap("output", "value"));
        }
    }

    @Test
    public void testUserTaskWithPredictionService() {

        BpmnProcess process = (BpmnProcess) BpmnProcess.from(config, new ClassPathResource("BPMN2-UserTask.bpmn2")).get(0);
        process.configure();

        ProcessInstance<BpmnVariables> processInstance = process.createInstance(BpmnVariables.create(Collections.singletonMap("test", "test")));

        processInstance.start();
        assertThat(processInstance.status()).isEqualTo(STATE_COMPLETED);

        Model result = (Model) processInstance.variables();
        assertThat(result.toMap()).hasSize(2)
                .containsEntry("s", "predicted value");
    }
}
