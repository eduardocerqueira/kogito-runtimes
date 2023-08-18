package org.kie.kogito.serialization.process;

import java.io.IOException;

import org.kie.kogito.process.ProcessInstance;

/**
 * A ProcessInstanceMarshaller must contain all the write/read logic for nodes
 * of a specific ProcessInstance. It colaborates with OutputMarshaller and
 * InputMarshaller, that delegates in a ProcessInstanceMarshaller to stream in/out runtime
 * information.
 *
 * @see org.drools.core.marshalling.impl.OutputMarshaller
 * @see ProcessInstanceMarshallerFactory
 */

public interface ProcessInstanceMarshaller {

    void writeProcessInstance(MarshallerWriterContext context, ProcessInstance<?> processInstance) throws IOException;

    ProcessInstance<?> readProcessInstance(MarshallerReaderContext context) throws IOException;

    void reloadProcessInstance(MarshallerReaderContext context, ProcessInstance<?> processInstance) throws IOException;

}
