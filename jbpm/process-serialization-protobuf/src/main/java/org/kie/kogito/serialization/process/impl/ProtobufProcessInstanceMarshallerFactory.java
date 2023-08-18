package org.kie.kogito.serialization.process.impl;

import java.io.InputStream;
import java.io.OutputStream;

import org.kie.kogito.serialization.process.MarshallerReaderContext;
import org.kie.kogito.serialization.process.MarshallerWriterContext;
import org.kie.kogito.serialization.process.ProcessInstanceMarshaller;
import org.kie.kogito.serialization.process.ProcessInstanceMarshallerFactory;

public class ProtobufProcessInstanceMarshallerFactory implements ProcessInstanceMarshallerFactory {

    public ProcessInstanceMarshaller newKogitoProcessInstanceMarshaller() {
        return new ProtobufProcessInstanceMarshaller();
    }

    public MarshallerWriterContext newWriterContext(OutputStream baos) {
        return new ProtobufProcessMarshallerWriteContext(baos);
    }

    public MarshallerReaderContext newReaderContext(InputStream bais) {
        return new ProtobufMarshallerReaderContext(bais);
    }
}
