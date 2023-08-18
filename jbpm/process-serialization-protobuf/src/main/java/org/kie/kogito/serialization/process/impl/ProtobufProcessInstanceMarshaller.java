package org.kie.kogito.serialization.process.impl;

import java.io.IOException;

import org.jbpm.ruleflow.instance.RuleFlowProcessInstance;
import org.kie.kogito.process.ProcessInstance;
import org.kie.kogito.process.impl.AbstractProcess;
import org.kie.kogito.process.impl.AbstractProcessInstance;
import org.kie.kogito.serialization.process.MarshallerContextName;
import org.kie.kogito.serialization.process.MarshallerReaderContext;
import org.kie.kogito.serialization.process.MarshallerWriterContext;
import org.kie.kogito.serialization.process.ProcessInstanceMarshaller;

/**
 * Marshaller class for RuleFlowProcessInstances
 */

public class ProtobufProcessInstanceMarshaller implements ProcessInstanceMarshaller {

    @Override
    public void writeProcessInstance(MarshallerWriterContext context, ProcessInstance<?> processInstance) throws IOException {
        RuleFlowProcessInstance pi = (RuleFlowProcessInstance) ((AbstractProcessInstance<?>) processInstance).internalGetProcessInstance();
        ProtobufProcessInstanceWriter writer = new ProtobufProcessInstanceWriter(context);
        writer.writeProcessInstance(pi, context.output());
    }

    @Override
    public ProcessInstance<?> readProcessInstance(MarshallerReaderContext context) throws IOException {
        ProtobufProcessInstanceReader reader = new ProtobufProcessInstanceReader(context);
        boolean readOnly = context.get(MarshallerContextName.MARSHALLER_INSTANCE_READ_ONLY);
        AbstractProcess<?> process = (AbstractProcess<?>) context.get(MarshallerContextName.MARSHALLER_PROCESS);
        return readOnly ? process.createReadOnlyInstance(reader.read(context.input())) : process.createInstance(reader.read(context.input()));
    }

    @Override
    public void reloadProcessInstance(MarshallerReaderContext context, ProcessInstance<?> processInstance) throws IOException {
        ProtobufProcessInstanceReader reader = new ProtobufProcessInstanceReader(context);
        ((AbstractProcessInstance<?>) processInstance).internalSetProcessInstance(reader.read(context.input()));
    }

}
