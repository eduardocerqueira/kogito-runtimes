package org.kie.kogito.incubation.rules;

import org.kie.kogito.incubation.common.LocalId;

public class InvalidRuleUnitIdException extends IllegalArgumentException {
    public InvalidRuleUnitIdException(LocalId id) {
        super("Not a valid rule unit ID" + id.asLocalUri());
    }
}
