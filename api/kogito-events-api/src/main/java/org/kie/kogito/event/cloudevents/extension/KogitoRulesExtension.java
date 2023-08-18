package org.kie.kogito.event.cloudevents.extension;

import java.util.Set;

import io.cloudevents.CloudEventExtension;
import io.cloudevents.CloudEventExtensions;
import io.cloudevents.core.provider.ExtensionProvider;

import static org.kie.kogito.event.cloudevents.CloudEventExtensionConstants.RULE_UNIT_ID;
import static org.kie.kogito.event.cloudevents.CloudEventExtensionConstants.RULE_UNIT_QUERY;
import static org.kie.kogito.event.cloudevents.extension.KogitoExtensionUtils.readStringExtension;

public class KogitoRulesExtension implements CloudEventExtension {

    private static final Set<String> KEYS = Set.of(RULE_UNIT_ID, RULE_UNIT_QUERY);

    private String ruleUnitId;
    private String ruleUnitQuery;

    public static void register() {
        ExtensionProvider.getInstance().registerExtension(KogitoRulesExtension.class, KogitoRulesExtension::new);
    }

    @Override
    public void readFrom(CloudEventExtensions extensions) {
        readStringExtension(extensions, RULE_UNIT_ID, this::setRuleUnitId);
        readStringExtension(extensions, RULE_UNIT_QUERY, this::setRuleUnitQuery);
    }

    @Override
    public Object getValue(String key) throws IllegalArgumentException {
        switch (key) {
            case RULE_UNIT_ID:
                return getRuleUnitId();
            case RULE_UNIT_QUERY:
                return getRuleUnitQuery();
            default:
                return null;
        }
    }

    @Override
    public Set<String> getKeys() {
        return KEYS;
    }

    public String getRuleUnitId() {
        return ruleUnitId;
    }

    public void setRuleUnitId(String ruleUnitId) {
        this.ruleUnitId = ruleUnitId;
    }

    public String getRuleUnitQuery() {
        return ruleUnitQuery;
    }

    public void setRuleUnitQuery(String ruleUnitQuery) {
        this.ruleUnitQuery = ruleUnitQuery;
    }

    @Override
    public String toString() {
        return "KogitoRulesExtension [ruleUnitId=" + ruleUnitId + ", ruleUnitQuery=" + ruleUnitQuery + "]";
    }
}
