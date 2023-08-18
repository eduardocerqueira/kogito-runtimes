package org.kie.kogito.prediction;

public class PredictionModelNotFoundException extends RuntimeException {

    public PredictionModelNotFoundException() {
        super();
    }

    public PredictionModelNotFoundException(String message) {
        super(message);
    }

    public PredictionModelNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public PredictionModelNotFoundException(Throwable cause) {
        super(cause);
    }

}
