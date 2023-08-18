package org.kie.kogito.process.validation;

import java.io.Serializable;

public interface ValidationError extends Serializable {

    String getMessage();

}
