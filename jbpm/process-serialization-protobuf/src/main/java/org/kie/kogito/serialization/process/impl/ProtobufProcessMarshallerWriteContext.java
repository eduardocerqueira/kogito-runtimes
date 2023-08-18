package org.kie.kogito.serialization.process.impl;

import java.io.OutputStream;

import org.kie.kogito.serialization.process.MarshallerWriterContext;

/**
 * Extension to default <code>MarshallerWriteContext</code>
 */

public class ProtobufProcessMarshallerWriteContext extends ProtobufAbstractMarshallerContext implements MarshallerWriterContext {

    private OutputStream os;

    public ProtobufProcessMarshallerWriteContext(OutputStream os) {
        this.os = os;
    }

    @Override
    public OutputStream output() {
        return os;
    }

}
