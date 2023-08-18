package org.jbpm.ruleflow.core.factory;

import java.util.Set;

import org.jbpm.process.core.ParameterDefinition;
import org.jbpm.process.core.Work;
import org.jbpm.process.core.datatype.DataType;
import org.jbpm.process.core.impl.ParameterDefinitionImpl;
import org.jbpm.process.core.impl.WorkImpl;
import org.jbpm.ruleflow.core.RuleFlowNodeContainerFactory;
import org.jbpm.workflow.core.Node;
import org.jbpm.workflow.core.NodeContainer;
import org.jbpm.workflow.core.node.WorkItemNode;
import org.kie.kogito.process.workitems.WorkParametersFactory;

public class AbstractWorkItemNodeFactory<T extends AbstractWorkItemNodeFactory<T, P>, P extends RuleFlowNodeContainerFactory<P, ?>> extends StateBasedNodeFactory<T, P> {

    public static final String METHOD_WORK_NAME = "workName";
    public static final String METHOD_WORK_PARAMETER = "workParameter";

    protected AbstractWorkItemNodeFactory(P nodeContainerFactory, NodeContainer nodeContainer, Node node, long id) {
        super(nodeContainerFactory, nodeContainer, node, id);
    }

    public WorkItemNode getWorkItemNode() {
        return (WorkItemNode) getNode();
    }

    public T waitForCompletion(boolean waitForCompletion) {
        getWorkItemNode().setWaitForCompletion(waitForCompletion);
        return (T) this;
    }

    public T workName(String name) {
        Work work = getWorkItemNode().getWork();
        if (work == null) {
            work = new WorkImpl();
            getWorkItemNode().setWork(work);
        }
        work.setName(name);
        return (T) this;
    }

    public T workParameter(String name, Object value) {
        Work work = getWorkItemNode().getWork();
        if (work == null) {
            work = new WorkImpl();
            getWorkItemNode().setWork(work);
        }
        work.setParameter(name, value);
        return (T) this;
    }

    public T workParameterFactory(WorkParametersFactory factory) {
        Work work = getWorkItemNode().getWork();
        if (work == null) {
            work = new WorkImpl();
            getWorkItemNode().setWork(work);
        }
        work.setWorkParametersFactory(factory);
        return (T) this;
    }

    public T workParameterDefinition(String name, DataType dataType) {
        Work work = getWorkItemNode().getWork();
        if (work == null) {
            work = new WorkImpl();
            getWorkItemNode().setWork(work);
        }
        Set<ParameterDefinition> parameterDefinitions = work.getParameterDefinitions();
        parameterDefinitions.add(new ParameterDefinitionImpl(name, dataType));
        work.setParameterDefinitions(parameterDefinitions);
        return (T) this;
    }
}
