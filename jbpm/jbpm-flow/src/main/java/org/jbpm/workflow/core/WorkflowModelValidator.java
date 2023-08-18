package org.jbpm.workflow.core;

import java.io.Serializable;
import java.util.Map;
import java.util.Optional;

public interface WorkflowModelValidator extends Serializable {

    /**
     * Validates the input model
     * 
     * @param model the input model as a map
     * @throws IllegalArgumentException if the validation failed
     */
    void validate(Map<String, Object> model);

    default <T> Optional<T> schema(Class<T> clazz) {
        return Optional.empty();
    }
}
