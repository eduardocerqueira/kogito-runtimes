package org.kie.kogito.serialization.process;

import org.kie.kogito.process.Process;

public final class MarshallerContextName<T> {

    public static final MarshallerContextName<ObjectMarshallerStrategy[]> OBJECT_MARSHALLING_STRATEGIES = new MarshallerContextName<>("OBJECT_MARSHALLING_STRATEGIES");
    public static final MarshallerContextName<String> MARSHALLER_FORMAT = new MarshallerContextName<>("FORMAT");
    public static final MarshallerContextName<Process<?>> MARSHALLER_PROCESS = new MarshallerContextName<>("PROCESS");
    public static final MarshallerContextName<Boolean> MARSHALLER_INSTANCE_READ_ONLY = new MarshallerContextName<>("READ_ONLY");

    public static final String MARSHALLER_FORMAT_JSON = "json";

    private String name;

    private MarshallerContextName(String name) {
        this.name = name;
    }

    public String name() {
        return this.name;
    }

    @SuppressWarnings("unchecked")
    public T cast(Object value) {
        return (T) value;
    }
}
