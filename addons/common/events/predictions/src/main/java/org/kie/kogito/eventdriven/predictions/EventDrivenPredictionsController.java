package org.kie.kogito.eventdriven.predictions;

import java.net.URI;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

import org.kie.api.pmml.PMML4Result;
import org.kie.kogito.config.ConfigBean;
import org.kie.kogito.event.DataEvent;
import org.kie.kogito.event.DataEventFactory;
import org.kie.kogito.event.EventEmitter;
import org.kie.kogito.event.EventReceiver;
import org.kie.kogito.event.cloudevents.extension.KogitoPredictionsExtension;
import org.kie.kogito.event.cloudevents.utils.CloudEventUtils;
import org.kie.kogito.prediction.PredictionModel;
import org.kie.kogito.prediction.PredictionModelNotFoundException;
import org.kie.kogito.prediction.PredictionModels;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.cloudevents.core.provider.ExtensionProvider;

public class EventDrivenPredictionsController {

    public static final String REQUEST_EVENT_TYPE = "PredictionRequest";
    public static final String RESPONSE_EVENT_TYPE = "PredictionResponse";
    public static final String RESPONSE_FULL_EVENT_TYPE = "PredictionResponseFull";

    private static final Logger LOG = LoggerFactory.getLogger(EventDrivenPredictionsController.class);

    private PredictionModels predictionModels;
    private ConfigBean config;
    private EventEmitter eventEmitter;
    private EventReceiver eventReceiver;

    protected EventDrivenPredictionsController() {
    }

    protected EventDrivenPredictionsController(PredictionModels predictionModels, ConfigBean config, EventEmitter eventEmitter, EventReceiver eventReceiver) {
        init(predictionModels, config, eventEmitter, eventReceiver);
    }

    protected void init(PredictionModels decisionModels, ConfigBean config, EventEmitter eventEmitter, EventReceiver eventReceiver) {
        this.predictionModels = decisionModels;
        this.config = config;
        this.eventEmitter = eventEmitter;
        this.eventReceiver = eventReceiver;

    }

    protected void subscribe() {
        eventReceiver.subscribe(this::handleRequest, Map.class);
    }

    private CompletionStage<Void> handleRequest(DataEvent<Map> event) {
        KogitoPredictionsExtension extension = ExtensionProvider.getInstance().parseExtension(KogitoPredictionsExtension.class, event);
        if (CloudEventUtils.isValidRequest(event, REQUEST_EVENT_TYPE, extension)) {
            getPredictionModel(extension.getPmmlFileName(), extension.getPmmlModelName()).map(model -> model.evaluateAll(model.newContext(event.getData())))
                    .ifPresentOrElse(result -> eventEmitter.emit(buildResponseCloudEvent(result, event, extension)), () -> LOG.warn("Discarding request because not model is found for {}", extension));
        } else {
            LOG.warn("Event {} is not valid. Ignoring it", event);
        }
        return CompletableFuture.completedFuture(null);
    }

    private Optional<PredictionModel> getPredictionModel(String fileName, String modelName) {
        try {
            return Optional.ofNullable(predictionModels.getPredictionModel(fileName, modelName));
        } catch (PredictionModelNotFoundException e) {
            LOG.warn("Model not found with name=\"{}\"", modelName);
            return Optional.empty();
        }
    }

    private DataEvent<?> buildResponseCloudEvent(PMML4Result result, DataEvent<Map> event, KogitoPredictionsExtension extension) {
        URI source = CloudEventUtils.buildDecisionSource(config.getServiceUrl(), extension.getPmmlModelName());
        Optional<String> subject = Optional.ofNullable(event.getSubject());
        KogitoPredictionsExtension publishedExtension = publishedExtension(extension);
        return CloudEventUtils.safeBoolean(extension.isPmmlFullResult()) ? DataEventFactory.from(result, RESPONSE_FULL_EVENT_TYPE, source, subject, publishedExtension)
                : DataEventFactory.from(Collections.singletonMap(result.getResultObjectName(),
                        result.getResultVariables().get(result.getResultObjectName())), RESPONSE_EVENT_TYPE, source, subject, publishedExtension);
    }

    private static KogitoPredictionsExtension publishedExtension(KogitoPredictionsExtension extension) {
        KogitoPredictionsExtension published = new KogitoPredictionsExtension();
        published.setPmmlFileName(extension.getPmmlFileName());
        published.setPmmlModelName(extension.getPmmlModelName());
        return published;
    }
}
