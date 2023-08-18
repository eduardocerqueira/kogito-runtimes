package org.kie.kogito.rules;

import java.util.Optional;
import java.util.OptionalInt;

import org.drools.ruleunits.api.conf.ClockType;
import org.drools.ruleunits.api.conf.EventProcessingType;

public class RuleUnitConfig extends org.drools.ruleunits.api.conf.RuleUnitConfig {

    public RuleUnitConfig(EventProcessingType eventProcessingType, ClockType clockType, Integer sessionPool) {
        super(eventProcessingType, clockType, sessionPool);
    }

    public RuleUnitConfig(Optional<EventProcessingType> eventProcessingType, Optional<ClockType> clockType, OptionalInt sessionPool) {
        super(eventProcessingType, clockType, sessionPool);
    }
}
