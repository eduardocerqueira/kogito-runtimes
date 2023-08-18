package org.jbpm.process.core;

/**
 * 
 */
public interface Contextable {

    void setContext(String contextType, Context context);

    Context getContext(String contextType);

}
