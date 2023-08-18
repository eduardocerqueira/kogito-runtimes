package org.jbpm.compiler.xml.processes;

import java.util.HashSet;

import org.jbpm.compiler.xml.Handler;
import org.jbpm.compiler.xml.Parser;
import org.jbpm.compiler.xml.core.BaseAbstractHandler;
import org.jbpm.process.core.TypeObject;
import org.jbpm.process.core.datatype.DataType;
import org.jbpm.process.core.datatype.impl.type.ObjectDataType;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public class TypeHandler extends BaseAbstractHandler
        implements
        Handler {
    public TypeHandler() {
        if ((this.validParents == null) && (this.validPeers == null)) {
            this.validParents = new HashSet<Class<?>>();
            this.validParents.add(TypeObject.class);

            this.validPeers = new HashSet<Class<?>>();
            this.validPeers.add(null);

            this.allowNesting = false;
        }
    }

    public Object start(final String uri,
            final String localName,
            final Attributes attrs,
            final Parser parser) throws SAXException {
        parser.startElementBuilder(localName,
                attrs);
        TypeObject typeable = (TypeObject) parser.getParent();
        String name = attrs.getValue("name");
        emptyAttributeCheck(localName, "name", name, parser);
        DataType dataType = null;

        name = name.replace("org.drools.core.process.core", "org.jbpm.process.core");
        try {
            dataType = (DataType) Class.forName(name).newInstance();
            // TODO make this pluggable so datatypes can read in other properties as well
            if (dataType instanceof ObjectDataType) {
                String className = attrs.getValue("className");
                if (className == null) {
                    className = "java.lang.Object";
                }
                ((ObjectDataType) dataType).setClassName(className);
            }
        } catch (ClassNotFoundException e) {
            throw new SAXParseException(
                    "Could not find datatype " + name, parser.getLocator());
        } catch (InstantiationException e) {
            throw new SAXParseException(
                    "Could not instantiate datatype " + name, parser.getLocator());
        } catch (IllegalAccessException e) {
            throw new SAXParseException(
                    "Could not access datatype " + name, parser.getLocator());
        }

        typeable.setType(dataType);
        return dataType;
    }

    public Object end(final String uri,
            final String localName,
            final Parser parser) throws SAXException {
        parser.endElementBuilder();
        return null;
    }

    public Class<?> generateNodeFor() {
        return DataType.class;
    }

}
