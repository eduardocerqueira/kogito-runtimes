package org.kie.kogito.grafana.dmn;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.kie.kogito.grafana.model.functions.GrafanaFunction;
import org.kie.kogito.grafana.model.panel.common.YAxis;

public class SupportedDecisionTypes {

    private static final Set<AbstractDmnType> supportedDmnTypes = new HashSet<>();

    private static final Map<Class, String> dmnInternalClassToDmnStandardMap = new HashMap<>();

    static {
        supportedDmnTypes.add(new BooleanType());
        supportedDmnTypes.add(new NumberType());
        supportedDmnTypes.add(new StringType());
        supportedDmnTypes.add(new TimeAndDateType());
        supportedDmnTypes.add(new DaysAndTimeDurationType());
        supportedDmnTypes.add(new YearsAndMonthsDurationType());
        supportedDmnTypes.add(new LocalDateType());
        supportedDmnTypes.add(new LocalTimeType());
        supportedDmnTypes.stream().forEach(x -> dmnInternalClassToDmnStandardMap.put(x.getInternalClass(), x.getDmnType()));
    }

    private SupportedDecisionTypes() {
    }

    public static boolean isSupported(String type) {
        return dmnInternalClassToDmnStandardMap.containsValue(type);
    }

    public static boolean isSupported(Class c) {
        return dmnInternalClassToDmnStandardMap.containsKey(c);
    }

    public static String fromInternalToStandard(Class c) {
        return dmnInternalClassToDmnStandardMap.get(c);
    }

    public static Optional<GrafanaFunction> getGrafanaFunction(String dmnType) {
        if (isSupported(dmnType)) {
            Optional<AbstractDmnType> type = supportedDmnTypes.stream().filter(x -> x.getDmnType().equalsIgnoreCase(dmnType)).findFirst();
            if (type.isPresent()) {
                return Optional.ofNullable(type.get().getGrafanaFunction());
            }
        }
        return Optional.empty();
    }

    public static List<YAxis> getYAxis(String dmnType) {
        if (isSupported(dmnType)) {
            Optional<AbstractDmnType> type = supportedDmnTypes.stream().filter(x -> x.getDmnType().equalsIgnoreCase(dmnType)).findFirst();
            if (type.isPresent()) {
                return type.get().getYaxes();
            }
        }
        return new ArrayList<>();
    }

    public static Collection<String> getSupportedDMNTypes() {
        return dmnInternalClassToDmnStandardMap.values();
    }
}
