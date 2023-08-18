package org.kie.kogito.tracing.decision;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.util.Map;

public class SpringBootDecisionTracingOneOfEachTypeTest extends BaseSpringBootDecisionTracingTest {

    private static final Map<String, Object> TEST_CONTEXT_VARIABLES = Map.of(
            "InputBoolean", true,
            "InputDate", LocalDate.of(2020, 1, 4),
            "InputDTDuration", Period.ofDays(1),
            "InputDateAndTime", LocalDateTime.of(2020, 1, 4, 16, 30),
            "InputNumber", 1,
            "InputString", "John Doe",
            "InputTime", LocalTime.of(16, 30),
            "InputYMDuration", Period.ofMonths(1));

    @Override
    protected String getTestModelName() {
        return "OneOfEachType";
    }

    @Override
    protected String getTestModelNameSpace() {
        return "http://www.trisotech.com/definitions/_4f5608e9-4d74-4c22-a47e-ab657257fc9c";
    }

    @Override
    protected Map<String, Object> getContextVariables() {
        return TEST_CONTEXT_VARIABLES;
    }

    @Override
    protected int getEvaluationEventCount() {
        return 18;
    }
}
