package org.kie.kogito.serialization.process;

import java.io.InputStream;

public interface MarshallerReaderContext extends MarshallerContext {

    InputStream input();

}
