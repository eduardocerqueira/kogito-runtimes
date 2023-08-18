package org.jbpm.compiler.xml.compiler.rules;

import org.drools.drl.ast.descr.AndDescr;
import org.drools.drl.ast.descr.PackageDescr;
import org.drools.drl.ast.descr.QueryDescr;
import org.jbpm.compiler.xml.Handler;
import org.jbpm.compiler.xml.Parser;
import org.jbpm.compiler.xml.core.BaseAbstractHandler;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

/**
 *
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class QueryHandler extends BaseAbstractHandler
        implements
        Handler {
    public QueryHandler() {
    }

    public Object start(final String uri,
            final String localName,
            final Attributes attrs,
            final Parser parser) throws SAXException {
        parser.startElementBuilder(localName,
                attrs);

        final String queryName = attrs.getValue("name");
        emptyAttributeCheck(localName, "name", queryName, parser);

        return new QueryDescr(queryName.trim());
    }

    public Object end(final String uri,
            final String localName,
            final Parser parser) throws SAXException {
        parser.endElementBuilder();

        final QueryDescr queryDescr = (QueryDescr) parser.getCurrent();

        final AndDescr lhs = queryDescr.getLhs();

        if (lhs == null || lhs.getDescrs().isEmpty()) {
            throw new SAXParseException("<query> requires a LHS",
                    parser.getLocator());
        }

        ((PackageDescr) parser.getData()).addRule(queryDescr);

        return queryDescr;
    }

    public Class generateNodeFor() {
        return QueryDescr.class;
    }
}
