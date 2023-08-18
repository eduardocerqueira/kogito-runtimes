package org.kie.kogito.serialization.process;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * Registry for Process/ProcessMarshaller
 */
public interface ProcessInstanceMarshallerFactory {

    ProcessInstanceMarshaller newKogitoProcessInstanceMarshaller();

    MarshallerWriterContext newWriterContext(OutputStream baos);

    MarshallerReaderContext newReaderContext(InputStream bais);

}
