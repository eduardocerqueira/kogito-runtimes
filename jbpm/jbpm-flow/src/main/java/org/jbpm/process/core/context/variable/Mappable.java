package org.jbpm.process.core.context.variable;

import java.util.List;
import java.util.Map;

import org.jbpm.workflow.core.impl.DataAssociation;

public interface Mappable {

    void addInMapping(String fromName, String toName);

    String getInMapping(String parameterName);

    Map<String, String> getInMappings();

    void addInAssociation(DataAssociation dataAssociation);

    List<DataAssociation> getInAssociations();

    void addOutMapping(String fromName, String toName);

    String getOutMapping(String parameterName);

    Map<String, String> getOutMappings();

    void addOutAssociation(DataAssociation dataAssociation);

    List<DataAssociation> getOutAssociations();

}
