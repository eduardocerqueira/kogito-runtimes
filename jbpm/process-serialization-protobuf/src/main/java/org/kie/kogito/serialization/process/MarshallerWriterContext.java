package org.kie.kogito.serialization.process;

import java.io.OutputStream;

public interface MarshallerWriterContext extends MarshallerContext {

    OutputStream output();

}
