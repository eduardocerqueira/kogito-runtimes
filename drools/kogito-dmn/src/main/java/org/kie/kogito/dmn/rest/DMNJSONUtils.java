package org.kie.kogito.dmn.rest;

import java.util.Map;

import org.kie.dmn.api.core.DMNContext;
import org.kie.dmn.api.core.FEELPropertyAccessible;
import org.kie.kogito.decision.DecisionModel;

/**
 * Internal Utility class.<br/>
 * Used to simplify generated/scaffolded code to create a DMNContext accordingly to JSON un-marshalling semantic
 */
public class DMNJSONUtils {

    /**
     * Internal Utility method.<br/>
     * Used to simplify generated/scaffolded code to create a DMNContext accordingly to JSON un-marshalling semantic
     */
    public static DMNContext ctx(DecisionModel dm, Map<String, Object> variables) {
        if (variables != null && variables.size() > 0) {
            return new org.kie.dmn.core.internal.utils.DynamicDMNContextBuilder(new org.kie.dmn.core.impl.DMNContextImpl(), dm.getDMNModel()).populateContextWith(variables);
        } else {
            return dm.newContext(variables);
        }
    }

    /**
     * Internal Utility method.<br/>
     * Used to simplify generated/scaffolded code to create a DMNContext accordingly to JSON un-marshalling semantic
     */
    public static DMNContext ctx(DecisionModel dm, FEELPropertyAccessible variables) {
        return dm.newContext(variables);
    }

    /**
     * Internal Utility method.<br/>
     * Used to simplify generated/scaffolded code to create a DMNContext accordingly to JSON un-marshalling semantic
     */
    public static DMNContext ctx(DecisionModel dm, Map<String, Object> variables, String decisionServiceName) {
        if (variables != null && variables.size() > 0) {
            return new org.kie.dmn.core.internal.utils.DynamicDMNContextBuilder(new org.kie.dmn.core.impl.DMNContextImpl(), dm.getDMNModel()).populateContextForDecisionServiceWith(decisionServiceName,
                    variables);
        } else {
            return dm.newContext(variables);
        }
    }

    /**
     * Internal Utility method.<br/>
     * Used to simplify generated/scaffolded code to create a DMNContext accordingly to JSON un-marshalling semantic
     */
    public static DMNContext ctx(DecisionModel dm, FEELPropertyAccessible variables, String decisionServiceName) {
        return dm.newContext(variables);
    }

    private DMNJSONUtils() {
        // intentionally private.
    }
}
