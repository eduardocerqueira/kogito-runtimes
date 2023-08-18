package org.jbpm.process.core.validation;

import org.kie.api.definition.process.Process;
import org.kie.kogito.process.validation.ValidationError;

/**
 * Represents a RuleFlow validation error.
 * 
 */
public interface ProcessValidationError extends ValidationError {

    Process getProcess();
}
