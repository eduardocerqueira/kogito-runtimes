package org.jbpm.workflow.instance.impl;

import java.util.Map;

import org.mvel2.integration.VariableResolver;
import org.mvel2.integration.impl.ImmutableDefaultFactory;
import org.mvel2.integration.impl.SimpleValueResolver;

public class OutputSetResolverFactory extends ImmutableDefaultFactory {

    private static final long serialVersionUID = 510l;

    private Map<String, Object> outputSet;

    public OutputSetResolverFactory(Map<String, Object> outputSet) {
        this.outputSet = outputSet;
    }

    @Override
    public boolean isResolveable(String name) {
        return outputSet.containsKey(name);
    }

    @Override
    public VariableResolver getVariableResolver(String name) {
        return new SimpleValueResolver(outputSet.get(name));
    }

}
