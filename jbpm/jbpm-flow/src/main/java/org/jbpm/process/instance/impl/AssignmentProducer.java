package org.jbpm.process.instance.impl;

public interface AssignmentProducer {
    void accept(String name, Object value);
}