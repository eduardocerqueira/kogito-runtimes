package org.kie.kogito.pmml.openapi.factories;

import org.kie.kogito.pmml.openapi.api.PMMLOASResult;
import org.kie.kogito.pmml.openapi.impl.PMMLOASResultImpl;
import org.kie.pmml.commons.model.KiePMMLModel;

/**
 * Static <code>PMMLOASResult</code> factory
 */
public class PMMLOASResultFactory {

    private PMMLOASResultFactory() {
        // avoid instantiation
    }

    public static PMMLOASResult getPMMLOASResult(KiePMMLModel source) {
        PMMLOASResultImpl.Builder builder = new PMMLOASResultImpl.Builder();
        if (source.getMiningFields() != null) {
            builder = builder.withMiningFields(source.getMiningFields());
        }
        if (source.getOutputFields() != null) {
            builder = builder.withOutputFields(source.getOutputFields());
        }
        return builder.build();
    }
}
