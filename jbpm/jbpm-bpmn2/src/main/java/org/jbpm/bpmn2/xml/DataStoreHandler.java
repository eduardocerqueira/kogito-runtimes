package org.jbpm.bpmn2.xml;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.jbpm.bpmn2.core.*;
import org.jbpm.bpmn2.core.Error;
import org.jbpm.compiler.xml.Handler;
import org.jbpm.compiler.xml.Parser;
import org.jbpm.compiler.xml.ProcessBuildData;
import org.jbpm.compiler.xml.core.BaseAbstractHandler;
import org.jbpm.process.core.datatype.DataType;
import org.jbpm.process.core.datatype.impl.type.ObjectDataType;
import org.jbpm.ruleflow.core.RuleFlowProcess;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

public class DataStoreHandler extends BaseAbstractHandler implements Handler {

    @SuppressWarnings("rawtypes")
    public DataStoreHandler() {
        if ((this.validParents == null) && (this.validPeers == null)) {
            this.validParents = new HashSet<Class<?>>();
            this.validParents.add(Definitions.class);

            this.validPeers = new HashSet<Class<?>>();
            this.validPeers.add(null);
            this.validPeers.add(ItemDefinition.class);
            this.validPeers.add(Message.class);
            this.validPeers.add(Interface.class);
            this.validPeers.add(Escalation.class);
            this.validPeers.add(Error.class);
            this.validPeers.add(Signal.class);
            this.validPeers.add(DataStore.class);
            this.validPeers.add(RuleFlowProcess.class);

            this.allowNesting = false;
        }
    }

    public Object start(final String uri, final String localName,
            final Attributes attrs, final Parser parser)
            throws SAXException {
        parser.startElementBuilder(localName, attrs);
        DataStore store = new DataStore();
        store.setId(attrs.getValue("id"));
        store.setName(attrs.getValue("name"));
        final String itemSubjectRef = attrs.getValue("itemSubjectRef");
        store.setItemSubjectRef(itemSubjectRef);
        Map<String, ItemDefinition> itemDefinitions = (Map<String, ItemDefinition>) ((ProcessBuildData) parser.getData()).getMetaData("ItemDefinitions");
        // retrieve type from item definition
        //FIXME we bypass namespace resolving here. That's not a good idea when we start having several documents, with imports.
        String localItemSubjectRef = itemSubjectRef.substring(
                itemSubjectRef.indexOf(":") + 1);
        DataType dataType = new ObjectDataType();
        if (itemDefinitions != null) {
            ItemDefinition itemDefinition = itemDefinitions.get(localItemSubjectRef);
            if (itemDefinition != null) {
                dataType = new ObjectDataType(itemDefinition.getStructureRef(), parser.getClassLoader());
            }
        }
        store.setType(dataType);

        Definitions parent = (Definitions) parser.getParent();
        List<DataStore> dataStores = parent.getDataStores();
        if (dataStores == null) {
            dataStores = new ArrayList<>();
            parent.setDataStores(dataStores);
        }
        dataStores.add(store);
        return store;
    }

    @SuppressWarnings("unchecked")
    public Object end(final String uri, final String localName,
            final Parser parser) throws SAXException {
        parser.endElementBuilder();
        return parser.getCurrent();
    }

    public Class<?> generateNodeFor() {
        return DataStore.class;
    }

}
