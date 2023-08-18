package org.kie.kogito.addons.quarkus.camel.deployment;

import org.kie.kogito.addons.quarkus.camel.runtime.CamelConstants;

import com.fasterxml.jackson.databind.JsonNode;

import io.serverlessworkflow.api.functions.FunctionRef;

/**
 * Static validation for Workflow DSL in build time.
 */
public final class CamelFunctionStaticValidator {

    private CamelFunctionStaticValidator() {
    }

    /**
     * Validation for the Function Reference
     *
     * @throws IllegalArgumentException if the there's more than one argument in the function call
     */
    public static void validateFunctionRef(final FunctionRef ref) {
        if (ref.getArguments() == null) {
            return;
        }
        switch (ref.getArguments().size()) {
            case 0:
                return;
            case 1:
                verifyBodyArguments(ref);
                break;
            case 2:
                verifyBodyArguments(ref);
                verifyHeaderArguments(ref);
                break;
            default:
                throw new IllegalArgumentException(
                        "Camel functions only support 'body', 'header', or no arguments. Please review the function '" + ref.getRefName() + "' arguments: \n" + ref.getArguments().asText());
        }
    }

    private static void verifyHeaderArguments(final FunctionRef ref) {
        final JsonNode headers = ref.getArguments().get(CamelConstants.HEADERS);
        if (headers == null) {
            throw new IllegalArgumentException(
                    "Camel functions only support 'body', 'header', or no arguments. Please review the arguments for the function '" + ref.getRefName() + "': \n" + ref.getArguments().asText());
        }
        if (!headers.isObject() && !headers.isTextual()) {
            throw new IllegalArgumentException(
                    "Camel functions headers arguments must be a valid expression or a key/value object. "
                            + "Please review the arguments for the function '" + ref.getRefName() + "': \n"
                            + headers.asText());
        }
    }

    private static void verifyBodyArguments(final FunctionRef ref) {
        if (ref.getArguments().get(CamelConstants.BODY) == null) {
            throw new IllegalArgumentException(
                    "No body arguments found in the function reference. Please review the function '" + ref.getRefName() + "' arguments to include a '\"body\": {}' argument.");
        }
    }

}
