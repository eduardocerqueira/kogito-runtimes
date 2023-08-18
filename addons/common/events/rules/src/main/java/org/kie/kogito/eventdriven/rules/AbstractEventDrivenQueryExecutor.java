package org.kie.kogito.eventdriven.rules;

import java.util.function.Function;

import org.drools.ruleunits.api.RuleUnit;
import org.drools.ruleunits.api.RuleUnitData;
import org.drools.ruleunits.api.RuleUnitInstance;
import org.kie.kogito.event.DataEvent;

public abstract class AbstractEventDrivenQueryExecutor<D extends RuleUnitData> implements EventDrivenQueryExecutor<D> {

    private RuleUnit<D> ruleUnit;
    private String queryName;
    private Function<RuleUnitInstance<D>, Object> queryFunction;
    private Class<D> objectClass;

    protected AbstractEventDrivenQueryExecutor() {
    }

    protected AbstractEventDrivenQueryExecutor(EventDrivenRulesController controller, RuleUnit<D> ruleUnit, String queryName, Function<RuleUnitInstance<D>, Object> queryFunction,
            Class<D> objectClass) {
        setup(controller, ruleUnit, queryName, queryFunction, objectClass);
    }

    protected void setup(EventDrivenRulesController controller, RuleUnit<D> ruleUnit, String queryName, Function<RuleUnitInstance<D>, Object> queryFunction, Class<D> objectClass) {
        this.ruleUnit = ruleUnit;
        this.queryName = queryName;
        this.queryFunction = queryFunction;
        this.objectClass = objectClass;
        controller.subscribe(this, objectClass);
    }

    @Override
    public String getRuleUnitId() {
        return objectClass.getCanonicalName();
    }

    @Override
    public String getQueryName() {
        return queryName;
    }

    @Override
    public Object executeQuery(DataEvent<D> input) {
        return internalExecuteQuery(input.getData());
    }

    private Object internalExecuteQuery(D input) {
        try (RuleUnitInstance<D> instance = ruleUnit.createInstance(input)) {
            return queryFunction.apply(instance);
        }
    }

    @Override
    public String toString() {
        return "AbstractEventDrivenQueryExecutor [ruleUnit=" + ruleUnit + ", queryName=" + queryName + ", objectClass="
                + objectClass + "]";
    }
}
