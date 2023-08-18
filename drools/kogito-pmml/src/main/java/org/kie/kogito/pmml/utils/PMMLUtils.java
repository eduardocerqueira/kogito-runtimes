package org.kie.kogito.pmml.utils;

import java.util.Map;

import org.kie.api.pmml.PMMLRequestData;
import org.kie.pmml.evaluator.core.utils.PMMLRequestDataBuilder;

/**
 * Class providing common methods
 */
public class PMMLUtils {

    private static final String CORRELATION_ID = "CORRELATION_ID";

    private PMMLUtils() {
        // Avoid instantiation
    }

    public static PMMLRequestData getPMMLRequestData(String modelName, Map<String, Object> parameters) {
        PMMLRequestDataBuilder pmmlRequestDataBuilder = new PMMLRequestDataBuilder(CORRELATION_ID, modelName);
        for (Map.Entry<String, Object> entry : parameters.entrySet()) {
            Object pValue = entry.getValue();
            Class class1 = pValue.getClass();
            pmmlRequestDataBuilder.addParameter(entry.getKey(), pValue, class1);
        }
        return pmmlRequestDataBuilder.build();
    }

    public static PMMLRequestData getPMMLRequestData(String modelName) {
        PMMLRequestDataBuilder pmmlRequestDataBuilder = new PMMLRequestDataBuilder(CORRELATION_ID, modelName);
        return pmmlRequestDataBuilder.build();
    }

}
