package org.kie.kogito.dmn.rest;

import org.kie.dmn.api.core.DMNResult;

public class DMNEvaluationErrorException extends RuntimeException {

    private final DMNResult result;

    public DMNEvaluationErrorException(org.kie.dmn.api.core.DMNResult result) {
        super();
        this.result = result;
    }

    public DMNResult getResult() {
        return result;
    }
}
