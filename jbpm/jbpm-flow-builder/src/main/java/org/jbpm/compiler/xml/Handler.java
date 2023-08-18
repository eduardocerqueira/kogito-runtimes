package org.jbpm.compiler.xml;

import java.util.Set;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

public interface Handler {

    Object start(String uri,
            String localName,
            Attributes attrs,
            Parser xmlPackageReader) throws SAXException;

    Object end(String uri,
            String localName,
            Parser xmlPackageReader) throws SAXException;

    Set<Class<?>> getValidParents();

    Set<Class<?>> getValidPeers();

    boolean allowNesting();

    Class<?> generateNodeFor();
}
