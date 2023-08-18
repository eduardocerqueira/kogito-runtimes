package org.jbpm.process.core;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

import org.jbpm.process.instance.impl.humantask.DeadlineInfo;
import org.jbpm.process.instance.impl.humantask.Reassignment;
import org.kie.kogito.process.workitems.WorkParametersFactory;

public interface Work {

    void setName(String name);

    String getName();

    void setParameter(String name, Object value);

    void setParameters(Map<String, Object> parameters);

    Object getParameter(String name);

    Map<String, Object> getParameters();

    void addParameterDefinition(ParameterDefinition parameterDefinition);

    void setParameterDefinitions(Set<ParameterDefinition> parameterDefinitions);

    Set<ParameterDefinition> getParameterDefinitions();

    String[] getParameterNames();

    ParameterDefinition getParameterDefinition(String name);

    Set<String> getMetaParameters();

    /**
     * Retrieve information about non started deadlines.
     * <P>
     * Deadline information consist of the expiration date (which can be a exact date
     * or a potentially repeatable duration)and a list of key value pairs with arbitrary
     * information about the notification itself.
     * 
     * @return a collection containing deadline information.
     */
    Collection<DeadlineInfo<Map<String, Object>>> getNotStartedDeadlines();

    /**
     * Retrieve information about non completed deadlines.
     * <P>
     * Deadline information consist of the expiration date (which can be a exact date
     * or a potentially repeatable duration)and a list of key value pairs with arbitrary
     * information about the notification itself.
     * 
     * @return a collection containing deadline information.
     */
    Collection<DeadlineInfo<Map<String, Object>>> getNotCompletedDeadlines();

    Collection<DeadlineInfo<Reassignment>> getNotStartedReassignments();

    Collection<DeadlineInfo<Reassignment>> getNotCompletedReassigments();

    void setWorkParametersFactory(WorkParametersFactory factory);

    WorkParametersFactory getWorkParametersFactory();

}
