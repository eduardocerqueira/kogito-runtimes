package org.jbpm.bpmn2.xml;

import org.jbpm.bpmn2.xml.di.BPMNEdgeHandler;
import org.jbpm.bpmn2.xml.di.BPMNPlaneHandler;
import org.jbpm.bpmn2.xml.di.BPMNShapeHandler;
import org.jbpm.compiler.xml.core.DefaultSemanticModule;

public class BPMNDISemanticModule extends DefaultSemanticModule {

    public BPMNDISemanticModule() {
        super("http://www.omg.org/spec/BPMN/20100524/DI");

        addHandler("BPMNPlane", new BPMNPlaneHandler());
        addHandler("BPMNShape", new BPMNShapeHandler());
        addHandler("BPMNEdge", new BPMNEdgeHandler());
    }

}
