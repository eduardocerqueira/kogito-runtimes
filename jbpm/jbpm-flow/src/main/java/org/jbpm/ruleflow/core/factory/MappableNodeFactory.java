package org.jbpm.ruleflow.core.factory;

import org.jbpm.process.core.context.variable.Mappable;
import org.jbpm.workflow.core.impl.DataAssociation;

public interface MappableNodeFactory<T extends NodeFactory<T, ?>> {

    String METHOD_IN_MAPPING = "inMapping";
    String METHOD_OUT_MAPPING = "outMapping";

    String METHOD_IN_ASSOCIATION = "mapDataInputAssociation";
    String METHOD_OUT_ASSOCIATION = "mapDataOutputAssociation";

    Mappable getMappableNode();

    default T mapDataInputAssociation(DataAssociation dataAssociation) {
        getMappableNode().addInAssociation(dataAssociation);
        return (T) this;
    }

    default T mapDataOutputAssociation(DataAssociation dataAssociation) {
        getMappableNode().addOutAssociation(dataAssociation);
        return (T) this;
    }

    default T inMapping(String source, String target) {
        getMappableNode().addInMapping(source, target);
        return (T) this;
    }

    default T outMapping(String source, String target) {
        getMappableNode().addOutMapping(source, target);
        return (T) this;
    }
}
