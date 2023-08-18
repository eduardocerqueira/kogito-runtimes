package org.kie.kogito.internal.process.runtime;

import java.util.List;
import java.util.Map;

import org.kie.api.runtime.KieRuntime;

public interface KogitoProcessContext {

    /**
     * Returns the process instance that is currently being
     * executed in this context.
     *
     * @return the process instance that is currently being
     *         executed in this context
     */
    KogitoProcessInstance getProcessInstance();

    /**
     * Returns the node instance that is currently being
     * executed in this context, or <code>null</code> if no
     * node instance is currently being executed.
     *
     * @return the node instance that is currently being
     *         executed in this context
     */
    KogitoNodeInstance getNodeInstance();

    default Map<String, List<String>> getHeaders() {
        return getProcessInstance().getHeaders();
    }

    /**
     * Returns the value of the variable with the given name.
     * Based on the current node instance, it will try to resolve
     * the given variable, taking nested variable scopes into
     * account. Returns <code>null</code> if the variable could
     * not be found.
     *
     * @param variableName the name of the variable
     * @return the value of the variable
     */
    Object getVariable(String variableName);

    /**
     * Sets the value of the variable with the given name.
     * Based on the current node instance, it will try to resolve
     * the given variable, taking nested variable scopes into
     * account.
     * <p>
     * If the variable cannot be resolved, it will set the value as
     * a process-level variable. It is however recommended to only
     * use this with caution, as it is always recommended to define
     * the variables that are used inside a process.
     *
     * @param variableName the name of the variable
     * @param value the value of the variable
     */
    void setVariable(String variableName, Object value);

    @Deprecated
    KieRuntime getKieRuntime();

    KogitoProcessRuntime getKogitoProcessRuntime();

    Map<String, Object> getContextData();
}