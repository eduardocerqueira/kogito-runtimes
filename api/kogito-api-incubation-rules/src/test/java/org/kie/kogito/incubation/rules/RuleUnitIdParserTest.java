package org.kie.kogito.incubation.rules;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RuleUnitIdParserTest {

    @Test
    void parseRuleUnitId() {
        assertThat(RuleUnitIdParser.parse("/rule-units/u").getClass()).isEqualTo(RuleUnitId.class);
        assertThat(RuleUnitIdParser.parse("/rule-units/u", RuleUnitId.class).ruleUnitId()).isEqualTo("u");
    }

    @Test
    void parseQueryId() {
        assertThat(RuleUnitIdParser.parse("/rule-units/u/queries/q").getClass()).isEqualTo(QueryId.class);
        assertThat(RuleUnitIdParser.parse("/rule-units/u/queries/q", RuleUnitId.class).ruleUnitId()).isEqualTo("u");
        assertThat(RuleUnitIdParser.parse("/rule-units/u/queries/q", QueryId.class).queryId()).isEqualTo("q");
    }

    @Test
    void parseRuleUnitInstanceId() {
        assertThat(RuleUnitIdParser.parse("/rule-units/u/instances/ui").getClass()).isEqualTo(RuleUnitInstanceId.class);
        assertThat(RuleUnitIdParser.parse("/rule-units/u/instances/ui", RuleUnitId.class).ruleUnitId()).isEqualTo("u");
        assertThat(RuleUnitIdParser.parse("/rule-units/u/instances/ui", RuleUnitInstanceId.class).ruleUnitInstanceId()).isEqualTo("ui");
    }

    @Test
    void parseInstanceQueryId() {
        assertThat(RuleUnitIdParser.parse("/rule-units/u/instances/ui/queries/q").getClass()).isEqualTo(InstanceQueryId.class);
        assertThat(RuleUnitIdParser.parse("/rule-units/u/instances/ui/queries/q", RuleUnitId.class).ruleUnitId()).isEqualTo("u");
        assertThat(RuleUnitIdParser.parse("/rule-units/u/instances/ui/queries/q", RuleUnitInstanceId.class).ruleUnitInstanceId()).isEqualTo("ui");
        assertThat(RuleUnitIdParser.parse("/rule-units/u/instances/ui/queries/q", InstanceQueryId.class).queryId()).isEqualTo("q");
    }

}