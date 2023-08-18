package org.kie.kogito.serialization.process.impl;

import java.io.InputStream;

import org.kie.kogito.serialization.process.MarshallerReaderContext;

public class ProtobufMarshallerReaderContext extends ProtobufAbstractMarshallerContext implements MarshallerReaderContext {

    private InputStream is;

    public ProtobufMarshallerReaderContext(InputStream is) {
        this.is = is;
    }

    @Override
    public InputStream input() {
        return is;
    }

}
