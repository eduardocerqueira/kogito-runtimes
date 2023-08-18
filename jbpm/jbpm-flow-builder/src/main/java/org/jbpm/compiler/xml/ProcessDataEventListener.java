package org.jbpm.compiler.xml;

import org.jbpm.workflow.core.Node;
import org.kie.api.definition.process.Process;

public interface ProcessDataEventListener {

    void onNodeAdded(Node node);

    void onProcessAdded(Process process);

    void onMetaDataAdded(String name, Object data);

    void onComplete(Process process);

    void onBuildComplete(Process process);
}
