package org.kie.kogito.process.validation;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ValidationLogDecorator extends ValidationDecorator {

    private static final Logger LOGGER = LoggerFactory.getLogger(ValidationLogDecorator.class);

    public ValidationLogDecorator(Map<String, Throwable> exceptions) {
        super(exceptions);
    }

    @Override
    public void decorate() {
        exceptions.forEach((processId, exception) -> LOGGER.error("Invalid process: '{}'. Found error: {}", processId, exception));
    }
}
