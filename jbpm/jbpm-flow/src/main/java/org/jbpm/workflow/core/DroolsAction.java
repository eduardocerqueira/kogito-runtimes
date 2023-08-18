package org.jbpm.workflow.core;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.HashMap;
import java.util.Map;

import org.drools.base.rule.accessor.CompiledInvoker;
import org.drools.base.rule.accessor.Wireable;

public class DroolsAction implements Externalizable, Wireable {

    private static final long serialVersionUID = 510l;

    private String name;
    private Map<String, Object> metaData = new HashMap<>();

    public void wire(Object object) {
        setMetaData("Action",
                object);
    }

    public void setMetaData(String name, Object value) {
        if ("Action".equals(name)) {
            this.metaData.putIfAbsent(name, value);
        } else {
            this.metaData.put(name, value);
        }
    }

    public Object getMetaData(String name) {
        return this.metaData.get(name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @SuppressWarnings("unchecked")
    public void readExternal(ObjectInput in) throws IOException,
            ClassNotFoundException {
        this.name = (String) in.readObject();
        this.metaData = (Map<String, Object>) in.readObject();
        Object action = in.readObject();
        setMetaData("Action", action);
    }

    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(name);
        Object action = this.metaData.remove("Action");
        out.writeObject(this.metaData);
        if (action instanceof CompiledInvoker) {
            out.writeObject(null);
        } else {
            out.writeObject(action);
        }
        setMetaData("Action", action);
    }

}
