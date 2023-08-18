package org.jbpm.process.instance;

import java.util.HashMap;
import java.util.Map;

import org.drools.core.process.AbstractProcessContext;
import org.kie.api.runtime.KieRuntime;
import org.kie.api.runtime.process.CaseAssignment;
import org.kie.api.runtime.process.CaseData;
import org.kie.kogito.internal.process.runtime.KogitoNodeInstance;
import org.kie.kogito.internal.process.runtime.KogitoProcessContext;
import org.kie.kogito.internal.process.runtime.KogitoProcessInstance;
import org.kie.kogito.internal.process.runtime.KogitoProcessRuntime;

public class KogitoProcessContextImpl extends AbstractProcessContext implements KogitoProcessContext {

    private Map<String, Object> contextData;

    public KogitoProcessContextImpl(KieRuntime kruntime) {
        super(kruntime);
        contextData = new HashMap<>();
    }

    @Override
    public KogitoProcessInstance getProcessInstance() {
        return (KogitoProcessInstance) super.getProcessInstance();
    }

    @Override
    public KogitoNodeInstance getNodeInstance() {
        return (KogitoNodeInstance) super.getNodeInstance();
    }

    @Override
    public KogitoProcessRuntime getKogitoProcessRuntime() {
        return InternalProcessRuntime.asKogitoProcessRuntime(getKieRuntime());
    }

    @Override
    public CaseAssignment getCaseAssignment() {
        throw new UnsupportedOperationException();
    }

    @Override
    public CaseData getCaseData() {
        throw new UnsupportedOperationException();
    }

    public void setContextData(Map<String, Object> contextData) {
        this.contextData = contextData;

    }

    @Override
    public Map<String, Object> getContextData() {
        return contextData;
    }
}
