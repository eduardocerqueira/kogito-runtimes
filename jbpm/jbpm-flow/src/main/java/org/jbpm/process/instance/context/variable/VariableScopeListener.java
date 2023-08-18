package org.jbpm.process.instance.context.variable;

import java.util.List;
import java.util.Objects;

import org.jbpm.process.instance.InternalProcessRuntime;
import org.jbpm.process.instance.ProcessInstance;
import org.kie.kogito.internal.process.event.KogitoObjectListener;
import org.kie.kogito.internal.process.event.KogitoProcessEventSupport;

public class VariableScopeListener implements KogitoObjectListener {

    private final ProcessInstance processInstance;
    private final String variableIdPrefix;
    private final String variableInstanceIdPrefix;
    private final List<String> tags;

    public VariableScopeListener(ProcessInstance processInstance, String name, String variableIdPrefix, String variableInstanceIdPrefix, List<String> tags) {
        this.processInstance = processInstance;
        this.variableIdPrefix = getString(variableIdPrefix, name);
        this.variableInstanceIdPrefix = getString(variableInstanceIdPrefix, name);
        this.tags = tags;
    }

    private String getString(String prefix, String suffix) {
        return (prefix == null ? "" : prefix + ".") + suffix;
    }

    @Override
    public void afterValueChanged(Object container, String property, Object oldValue, Object newValue) {
        getProcessEventSupport().fireAfterVariableChanged(
                getString(variableIdPrefix, property),
                getString(variableInstanceIdPrefix, property),
                oldValue, newValue, tags, processInstance, null, processInstance.getKnowledgeRuntime());
    }

    @Override
    public void beforeValueChanged(Object container, String property, Object oldValue, Object newValue) {
        getProcessEventSupport().fireBeforeVariableChanged(
                getString(variableIdPrefix, property),
                getString(variableInstanceIdPrefix, property),
                oldValue, newValue, tags, processInstance, null, processInstance.getKnowledgeRuntime());
    }

    private KogitoProcessEventSupport getProcessEventSupport() {
        return ((InternalProcessRuntime) processInstance.getKnowledgeRuntime().getProcessRuntime()).getProcessEventSupport();
    }

    @Override
    public int hashCode() {
        return Objects.hash(processInstance, tags, variableIdPrefix, variableInstanceIdPrefix);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        VariableScopeListener other = (VariableScopeListener) obj;
        return Objects.equals(processInstance, other.processInstance) && Objects.equals(tags, other.tags)
                && Objects.equals(variableIdPrefix, other.variableIdPrefix)
                && Objects.equals(variableInstanceIdPrefix, other.variableInstanceIdPrefix);
    }
}
