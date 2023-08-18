package org.jbpm.process.instance.context.exception;

import org.jbpm.process.core.context.exception.ActionExceptionHandler;
import org.jbpm.process.core.context.exception.ExceptionHandler;
import org.jbpm.process.core.context.exception.ExceptionScope;
import org.jbpm.process.instance.ContextInstanceContainer;
import org.jbpm.process.instance.KogitoProcessContextImpl;
import org.jbpm.process.instance.ProcessInstance;
import org.jbpm.process.instance.impl.Action;
import org.jbpm.workflow.instance.NodeInstance;
import org.kie.kogito.internal.process.runtime.KogitoProcessContext;

public class DefaultExceptionScopeInstance extends ExceptionScopeInstance {

    private static final long serialVersionUID = 510l;

    @Override
    public String getContextType() {
        return ExceptionScope.EXCEPTION_SCOPE;
    }

    public void handleException(ExceptionHandler handler, String exception, KogitoProcessContext params) {

        if (handler instanceof ActionExceptionHandler) {
            ActionExceptionHandler exceptionHandler = (ActionExceptionHandler) handler;
            Action action = (Action) exceptionHandler.getAction().getMetaData("Action");
            try {
                ProcessInstance processInstance = getProcessInstance();
                KogitoProcessContextImpl processContext = new KogitoProcessContextImpl(processInstance.getKnowledgeRuntime());
                ContextInstanceContainer contextInstanceContainer = getContextInstanceContainer();
                if (contextInstanceContainer instanceof NodeInstance) {
                    processContext.setNodeInstance((NodeInstance) contextInstanceContainer);
                } else {
                    processContext.setNodeInstance((params).getNodeInstance());
                }
                processContext.setProcessInstance(processInstance);
                String faultVariable = exceptionHandler.getFaultVariable();
                if (faultVariable != null) {
                    processContext.setVariable(faultVariable, (params).getContextData().get("Exception"));
                }

                action.execute(processContext);
            } catch (Exception e) {
                throw new RuntimeException("unable to execute Action", e);
            }
        } else {
            throw new IllegalArgumentException("Unknown exception handler " + handler);
        }
    }

}
