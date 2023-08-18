package org.jbpm.process.instance.impl;

import java.io.Serializable;

import org.drools.core.common.InternalAgenda;
import org.jbpm.process.instance.ProcessInstance;
import org.jbpm.workflow.core.Constraint;
import org.jbpm.workflow.core.Node;
import org.jbpm.workflow.instance.NodeInstance;
import org.kie.api.definition.process.Connection;

/**
 * Default implementation of a constraint.
 * 
 */
public class RuleConstraintEvaluator implements Constraint,
        ConstraintEvaluator, Serializable {

    private static final long serialVersionUID = 510l;

    private String name;
    private String constraint;
    private int priority;
    private String dialect;
    private String type;
    private boolean isDefault;

    public String getConstraint() {
        return this.constraint;
    }

    public void setConstraint(final String constraint) {
        this.constraint = constraint;
    }

    public String getName() {
        return this.name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String toString() {
        return this.name;
    }

    public int getPriority() {
        return this.priority;
    }

    public void setPriority(final int priority) {
        this.priority = priority;
    }

    public String getDialect() {
        return dialect;
    }

    public void setDialect(String dialect) {
        this.dialect = dialect;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isDefault() {
        return isDefault;
    }

    public void setDefault(boolean isDefault) {
        this.isDefault = isDefault;
    }

    public boolean evaluate(NodeInstance instance,
            Connection connection,
            Constraint constraint) {
        ProcessInstance processInstance = (ProcessInstance) instance.getProcessInstance();
        InternalAgenda agenda = (InternalAgenda) processInstance.getKnowledgeRuntime().getAgenda();
        String rule = "RuleFlow-Split-" + processInstance.getProcessId() + "-" +
                ((Node) instance.getNode()).getUniqueId() + "-" +
                ((Node) connection.getTo()).getUniqueId() + "-" + connection.getToType();

        return agenda.isRuleActiveInRuleFlowGroup("DROOLS_SYSTEM", rule, processInstance.getStringId());
    }

    public Object getMetaData(String name) {
        return null;
    }

    public void setMetaData(String name, Object value) {
        // Do nothing
    }

}
