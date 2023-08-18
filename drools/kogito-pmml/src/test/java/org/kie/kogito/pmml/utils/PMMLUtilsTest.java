package org.kie.kogito.pmml.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.kie.api.pmml.PMMLRequestData;
import org.kie.api.pmml.ParameterInfo;

import static org.assertj.core.api.Assertions.assertThat;

class PMMLUtilsTest {

    @Test
    void getPMMLRequestData() {
        final String modelName = "MODEL_NAME";
        final Map<String, Object> parameters = getParameters();
        final PMMLRequestData retrieved = PMMLUtils.getPMMLRequestData(modelName, parameters);
        assertThat(retrieved).isNotNull();
        assertThat(retrieved.getModelName()).isEqualTo(modelName);
        final Map<String, ParameterInfo> parameterInfos = retrieved.getMappedRequestParams();
        assertThat(parameterInfos).hasSameSizeAs(parameters);

        assertThat(parameters).allSatisfy((key, value) -> {
            assertThat(parameterInfos).containsKey(key);
            ParameterInfo parameterInfo = parameterInfos.get(key);
            assertThat(parameterInfo.getValue()).isEqualTo(value);
            assertThat(parameterInfo.getType()).isEqualTo(value.getClass());
        });
    }

    private Map<String, Object> getParameters() {
        final Map<String, Object> toReturn = new HashMap<>();
        IntStream.range(0, 3).forEach(i -> {
            toReturn.put("KEY_" + i, "VALUE_" + i);
        });
        return toReturn;
    }
}