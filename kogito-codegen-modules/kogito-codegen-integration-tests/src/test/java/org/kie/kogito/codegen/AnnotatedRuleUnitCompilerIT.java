package org.kie.kogito.codegen;

import org.drools.ruleunits.api.DataHandle;
import org.drools.ruleunits.api.RuleUnit;
import org.drools.ruleunits.api.RuleUnitInstance;
import org.junit.jupiter.api.Test;
import org.kie.kogito.Application;
import org.kie.kogito.codegen.data.Person;
import org.kie.kogito.codegen.unit.AnnotatedRules;
import org.kie.kogito.rules.RuleUnits;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class AnnotatedRuleUnitCompilerIT extends AbstractCodegenIT {

    @Test
    public void testAnnotatedRuleUnit() throws Exception {
        Application application = generateRulesFromJava("org/kie/kogito/codegen/unit/AnnotatedRules.java");
        assertNotNull(application);

        AnnotatedRules adults = new AnnotatedRules();

        adults.getPersons().add(new Person("Mario", 45));
        adults.getPersons().add(new Person("Marilena", 47));

        Person sofia = new Person("Sofia", 7);
        DataHandle dhSofia = adults.getPersons().add(sofia);

        RuleUnit<AnnotatedRules> unit = application.get(RuleUnits.class).create(AnnotatedRules.class);
        RuleUnitInstance<AnnotatedRules> instance = unit.createInstance(adults);
        assertNotNull(instance);
    }

}
