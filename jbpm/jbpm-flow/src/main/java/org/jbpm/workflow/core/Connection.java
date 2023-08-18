package org.jbpm.workflow.core;

/**
 * Represents a connection between two nodes in a workflow.
 * 
 */
public interface Connection extends org.kie.api.definition.process.Connection {

    void setMetaData(String name, Object value);

}
