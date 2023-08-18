package org.kie.kogito.quarkus.drools;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.drools.ruleunits.api.RuleUnit;
import org.drools.ruleunits.api.RuleUnitInstance;
import org.junit.jupiter.api.Test;
import org.kie.kogito.examples.Hello;

import io.quarkus.test.junit.QuarkusTest;

import static org.assertj.core.api.Assertions.assertThat;

@QuarkusTest
public class HelloTest {

    @Inject
    RuleUnit<Hello> ruleUnit;

    @Test
    public void testHelloEndpoint() {
        Hello data = new Hello();
        data.getStrings().add("hello");

        RuleUnitInstance<Hello> ruleUnitInstance = ruleUnit.createInstance(data);
        List<Map<String, Object>> results = ruleUnitInstance.executeQuery("hello").toList();

        List<String> stringResults = results.stream()
                .flatMap(entry -> entry.values().stream())
                .map(String.class::cast)
                .collect(Collectors.toList());

        assertThat(stringResults).contains("hello", "world");
    }
}
