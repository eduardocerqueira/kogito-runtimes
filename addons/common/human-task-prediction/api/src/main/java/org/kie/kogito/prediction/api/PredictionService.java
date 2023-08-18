package org.kie.kogito.prediction.api;

import java.util.Map;

import org.kie.api.runtime.process.WorkItem;

/**
 * Interface which allows a prediction or recommendation service implementation to be trained with task data
 * and return outcome predictions based on task input.
 * <p>
 * This interface is still considered subject to change.
 */
public interface PredictionService {

    String getIdentifier();

    /**
     * Return an outcome prediction for a set of input attributes.
     * 
     * @param task Task information to be optionally used by the predictive model
     * @param inputData A map of input attributes with the attribute name as key and the attribute value as value.
     * @return PredictionOutcome object which encapsulates the results from a prediction
     */
    PredictionOutcome predict(WorkItem task, Map<String, Object> inputData);

    /**
     * Train a predictive model using task data and a set of input and outcome attributes.
     * 
     * @param task Task information to be optionally used by the predictive model
     * @param inputData A map of input attributes with the attribute name as key and the attribute value as value.
     * @param outputData A map of output attributes (outcomes) with the attribute name as key and the attribute value as value.
     */
    void train(WorkItem task, Map<String, Object> inputData, Map<String, Object> outputData);
}
