package org.jbpm.ruleflow.instance;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import org.jbpm.process.instance.AbstractProcessInstanceFactory;
import org.jbpm.process.instance.ProcessInstance;

public class RuleFlowProcessInstanceFactory extends AbstractProcessInstanceFactory implements Externalizable {

    private static final long serialVersionUID = 510l;

    public ProcessInstance createProcessInstance() {
        return new RuleFlowProcessInstance();
    }

    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
    }

    public void writeExternal(ObjectOutput out) throws IOException {
    }

}
