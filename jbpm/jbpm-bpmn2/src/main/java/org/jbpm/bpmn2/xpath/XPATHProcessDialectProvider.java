package org.jbpm.bpmn2.xpath;

import org.jbpm.process.builder.dialect.ProcessDialect;
import org.jbpm.process.builder.dialect.ProcessDialectProvider;

public class XPATHProcessDialectProvider implements ProcessDialectProvider {

    public static final String ID = "XPath";
    private static final XPATHProcessDialect DIALECT = new XPATHProcessDialect();

    @Override
    public String name() {
        return ID;
    }

    @Override
    public ProcessDialect dialect() {
        return DIALECT;
    }
}
