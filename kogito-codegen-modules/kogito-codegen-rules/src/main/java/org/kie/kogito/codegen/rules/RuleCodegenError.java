package org.kie.kogito.codegen.rules;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

import org.drools.drl.parser.DroolsError;

public class RuleCodegenError extends Error {

    private final DroolsError[] errors;

    public RuleCodegenError(Collection<DroolsError> errors) {
        this(errors.toArray(new DroolsError[errors.size()]));
    }

    public RuleCodegenError(DroolsError... errors) {
        super("Errors were generated during the code-generation process:\n" +
                Arrays.stream(errors)
                        .map(DroolsError::toString)
                        .collect(Collectors.joining("\n")));
        this.errors = errors;
    }

    public RuleCodegenError(Exception ex, DroolsError... errors) {
        super("Errors were generated during the code-generation process:\n" +
                ex.getMessage() + "\n" +
                Arrays.stream(errors)
                        .map(DroolsError::toString)
                        .collect(Collectors.joining("\n")),
                ex);
        this.errors = errors;
    }

    public DroolsError[] getErrors() {
        return errors;
    }
}
