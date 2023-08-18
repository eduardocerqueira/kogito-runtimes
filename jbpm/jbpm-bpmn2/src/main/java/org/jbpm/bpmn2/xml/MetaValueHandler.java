package org.jbpm.bpmn2.xml;

import java.util.HashSet;

import org.jbpm.compiler.xml.Handler;
import org.jbpm.compiler.xml.Parser;
import org.jbpm.compiler.xml.core.BaseAbstractHandler;
import org.jbpm.process.core.ValueObject;
import org.jbpm.process.core.datatype.DataType;
import org.w3c.dom.Element;
import org.w3c.dom.Text;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public class MetaValueHandler extends BaseAbstractHandler implements Handler {

    public MetaValueHandler() {
        if ((this.validParents == null) && (this.validPeers == null)) {
            this.validParents = new HashSet<Class<?>>();
            this.validParents.add(ValueObject.class);

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
        return null;
    }

    public Object end(final String uri,
            final String localName,
            final Parser parser) throws SAXException {
        final Element element = parser.endElementBuilder();
        ValueObject valueObject = (ValueObject) parser.getParent();
        String text = ((Text) element.getChildNodes().item(0)).getWholeText();
        if (text != null) {
            text = text.trim();
            if ("".equals(text)) {
                text = null;
            }
        }
        Object value = restoreValue(text, valueObject.getType(), parser);
        valueObject.setValue(value);
        return null;
    }

    private Object restoreValue(String text, DataType dataType, Parser parser) throws SAXException {
        if (text == null || "".equals(text)) {
            return null;
        }
        if (dataType == null) {
            throw new SAXParseException(
                    "Null datatype", parser.getLocator());
        }
        return dataType.readValue(text);
    }

    public Class<?> generateNodeFor() {
        return null;
    }

}
