package org.jbpm.process.instance;

/**
 * 
 */
public interface ContextableInstance {

    ContextInstance getContextInstance(String contextId);

}
