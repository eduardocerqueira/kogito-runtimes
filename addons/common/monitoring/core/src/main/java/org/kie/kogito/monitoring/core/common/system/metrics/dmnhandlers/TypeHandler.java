package org.kie.kogito.monitoring.core.common.system.metrics.dmnhandlers;

public interface TypeHandler<T> {

    void record(String type, String endpointName, T sample);

    String getDmnType();
}
