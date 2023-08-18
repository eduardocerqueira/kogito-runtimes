package org.jbpm.bpmn2.feel;

import org.jbpm.process.builder.dialect.ProcessDialect;
import org.jbpm.process.builder.dialect.ProcessDialectProvider;
import org.jbpm.process.core.validation.ProcessValidatorRegistry;

public class FeelProcessDialectProvider implements ProcessDialectProvider {

    private static final FeelProcessDialect DIALECT = new FeelProcessDialect();
    public static final String ID = "FEEL";

    static {
        ProcessValidatorRegistry.getInstance().registerAdditonalValidator(FeelProcessValidator.getInstance());
    }

    @Override
    public String name() {
        return ID;
    }

    @Override
    public ProcessDialect dialect() {
        return DIALECT;
    }
}
