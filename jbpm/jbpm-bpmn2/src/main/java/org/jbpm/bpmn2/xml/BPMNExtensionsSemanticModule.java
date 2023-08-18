package org.jbpm.bpmn2.xml;

import org.jbpm.compiler.xml.core.DefaultSemanticModule;

public class BPMNExtensionsSemanticModule extends DefaultSemanticModule {

    public static final String BPMN2_EXTENSIONS_URI = "http://www.jboss.org/drools";

    public BPMNExtensionsSemanticModule() {
        super(BPMN2_EXTENSIONS_URI);

        addHandler("import", new ImportHandler());
        addHandler("global", new GlobalHandler());
        addHandler("metaData", new MetaDataHandler());
        addHandler("metaValue", new MetaValueHandler());
    }

}
