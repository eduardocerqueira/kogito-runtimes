package org.jbpm.process.instance.impl;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.Serializable;

import org.drools.base.definitions.rule.impl.RuleImpl;
import org.drools.base.rule.accessor.GlobalResolver;
import org.drools.core.common.InternalWorkingMemory;
import org.drools.mvel.MVELDialectRuntimeData;
import org.drools.mvel.expr.MVELCompilationUnit;
import org.drools.mvel.expr.MVELCompileable;
import org.jbpm.workflow.instance.impl.MVELProcessHelper;
import org.kie.kogito.internal.process.runtime.KogitoProcessContext;
import org.mvel2.integration.VariableResolverFactory;

public class MVELAction
        implements
        Action,
        MVELCompileable,
        Externalizable {
    private static final long serialVersionUID = 510l;

    private MVELCompilationUnit unit;
    private String id;

    private Serializable expr;

    public MVELAction() {
    }

    public MVELAction(final MVELCompilationUnit unit,
            final String id) {
        this.unit = unit;
        this.id = id;
    }

    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        id = in.readUTF();
        unit = (MVELCompilationUnit) in.readObject();
    }

    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeUTF(id);
        out.writeObject(unit);
    }

    public void compile(MVELDialectRuntimeData data) {
        expr = unit.getCompiledExpression(data);
    }

    public void compile(MVELDialectRuntimeData data, RuleImpl rule) {
        expr = unit.getCompiledExpression(data);
    }

    public String getDialect() {
        return id;
    }

    @Override
    public void execute(KogitoProcessContext context) throws Exception {
        int length = unit.getOtherIdentifiers().length;
        Object[] vars = new Object[length];
        if (unit.getOtherIdentifiers() != null) {
            for (int i = 0; i < length; i++) {
                vars[i] = context.getVariable(unit.getOtherIdentifiers()[i]);
            }
        }

        InternalWorkingMemory internalWorkingMemory = (InternalWorkingMemory) context.getKieRuntime();

        VariableResolverFactory factory = unit.getFactory(context,
                null, // No previous declarations
                null, // No rule
                null, // No "right object" 
                null, // No (left) tuples
                vars,
                internalWorkingMemory,
                (GlobalResolver) context.getKieRuntime().getGlobals());

        MVELProcessHelper.evaluator().executeExpression(this.expr,
                null,
                factory);

    }

    public Serializable getCompExpr() {
        return expr;
    }

}
