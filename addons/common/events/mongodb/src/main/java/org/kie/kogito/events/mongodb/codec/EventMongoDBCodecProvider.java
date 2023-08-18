package org.kie.kogito.events.mongodb.codec;

import org.bson.codecs.Codec;
import org.bson.codecs.configuration.CodecProvider;
import org.bson.codecs.configuration.CodecRegistry;
import org.kie.kogito.event.process.ProcessInstanceDataEvent;
import org.kie.kogito.event.process.UserTaskInstanceDataEvent;
import org.kie.kogito.event.process.VariableInstanceDataEvent;

public class EventMongoDBCodecProvider implements CodecProvider {

    private static final ProcessInstanceDataEventCodec PROCESS_INSTANCE_DATA_EVENT_CODEC = new ProcessInstanceDataEventCodec();
    private static final UserTaskInstanceDataEventCodec USER_TASK_INSTANCE_DATA_EVENT_CODEC = new UserTaskInstanceDataEventCodec();
    private static final VariableInstanceDataEventCodec VARIABLE_INSTANCE_DATA_EVENT_CODEC = new VariableInstanceDataEventCodec();

    @SuppressWarnings("unchecked")
    @Override
    public <T> Codec<T> get(Class<T> aClass, CodecRegistry codecRegistry) {
        if (aClass == ProcessInstanceDataEvent.class) {
            return (Codec<T>) PROCESS_INSTANCE_DATA_EVENT_CODEC;
        }
        if (aClass == UserTaskInstanceDataEvent.class) {
            return (Codec<T>) USER_TASK_INSTANCE_DATA_EVENT_CODEC;
        }
        if (aClass == VariableInstanceDataEvent.class) {
            return (Codec<T>) VARIABLE_INSTANCE_DATA_EVENT_CODEC;
        }
        return null;
    }
}
