package org.kie.kogito.codegen.process;

import java.text.MessageFormat;
import java.util.Optional;

public class ProcessCodegenException extends RuntimeException {

    public ProcessCodegenException(String message) {
        super(message);
    }

    public ProcessCodegenException(String message, Optional<? extends Throwable> cause) {
        super(message, cause.orElse(null));
    }

    public ProcessCodegenException(String path, Throwable cause) {
        super(MessageFormat.format("Error while elaborating file \"{0}\": {1}", path, cause.getMessage()), cause);
    }

    public ProcessCodegenException(String id, String packageName, Throwable cause) {
        super(MessageFormat.format("Error while elaborating process id = \"{0}\", packageName = \"{1}\": {2}", id, packageName, cause.getMessage()), cause);
    }
}
