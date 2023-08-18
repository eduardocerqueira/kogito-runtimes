package org.kie.kogito.spring.drools;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kie.kogito.examples.Hello;
import org.kie.kogito.examples.KogitoSpringbootApplication;
import org.drools.ruleunits.api.RuleUnit;
import org.drools.ruleunits.api.RuleUnitInstance;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = KogitoSpringbootApplication.class)
public class HelloTest {

    @Autowired
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
