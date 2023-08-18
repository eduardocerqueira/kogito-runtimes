package org.kie.kogito.monitoring.core.common;

public class Constants {

    // https://issues.redhat.com/browse/KOGITO-4618 remove and take the constant from org.kie.kogito.explainability.Constants once explainability addon will be part of product
    public static final String SKIP_MONITORING = "skipMonitoring";
    public static final String MONITORING_RULE_USE_DEFAULT = "kogito.monitoring.rule.useDefault";
    public static final String MONITORING_PROCESS_USE_DEFAULT = "kogito.monitoring.process.useDefault";
    public static final String HTTP_INTERCEPTOR_USE_DEFAULT = "kogito.monitoring.interceptor.useDefault";

    private Constants() {

    }
}
